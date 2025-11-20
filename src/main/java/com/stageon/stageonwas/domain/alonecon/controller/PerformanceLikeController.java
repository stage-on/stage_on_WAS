package com.stageon.stageonwas.domain.alonecon.controller;

import com.stageon.stageonwas.domain.alonecon.dto.PerformanceLikeResDto;
import com.stageon.stageonwas.domain.alonecon.service.PerformanceLikeService;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class PerformanceLikeController {

    private final PerformanceLikeService performanceLikeService;

    // 공연 좋아요 api 체크 및 cicd 체크용 주석
    @PostMapping("/performances/{performanceId}")
    public ResponseEntity<String> likePerformance(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long performanceId) {

        Long userId = userDetails.getId();
        performanceLikeService.likePerformance(userId, performanceId);
        return ResponseEntity.ok("공연 좋아요 성공");
    }

    // 공연 좋아요 취소
    @DeleteMapping("/performances/{performanceId}")
    public ResponseEntity<String> unlikePerformance(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long performanceId) {

        Long userId = userDetails.getId();
        performanceLikeService.unlikePerformance(userId, performanceId);
        return ResponseEntity.ok("공연 좋아요 취소 성공");
    }

    // 좋아요 누른 콘서트 목록 조회
    @GetMapping("/my/concerts")
    public ResponseEntity<List<PerformanceLikeResDto>> getMyConcerts(@AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getId();
        List<PerformanceLikeResDto> myConcerts = performanceLikeService.getMyConcerts(userId);
        return ResponseEntity.ok(myConcerts);
    }
}
