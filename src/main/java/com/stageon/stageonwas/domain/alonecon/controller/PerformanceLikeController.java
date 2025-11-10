package com.stageon.stageonwas.domain.alonecon.controller;

import com.stageon.stageonwas.domain.alonecon.dto.PerformanceLikeResDto;
import com.stageon.stageonwas.domain.alonecon.service.PerformanceLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class PerformanceLikeController {

    private final PerformanceLikeService performanceLikeService;

    // 공연 좋아요
    @PostMapping("/performances/{performanceId}")
    public ResponseEntity<String> likePerformance(
            @RequestParam Long userId,
            @PathVariable Long performanceId) {
        performanceLikeService.likePerformance(userId, performanceId);
        return ResponseEntity.ok("공연 좋아요 성공");
    }

    // 공연 좋아요 취소
    @DeleteMapping("/performances/{performanceId}")
    public ResponseEntity<String> unlikePerformance(
            @RequestParam Long userId,
            @PathVariable Long performanceId) {
        performanceLikeService.unlikePerformance(userId, performanceId);
        return ResponseEntity.ok("공연 좋아요 취소 성공");
    }

    // 좋아요 누른 콘서트 목록 조회
    @GetMapping("/my/concerts")
    public ResponseEntity<List<PerformanceLikeResDto>> getMyConcerts(@RequestParam Long userId) {
        List<PerformanceLikeResDto> myConcerts = performanceLikeService.getMyConcerts(userId);
        return ResponseEntity.ok(myConcerts);
    }
}
