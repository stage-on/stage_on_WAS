package com.stageon.stageonwas.domain.alonecon.controller;

import com.stageon.stageonwas.domain.alonecon.api.PerformanceLikeApi;
import com.stageon.stageonwas.domain.alonecon.dto.PerformanceLikeResDto;
import com.stageon.stageonwas.domain.alonecon.service.PerformanceLikeService;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/likes")
public class PerformanceLikeController implements PerformanceLikeApi {

    private final PerformanceLikeService performanceLikeService;

    // 2) 좋아요 누른 공연 중 typeofcon == 2 (페스티벌만)
    @Operation(
            summary = "좋아요한 공연 중 페스티벌만 조회",
            description = """
                    사용자가 좋아요한 공연들 중  
                    `typeofcon = 2`(페스티벌) 항목만 필터링하여 반환합니다.
                    
                    🏷 사용 예:
                    - 'My Festivals Only'
                    - 찜한 공연 중 페스티벌 목록 표시
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공 (페스티벌 목록 반환)"),
            @ApiResponse(responseCode = "204", description = "좋아요한 공연 중 페스티벌이 없음"),
            @ApiResponse(responseCode = "401", description = "로그인이 필요함")
    })
    @GetMapping("/my/festivals")
    public ResponseEntity<List<PerformanceLikeResDto>> getMyFestivalConcerts(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getId();
        List<PerformanceLikeResDto> result = performanceLikeService.getMyFestivalConcerts(userId);
        return ResponseEntity.ok(result);
    }

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
