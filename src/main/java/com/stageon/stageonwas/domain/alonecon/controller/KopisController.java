package com.stageon.stageonwas.domain.alonecon.controller;
import com.stageon.stageonwas.domain.alonecon.repository.UserPerformanceLikeRepository;
import com.stageon.stageonwas.domain.alonecon.service.MyBandPerformanceService;
import com.stageon.stageonwas.domain.alonecon.dto.MyBandPerformanceSectionDto;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.stageon.stageonwas.domain.alonecon.dto.PerformanceFestivalDto;
import com.stageon.stageonwas.domain.alonecon.dto.PerformancePeriodDto;
import com.stageon.stageonwas.domain.alonecon.dto.PerformanceSimpleDto;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.repository.PerformanceDetailRepository;
import com.stageon.stageonwas.domain.alonecon.service.KopisService;
import com.stageon.stageonwas.domain.alonecon.service.MyBandPerformanceService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.*;

// ===== Swagger/OpenAPI =====
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@Validated
@RestController
@RequestMapping("/api/v1/kopis")
@RequiredArgsConstructor
@Tag(
        name = "KOPIS 공연 조회 API",
        description = "KOPIS에서 수집한 공연/페스티벌 정보를 로컬 DB에서 조회하는 API 모음"
)
public class KopisController {

    private final KopisService svc;
    private final PerformanceDetailRepository performanceRepo;
    private final UserPerformanceLikeRepository userPerformanceLikeRepository;

    private final MyBandPerformanceService myBandPerformanceService;
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

    @GetMapping("/performances/festivals")
    public ResponseEntity<List<PerformanceFestivalDto>> getAllFestivals(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        // 1) typeofcon = 2 인 것만 조회
        List<PerformanceDetail> festivals =
                performanceRepo.findByTypeofcon(2);

        if (festivals.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }

        // 2) 로그인 유저 id (비로그인일 수 있으니 null 체크)
        Long userId = (userDetails == null) ? null : userDetails.getId();

        // 3) 이 유저가 좋아요한 공연 id 목록 조회
        Set<Long> likedIdSet = new HashSet<>();
        if (userId != null) {
            List<Long> likedIds =
                    userPerformanceLikeRepository.findPerformanceIdsByUserId(userId);
            likedIdSet.addAll(likedIds);
        }

        // 4) 엔티티 → DTO 변환 + liked 세팅
        List<PerformanceFestivalDto> result = festivals.stream()
                .map(p -> new PerformanceFestivalDto(
                        p,
                        likedIdSet.contains(p.getId())   // 🔥 여기서 true/false 결정
                ))
                .toList();

        return ResponseEntity.ok(result);
    }


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
    @GetMapping("/performances/my-bands/sections")
    public ResponseEntity<List<MyBandPerformanceSectionDto>> getMyBandPerformanceSections(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getId();
        List<MyBandPerformanceSectionDto> sections =
                myBandPerformanceService.getMyBandPerformances(userId);

        return ResponseEntity.ok(sections);
    }
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
    @GetMapping("/random5")
    public List<PerformanceDetail> getRandom5Performances() {

        // 1. 모든 공연 정보 가져오기
        List<PerformanceDetail> all = performanceRepo.findAll();

        // 2. 랜덤 섞기
        Collections.shuffle(all);

        // 3. 5개만 반환
        return all.stream()
                .limit(5)
                .toList();
    }
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
    @GetMapping("/performances/search/artist/period")
    public ResponseEntity<List<PerformancePeriodDto>> searchArtistPeriod(
            @RequestParam("q") @NotBlank String keyword) {

        String q = keyword.trim();

        List<PerformanceDetail> list =
                performanceRepo.findByArtistNameAnywhere(q);

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }

        List<PerformancePeriodDto> result = list.stream()
                .map(PerformancePeriodDto::new)   // 엔티티 → DTO (생성자 활용)
                .toList();

        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "아티스트 이름으로 공연/페스티벌 검색 (타입별 DTO로 반환)",
            description = """
                    아티스트 이름으로 공연들을 검색한 뒤,
                    `typeofcon` 값에 따라 서로 다른 DTO로 반환합니다.
                    
                    - `typeofcon == 1` : 일반 공연 → `PerformanceSimpleDto`
                    - 그 외(2 등)     : 페스티벌 → `PerformanceFestivalDto`
                    
                    프론트에서는 각 요소의 필드를 확인해서 어떤 DTO 타입인지 구분해서 사용하면 됩니다.
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
    @GetMapping("/performances/search/artist")
    public ResponseEntity<List<?>> searchArtist(@RequestParam("q") String keyword) {

        List<PerformanceDetail> list =
                performanceRepo.findByArtistNameAnywhere(keyword.trim());

        List<?> result = list.stream()
                .map(p -> {
                    Integer t = p.getTypeofcon();
                    if (t != null && t == 1) {
                        // 공연용: 페스티벌 상세 제외 DTO
                        return new PerformanceSimpleDto(p);
                    } else {
                        // 페스티벌용: 상세 전부 포함 DTO
                        return new PerformanceFestivalDto(p);
                    }
                })
                .toList();

        return ResponseEntity.ok(result);
    }

    // ==============================
    // 🎵 로컬 DB 검색 컨트롤러 (예전 그대로)
    // ==============================
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
    /** 공연명 검색 */
    @GetMapping("/performances/search")
    public ResponseEntity<List<PerformanceDetail>> searchByPrfnm(@RequestParam("q") @NotBlank String keyword) {
        String q = keyword.trim();
        List<PerformanceDetail> results =
                performanceRepo.findTop50ByPrfnmContainingIgnoreCaseOrderByPrfpdfromDesc(q);

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.ok(results);
    }
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
    /** 로컬 단건 상세 */
    @GetMapping("/performances/detail/{mt20id}")
    public ResponseEntity<PerformanceDetail> getLocalDetail(@PathVariable("mt20id") String mt20id) {
        return performanceRepo.findByMt20id(mt20id.trim())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
