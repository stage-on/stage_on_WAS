package com.stageon.stageonwas.config;

import com.stageon.stageonwas.domain.auth.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2Service oAuth2Service; // (OAuth2UserService 구현체)

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http
                //  csrf, logout, formLogin 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/","/api/v1/oauth/login").permitAll()
                        .anyRequest().authenticated()
                )


                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/oauth/loginInfo", true)
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2Service)
                        )
                );

        return http.build();
    }
}