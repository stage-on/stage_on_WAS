package com.stageon.stageonwas.domain.auth.jwt;

import com.stageon.stageonwas.domain.auth.entity.User;
import com.stageon.stageonwas.domain.auth.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    // (주의 할 부분: 이 주소는 'permitAll'이 아니어야 함. JwtAuthenticationFilter가 검사해야 하니까)
    private static final String TARGET_URL = "/api/v1/stage/home";


    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        // 2. principal을 (User)가 아닌 (OAuth2User)로 받기
        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        // 3. OAuth2User에서 email 속성 꺼내기
        // (참고: 구글은 'email', 카카오는 'kakao_account'.'email' 일 수 있음)
        String email = oauthUser.getAttribute("email");

        // 4. DB에서 해당 email의 "우리 User 엔티티" 조회
        // (OAuth2Service에서 이미 회원가입/업데이트 처리를 했다고 가정합니다)
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("OAuth2 로그인 후 DB에서 유저를 찾을 수 없습니다. email: " + email));

        // 5. 우리 User 엔티티의 이메일로 JWT 토큰 발급
        String accessToken = jwtProvider.generateAccessToken(user.getEmail());

        // 6. 리디렉션 URL 생성 (토큰을 쿼리 파라미터로 추가)
        String redirectUrl = UriComponentsBuilder.fromUriString(TARGET_URL)
                .queryParam("token", accessToken)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString();

        // 7. 생성된 URL로 리디렉션
        response.sendRedirect(redirectUrl);
    }
}