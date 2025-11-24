package com.stageon.stageonwas.domain.auth.controller;

import com.stageon.stageonwas.domain.auth.dto.ProfileResDto;
import com.stageon.stageonwas.domain.auth.service.ProfileService;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/api/v1/profile")
    public ResponseEntity<ProfileResDto> getMyProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {

        String email = userDetails.getUser().getEmail();
        String provider = userDetails.getUser().getProvider();

        ProfileResDto responseDto = profileService.getMyProfile(email, provider);

        return ResponseEntity.ok(responseDto);
    }
}