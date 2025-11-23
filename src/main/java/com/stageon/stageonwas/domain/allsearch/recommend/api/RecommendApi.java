package com.stageon.stageonwas.domain.allsearch.recommend.api;

import com.stageon.stageonwas.domain.allsearch.recommend.dto.RecommendedResDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Recommend API", description = "추천 검색어(공연명) 조회 API")
@RequestMapping("/api/v1/recommend")
public interface RecommendApi {

    @Operation(
            summary = "추천 검색어 조회",
            description = "데이터베이스에 존재하는 공연명 중 무작위로 5개를 선정하여 추천 검색어로 반환합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(
                            schema = @Schema(implementation = RecommendedResDto.class),
                            examples = @ExampleObject(value = """
                                    [
                                      {
                                        "keyword": "싸이 흠뻑쇼 2024"
                                      },
                                      {
                                        "keyword": "검정치마 단독 공연"
                                      },
                                      {
                                        "keyword": "지킬 앤 하이드"
                                      },
                                      {
                                        "keyword": "2024 펜타포트 락 페스티벌"
                                      },
                                      {
                                        "keyword": "아이유 콘서트 : The Golden Hour"
                                      }
                                    ]
                                    """)
                    )
            )
    })
    @GetMapping
    ResponseEntity<List<RecommendedResDto>> getRecommendedKeywords(
            @AuthenticationPrincipal CustomUserDetails userDetails
    );
}