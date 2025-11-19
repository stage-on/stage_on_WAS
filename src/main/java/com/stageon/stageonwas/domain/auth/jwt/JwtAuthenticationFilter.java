package com.stageon.stageonwas.domain.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private static final List<String> EXCLUDE_URLS = List.of(
            "/oauth2/**",
            "/error",
            "/v3/api-docs",         // v3/api-docs 전체 주소 (정확한 매칭을 위해)
            "/v3/api-docs/**",      // v3/api-docs 하위 경로
            "/swagger-ui",          // swagger-ui 전체 주소
            "/swagger-ui/**",       // swagger-ui 하위 경로
            "/swagger-resources/**",
            "/swagger-resources",
            "/swagger-ui.html",
            "/webjars/**",
            "/api-docs/**"
    );

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return EXCLUDE_URLS.stream().anyMatch(exclude -> pathMatcher.match(exclude, path));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 1. URL 쿼리 파라미터에서 먼저 토큰 확인
        String token = request.getParameter("token");

        // 2. 쿼리 파라미터에 토큰이 없으면, 기존처럼 헤더에서 확인
        if (token == null) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            }
        }

        // 3. 토큰이 (어떤 방식이든) 존재하면 검증 시작
        if (token != null && jwtProvider.validateToken(token)) {
            String email = jwtProvider.getSubject(token);

            // email을 principal로 저장
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}