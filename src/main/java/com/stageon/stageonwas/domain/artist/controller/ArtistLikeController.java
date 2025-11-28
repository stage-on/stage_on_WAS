package com.stageon.stageonwas.domain.artist.controller;

import com.stageon.stageonwas.domain.artist.api.ArtistLikeApi;
import com.stageon.stageonwas.domain.artist.dto.ArtistLikeResDto;
import com.stageon.stageonwas.domain.artist.dto.ArtistSelectReqDto;
import com.stageon.stageonwas.domain.artist.service.ArtistLikeService;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class ArtistLikeController implements ArtistLikeApi {

    private final ArtistLikeService artistLikeService;

    @PostMapping("/artists/{artistId}")
    public ResponseEntity<String> likeArtist(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long artistId) {

        Long userId = userDetails.getId();
        artistLikeService.likeArtist(userId, artistId);
        return ResponseEntity.ok("아티스트 좋아요 성공");
    }

    @PostMapping("/artists/delete")
    public ResponseEntity<String> unlikeArtist(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody ArtistSelectReqDto artistSelectReqDto) {

        Long userId = userDetails.getId();
        artistLikeService.unlikeArtist(userId, artistSelectReqDto.getArtistIds());
        return ResponseEntity.ok("아티스트 좋아요 취소 성공");
    }

    @GetMapping("/my/bands")
    public ResponseEntity<List<ArtistLikeResDto>> getMyBands(
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getId();

        List<ArtistLikeResDto> myBands = artistLikeService.getMyBands(userId);
        return ResponseEntity.ok(myBands);
    }
}