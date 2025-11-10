package com.stageon.stageonwas.domain.artist.controller;

import com.stageon.stageonwas.domain.artist.dto.ArtistLikeResDto;
import com.stageon.stageonwas.domain.artist.service.ArtistLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class ArtistLikeController {

    private final ArtistLikeService artistLikeService;

    @PostMapping("/artists/{artistId}")
    public ResponseEntity<String> likeArtist(
            @RequestParam Long userId,
            @PathVariable Long artistId) {
        artistLikeService.likeArtist(userId, artistId);
        return ResponseEntity.ok("아티스트 좋아요 성공");
    }

    @DeleteMapping("/artists/{artistId}")
    public ResponseEntity<String> unlikeArtist(
            @RequestParam Long userId,
            @PathVariable Long artistId) {
        artistLikeService.unlikeArtist(userId, artistId);
        return ResponseEntity.ok("아티스트 좋아요 취소 성공");
    }

    // 좋아요 한 아티스트 조회
    @GetMapping("/my/bands")
    public ResponseEntity<List<ArtistLikeResDto>> getMyBands(@RequestParam Long userId) {
        List<ArtistLikeResDto> myBands = artistLikeService.getMyBands(userId);
        return ResponseEntity.ok(myBands);
    }
}