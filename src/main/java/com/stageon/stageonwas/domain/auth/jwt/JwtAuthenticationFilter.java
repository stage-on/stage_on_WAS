package com.stageon.stageonwas.domain.auth.jwt;

import com.stageon.stageonwas.domain.auth.entity.User;
import com.stageon.stageonwas.domain.auth.repository.UserRepository;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

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

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return EXCLUDE_URLS.stream().anyMatch(exclude -> pathMatcher.match(exclude, path));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 토큰 추출
        String token = resolveToken(request);

        // 토큰 검증 및 인증 처리
        if (token != null && jwtProvider.validateToken(token)) {

            // 토큰에서 subject추출
            String subject = jwtProvider.getSubject(token);

            // DB에서 User 조회
            // (만약 토큰 subject가 userId라면 findById로 변경해야 함)
            User user = userRepository.findById(Long.valueOf(subject))
                    .orElse(null);

            if (user != null) {
                // CustomUserDetails 생성
                CustomUserDetails userDetails = new CustomUserDetails(user);

                // SecurityContext에 저장
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String token = request.getParameter("token");
        if (token == null) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
            }
        }
        return token;
    }
}