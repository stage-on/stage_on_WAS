package com.stageon.stageonwas.domain.allsearch.search.api;

import com.stageon.stageonwas.domain.allsearch.search.dto.SearchResDto;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Search API", description = "통합 검색 API")
@RequestMapping("/api/v1/search")
public interface SearchApi {

    @Operation(
            summary = "통합 검색",
            description = "키워드로 아티스트 및 공연 정보를 검색하고 검색 기록을 저장합니다."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(
                            schema = @Schema(implementation = SearchResDto.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "performances": {
                                            "count": 2,
                                            "items": [
                                                {
                                                    "performanceId": 1,
                                                    "title": "검정치마 단독 공연: LAST CHANCE TO COME BACK HOME",
                                                    "posterUrl": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF262418_250404_101520.gif",
                                                    "startDate": "2025-05-02",
                                                    "endDate": "2025-05-11",
                                                    "artistNames": [
                                                        "검정치마"
                                                    ]
                                                },
                                                {
                                                    "performanceId": 2,
                                                    "title": "검정치마 단독공연: SONGS TO BRING YOU HOME",
                                                    "posterUrl": "http://www.kopis.or.kr/upload/pfmPoster/PF_PF256780_250108_125004.gif",
                                                    "startDate": "2025-02-07",
                                                    "endDate": "2025-02-09",
                                                    "artistNames": [
                                                        "검정치마"
                                                    ]
                                                }
                                            ]
                                        },
                                        "artists": {
                                            재희형 도대체 아티스트 merge 언제 하실거죠..
                                            "count": 0,
                                            "items": []
                                        }
                                    }
                                    """)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "검색어 누락 또는 유효성 실패",
                    content = @Content(examples = {
                            @ExampleObject(name = "필수 파라미터 누락", value = """
                                    { "status": 400, "message": "query 파라미터는 필수입니다." }
                                    """),
                            @ExampleObject(name = "검색어 공백/빈 값", value = """
                                    { "status": 400, "message": "검색어는 필수 입력값이며, 공백만으로는 검색할 수 없습니다." }
                                    """)
                    })
            ),
            @ApiResponse(responseCode = "404", description = "사용자 없음",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 404, "message": "존재하지 않는 회원입니다." }
                            """))),
            @ApiResponse(responseCode = "401", description = "인증 실패",
                    content = @Content(examples = @ExampleObject("""
                            { "status": 401, "message": "인증 정보가 유효하지 않습니다." }
                            """)))
    })
    @GetMapping
    ResponseEntity<SearchResDto> getSearchResults(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam
            @NotBlank(message = "검색어는 필수 입력값이며, 공백만으로는 검색할 수 없습니다.")
            String query

    );
}