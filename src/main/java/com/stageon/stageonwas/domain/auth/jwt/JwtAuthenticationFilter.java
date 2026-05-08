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

    // 인증 검사를 생략할 화이트리스트 경로 설정 (로그인, 스웨거 문서 등)
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

    // 현재 요청 경로가 인증 제외 목록에 해당하는지 판별하여 필터 수행 여부 결정
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return EXCLUDE_URLS.stream().anyMatch(exclude -> pathMatcher.match(exclude, path));
    }

    // 모든 요청에 대해 실행되는 필터 내부 로직
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // 헤더나 파라미터로부터 JWT 토큰 문자열을 추출
        String token = resolveToken(request);

        // 토큰이 유효하며 서버의 시크릿 키로 검증이 가능한 경우 진행
        if (token != null && jwtProvider.validateToken(token)) {

            // 토큰의 Payload 영역에서 유저의 고유 식별값(ID)을 추출
            String subject = jwtProvider.getSubject(token);

            // 추출한 식별값을 이용해 DB에서 실제 유저의 엔티티 정보를 조회
            User user = userRepository.findById(Long.valueOf(subject))
                    .orElse(null);

            if (user != null) {
                // 조회된 유저 정보를 기반으로 Spring Security 전용 유저 상세 객체 생성
                CustomUserDetails userDetails = new CustomUserDetails(user);

                // 유저 정보와 권한을 포함하는 인증 토큰 객체 생성
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // 현재 스레드의 보안 컨텍스트에 인증 객체 저장 (이후 로직에서 로그인을 유지한 것으로 간주)
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 인증 로직 수행 후 다음 필터 체인으로 요청을 전달
        chain.doFilter(request, response);
    }

    // HTTP 요청의 다양한 위치(파라미터 또는 헤더)에서 토큰을 수집하는 보조 메서드
    private String resolveToken(HttpServletRequest request) {
        String token = request.getParameter("token");
        if (token == null) {
            String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7); // "Bearer " 문자열을 제외한 실제 토큰 값만 추출
            }
        }
        return token;
    }
}