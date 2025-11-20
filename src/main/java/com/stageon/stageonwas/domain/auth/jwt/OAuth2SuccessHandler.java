package com.stageon.stageonwas.domain.auth.jwt;

import com.stageon.stageonwas.domain.auth.dto.UserProfile;
import com.stageon.stageonwas.domain.auth.entity.OAuthAttributes;
import com.stageon.stageonwas.domain.auth.entity.User;
import com.stageon.stageonwas.domain.auth.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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

    // permitAll이 아니어야 함. JwtAuthenticationFilter가 검사해야함
    private static final String TARGET_URL = "https://stage-on-web.vercel.app/main/home";


    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        String registrationId = authToken.getAuthorizedClientRegistrationId();

        UserProfile userProfile = OAuthAttributes.extract(registrationId, oauthUser.getAttributes());

        String email = userProfile.getEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다. email: " + email));

        // JWT 토큰 발급
        String accessToken = jwtProvider.generateAccessToken(user.getUserId());

        // 리디렉션 URL 생성 (토큰을 쿼리 파라미터로 추가)
        String redirectUrl = UriComponentsBuilder.fromUriString(TARGET_URL)
                .queryParam("token", accessToken)
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString();

        // 생성된 URL로 리디렉션
        response.sendRedirect(redirectUrl);
    }
}