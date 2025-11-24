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
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    private static final String TARGET_URL = "https://stage-on-web.vercel.app/main/home";

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        Map<String, Object> attributes = oauthUser.getAttributes();
        String email = (String) attributes.get("email");

        // 이메일이 없으면 로그 찍고 에러 처리
        if (email == null) {
            System.out.println("OAuth2SuccessHandler: 이메일을 찾을 수 없습니다.");
            throw new RuntimeException("이메일 정보를 찾을 수 없습니다.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다. email: " + email));

        // JWT 토큰 발급
        String accessToken = jwtProvider.generateAccessToken(user.getUserId());

        // 리디렉션 URL 생성
        String redirectUrl = UriComponentsBuilder.fromUriString(TARGET_URL)
                .queryParam("token", accessToken)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString();

        // 리디렉션
        response.sendRedirect(redirectUrl);
    }
}