package com.stageon.stageonwas.domain.allsearch.history.api;

import com.stageon.stageonwas.domain.allsearch.history.dto.SearchHistoryResDto;
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

@Tag(name = "Search History API", description = "검색 기록 조회 및 삭제 API")
@RequestMapping("/api/v1/search")
public interface HistoryApi {

    @Operation(
            summary = "최근 검색 기록 조회",
            description = "로그인한 사용자의 최근 검색 기록 목록을 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(
                            schema = @Schema(implementation = SearchHistoryResDto.class)
                    )
            ),
            @ApiResponse(responseCode = "404", description = "사용자 없음",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 404, "message": "존재하지 않는 회원입니다." }
                            """)))
    })
    @GetMapping("/history")
    ResponseEntity<List<SearchHistoryResDto>> getRecentSearches(
            @AuthenticationPrincipal CustomUserDetails userDetails
    );


    @Operation(
            summary = "검색 기록 삭제",
            description = "특정 검색 기록 항목을 삭제합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "삭제 성공",
                    content = @Content(examples = @ExampleObject("검색 기록 삭제 성공"))
            ),
            @ApiResponse(responseCode = "403", description = "삭제 권한 없음",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 403, "message": "접근 권한이 없습니다." }
                            """))
            ),
            @ApiResponse(responseCode = "404", description = "검색 기록 없음",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 404, "message": "존재하지 않는 검색 기록입니다." }
                            """))
            )
    })
    @DeleteMapping("/history/{historyId}")
    ResponseEntity<String> deleteSearchHistory(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long historyId
    );
}