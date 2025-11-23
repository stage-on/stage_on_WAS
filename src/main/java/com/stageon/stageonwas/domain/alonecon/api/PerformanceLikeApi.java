package com.stageon.stageonwas.domain.alonecon.api;

import com.stageon.stageonwas.domain.alonecon.dto.PerformanceLikeResDto;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Performance Like API", description = "공연 좋아요 및 목록 조회 API")
@RequestMapping("/api/v1/likes")
public interface PerformanceLikeApi {

    @Operation(
            summary = "공연 좋아요 등록",
            description = "사용자가 특정 공연에 좋아요를 등록합니다. (최대 5개 제한)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "좋아요 등록 성공",
                    content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(responseCode = "400", description = "유효성 및 비즈니스 조건 실패",
                    content = @Content(examples = {
                            @ExampleObject(name = "좋아요 개수 초과", value = """
                                    { "status": 400, "message": "좋아요는 아티스트와 공연을 합쳐 최대 5개까지만 가능합니다." }
                                    """),
                            @ExampleObject(name = "파라미터 타입 불일치", value = """
                                    { "status": 400, "message": "performanceId 파라미터 형식이 올바르지 않습니다." }
                                    """)
                    })
            ),
            @ApiResponse(responseCode = "409", description = "이미 좋아요 누른 상태",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 409, "message": "이미 좋아요를 누른 대상입니다." }
                            """))
            ),
            @ApiResponse(responseCode = "404", description = "리소스 없음",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 404, "message": "존재하지 않는 공연입니다." }
                            """))
            ),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @PostMapping("/performances/{performanceId}")
    ResponseEntity<String> likePerformance(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long performanceId);


    @Operation(
            summary = "공연 좋아요 취소",
            description = "사용자가 특정 공연에 등록된 좋아요를 취소합니다. (최소 2개 유지)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "좋아요 취소 성공",
                    content = @Content(schema = @Schema(hidden = true))
            ),
            @ApiResponse(responseCode = "400", description = "최소 개수 미달",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 400, "message": "좋아요는 최소 2개를 유지해야 합니다." }
                            """))
            ),
            @ApiResponse(responseCode = "404", description = "좋아요 내역 없음",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 404, "message": "좋아요 내역이 존재하지 않습니다." }
                            """))
            ),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @DeleteMapping("/performances/{performanceId}")
    ResponseEntity<String> unlikePerformance(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long performanceId);
    

    @Operation(
            summary = "좋아요 누른 콘서트 목록 조회",
            description = "현재 사용자가 좋아요를 누른 모든 공연 목록을 조회합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(
                            schema = @Schema(implementation = PerformanceLikeResDto.class),
                            examples = @ExampleObject(value = """
                                    [
                                              {
                                                "performanceId": 1,
                                                "title": "검정치마 단독공연",
                                                "posterUrl": "https://image.example.com/poster/1.jpg",
                                                "prfpdfrom": "2025-12-20",
                                                "prfpdto": "2025-12-21",
                                                "fcltynm": "일산 킨텍스"
                                              },
                                              {
                                                "performanceId": 7,
                                                "title": "2024 아이유 콘서트",
                                                "posterUrl": "https://image.example.com/poster/7.jpg",
                                                "prfpdfrom": "2024-11-09",
                                                "prfpdto": "2024-11-10",
                                                "fcltynm": "KSPO DOME"
                                              }
                                            ]
                                    """)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "사용자 없음",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 404, "message": "존재하지 않는 회원입니다." }
                            """))
            ),
            @ApiResponse(responseCode = "401", description = "인증 실패")
    })
    @GetMapping("/my/concerts")
    ResponseEntity<List<PerformanceLikeResDto>> getMyConcerts(
            @AuthenticationPrincipal CustomUserDetails userDetails);
}