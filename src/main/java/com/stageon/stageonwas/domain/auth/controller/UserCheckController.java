package com.stageon.stageonwas.domain.auth.controller;

import com.stageon.stageonwas.domain.auth.dto.UserCheckResDto;
import com.stageon.stageonwas.domain.auth.service.UserCheckService;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserCheckController {
    private final UserCheckService userCheckService;
    @GetMapping("/login-check")
    public ResponseEntity<UserCheckResDto> checkLoginStatus(@AuthenticationPrincipal CustomUserDetails userDetails) {
        // 서비스에게 "이 사람 상태 좀 확인해줘" 라고 시킴
        UserCheckResDto response = userCheckService.checkLoginStatus(userDetails.getUsername());

        return ResponseEntity.ok(response);
    }

}
