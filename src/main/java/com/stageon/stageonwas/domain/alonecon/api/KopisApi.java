package com.stageon.stageonwas.domain.alonecon.api;

import com.stageon.stageonwas.domain.alonecon.dto.MyBandPerformanceSectionDto;
import com.stageon.stageonwas.domain.alonecon.dto.PerformanceFestivalDto;
import com.stageon.stageonwas.domain.alonecon.dto.PerformancePeriodDto;
import com.stageon.stageonwas.domain.alonecon.dto.PerformanceSimpleDto;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(
        name = "KOPIS 공연 조회 API",
        description = "KOPIS에서 수집한 공연/페스티벌 정보를 로컬 DB에서 조회하는 API 모음"
)
public interface KopisApi {

    // =============== 1) 페스티벌 전체 조회 ===============
    @Operation(
            summary = "페스티벌(타입 2) 공연만 전체 조회",
            description = """
                    로컬 DB에 저장된 공연 중에서 typeofcon = 2 인 것만 모아서 반환합니다.
                    (즉, 페스티벌 전용 리스트)
                    
                    - 프론트에서는 PerformanceFestivalDto 를 사용하면 됩니다.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "페스티벌 목록 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PerformanceFestivalDto.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "페스티벌 데이터 없음",
                    content = @Content
            )
    })
    ResponseEntity<List<PerformanceFestivalDto>> getAllFestivals(
            @Parameter(hidden = true) CustomUserDetails userDetails
    );

    // =============== 2) 내가 좋아요한 밴드 공연 섹션 ===============
    @Operation(
            summary = "내가 좋아요한 밴드 공연 섹션 조회",
            description = """
                    현재 로그인한 사용자가 '좋아요'한 밴드들의 공연 정보를
                    여러 섹션(예: '이번 주', '다가오는 공연', '마감 임박' 등)으로 묶어서 반환합니다.
                    
                    - `Authorization: Bearer {accessToken}` 헤더 필요
                    - 유저 정보는 `@AuthenticationPrincipal CustomUserDetails`에서 가져옵니다.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "섹션별 공연 정보 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MyBandPerformanceSectionDto.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "인증 실패 (토큰 없음 또는 만료됨)",
                    content = @Content
            )
    })
    ResponseEntity<List<MyBandPerformanceSectionDto>> getMyBandPerformanceSections(
            @Parameter(hidden = true) CustomUserDetails userDetails
    );

    // =============== 3) 랜덤 공연 5개 ===============
    @Operation(
            summary = "랜덤 공연 5개 조회",
            description = """
                    로컬 DB에 저장된 공연/페스티벌 정보 중에서
                    임의로 5개를 랜덤 추출하여 반환합니다.
                    
                    - 추천 영역이나 메인 페이지 '랜덤 공연' 등에서 활용 가능합니다.
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "랜덤 공연 목록 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PerformanceDetail.class))
                    )
            )
    })
    List<PerformanceDetail> getRandom5Performances();

    @Operation(
            summary = "아티스트 이름으로 공연 기간 정보 검색",
            description = """
                아티스트 이름으로 공연을 검색하고,
                기간 정보에 특화된 `PerformancePeriodDto` 리스트를 반환합니다.
                
                - 내부적으로 `performanceRepo.findByArtistNameAnywhere(q)` 호출
                - 공연이 하나도 없으면 `204 No Content` 반환
                """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "검색 성공 - 공연 기간 DTO 리스트 반환",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PerformancePeriodDto.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "검색 결과 없음",
                    content = @Content
            )
    })
    ResponseEntity<List<PerformancePeriodDto>> searchArtistPeriod(
            @Parameter(description = "검색 키워드", example = "잔나비")
            @NotBlank String keyword,

            @Parameter(hidden = true)
            CustomUserDetails userDetails
    );


    // =============== 5) 아티스트 이름으로 공연/페스티벌 검색 ===============
    @Operation(
            summary = "아티스트 이름으로 공연/페스티벌 검색 (타입별 DTO로 반환)",
            description = """
                    아티스트 이름으로 공연들을 검색한 뒤,
                    `typeofcon` 값에 따라 서로 다른 DTO로 반환합니다.
                    
                    - `typeofcon == 1` : 일반 공연 → `PerformanceSimpleDto`
                    - 그 외(2 등)     : 페스티벌 → `PerformanceFestivalDto`
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "검색 성공 - 공연/페스티벌 DTO 리스트 반환",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(anyOf = {
                                    PerformanceSimpleDto.class,
                                    PerformanceFestivalDto.class
                            }))
                    )
            )
    })
    ResponseEntity<List<?>> searchArtist(
            @Parameter(description = "검색 키워드", example = "새소년") String keyword,
            @Parameter(hidden = true) CustomUserDetails userDetails
    );

    // =============== 6) 공연명 검색 ===============
    @Operation(
            summary = "공연명으로 공연 검색",
            description = """
                    공연 제목(`prfnm`)에 특정 키워드가 포함된 공연을 검색합니다.
                    
                    - 내부적으로 `findTop50ByPrfnmContainingIgnoreCaseOrderByPrfpdfromDesc(q)` 사용
                    - 최대 50개까지 반환
                    - 검색 결과가 없으면 `204 No Content` 반환
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "검색 성공 - 공연 엔티티 리스트 반환",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PerformanceDetail.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "204",
                    description = "검색 결과 없음",
                    content = @Content
            )
    })
    ResponseEntity<List<PerformanceDetail>> searchByPrfnm(
            @NotBlank String keyword,
            @Parameter(hidden = true) CustomUserDetails userDetails
    );


    // =============== 7) 단건 상세 조회 ===============
    @Operation(
            summary = "로컬 DB 공연 단건 상세 조회",
            description = """
                    KOPIS 공연 ID(`mt20id`)로 로컬 DB에 저장된 공연 상세 정보를 조회합니다.
                    
                    - KOPIS API로부터 이미 수집/저장된 공연만 조회 가능
                    - 존재하지 않는 경우 `404 Not Found` 반환
                    """
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "상세 정보 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PerformanceDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "해당 mt20id 공연을 찾을 수 없음",
                    content = @Content
            )
    })
    ResponseEntity<PerformanceDetail> getLocalDetail(
            @Parameter(description = "KOPIS 공연 ID", example = "PF123456") String mt20id,
            @Parameter(hidden = true) CustomUserDetails userDetails
    );

}
