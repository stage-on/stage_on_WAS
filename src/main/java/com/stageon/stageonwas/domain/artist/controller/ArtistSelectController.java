package com.stageon.stageonwas.domain.artist.controller;

import com.stageon.stageonwas.domain.artist.api.ArtistSelectApi;
import com.stageon.stageonwas.domain.artist.dto.ArtistSelectReqDto;
import com.stageon.stageonwas.domain.artist.service.ArtistSelectService;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/first")
@RequiredArgsConstructor
public class ArtistSelectController implements ArtistSelectApi {

    private final ArtistSelectService artistSelectService;

    @PostMapping("/select")
    public ResponseEntity<String> completeOnboarding(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody ArtistSelectReqDto requestDto
    ) {
        artistSelectService.selectArtists(userDetails.getId(), requestDto.getArtistIds());

        return ResponseEntity.ok("아티스트 선택 및 회원가입이 완료되었습니다.");
    }
}