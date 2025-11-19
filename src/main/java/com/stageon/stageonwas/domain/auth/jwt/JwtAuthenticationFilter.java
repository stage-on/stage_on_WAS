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
            "/**/v3/api-docs/**",
            "/**/swagger-ui/**",
            "/**/swagger-resources/**",
            "/**/webjars/**",
            "/**/api-docs/**",
            "/**/swagger-ui.html"
    );

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        System.out.println("=================================================");
        System.out.println(">>> [JwtFilter] 들어온 요청 주소(URI): " + path);
        System.out.println("=================================================");

        // 🛡️ [해결책] 주소 앞뒤가 어떻게 짤렸든 'swagger'만 포함되면 통과시킵니다.
        if (path.contains("/swagger-ui") ||
                path.contains("/v3/api-docs") ||
                path.contains("/swagger-resources") ||
                path.contains("/webjars") ||
                path.contains("/oauth2") ||
                path.contains("/error") ||
                path.contains("/api-docs")) {
            return true; // 필터 건너뛰기
        }

        return false;
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