package com.stageon.stageonwas.domain.alonecon.controller;

import com.stageon.stageonwas.domain.alonecon.dto.PerformanceDetailResponse;
import com.stageon.stageonwas.domain.alonecon.dto.SlotCustomSaveRequest;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.entity.UserSlotCustom;
import com.stageon.stageonwas.domain.alonecon.entity.UserSlotCustomId;
import com.stageon.stageonwas.domain.alonecon.repository.PerformanceDetailRepository;
import com.stageon.stageonwas.domain.alonecon.repository.UserSlotCustomRepository;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/festivals")
@RequiredArgsConstructor
@Tag(name = "Festival Timetable", description = "페스티벌 타임테이블 / 커스텀 관련 API")
public class FestivalTimetableController {

    private final PerformanceDetailRepository performanceDetailRepository;
    private final UserSlotCustomRepository userSlotCustomRepository;

    // ==================== 1) 내가 커스텀한 페스티벌 목록 ====================
    @GetMapping("/custom")
    @Operation(
            summary = "내가 커스텀한 페스티벌 목록 조회",
            description = "유저가 타임테이블을 한 번이라도 커스텀한 페스티벌(performance) 목록을 반환합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "커스텀한 페스티벌 목록",
            content = @Content(schema = @Schema(implementation = PerformanceDetail.class))
    )
    public ResponseEntity<List<PerformanceDetail>> getMyCustomFestivals(
            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUser().getUserId();

        List<Long> performanceIds =
                userSlotCustomRepository.findDistinctPerformanceIdsByUserId(userId);

        if (performanceIds.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }

        List<PerformanceDetail> festivals =
                performanceDetailRepository.findAllById(performanceIds);

        return ResponseEntity.ok(festivals);
    }

    @GetMapping("/{mt20id}/my-detail")
    @Operation(
            summary = "로그인 유저 기준 페스티벌 상세 + 슬롯 반전 여부 조회",
            description = """
                seed로 저장된 페스티벌 타임테이블(slots)에
                현재 로그인한 유저의 커스텀 정보(색상 반전 여부)가 있으면 합쳐서 반환합니다.
                (커스텀이 없으면 seed 그대로 반환)
                """
    )
    @ApiResponse(
            responseCode = "200",
            description = "상세 정보 + inverted 정보",
            content = @Content(schema = @Schema(implementation = PerformanceDetailResponse.class))
    )
    public ResponseEntity<PerformanceDetailResponse> getMyFestivalDetail(
            @Parameter(description = "KOPIS 공연 ID", example = "PF263558")
            @PathVariable String mt20id,

            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails   // ✅ 토큰에서 유저 정보
    ) {
        // 1) 로그인 유저 ID
        Long userId = userDetails.getUser().getUserId();

        // 2) 공연 기본 정보 (seed)
        PerformanceDetail fes = performanceDetailRepository.findByMt20id(mt20id)
                .orElseThrow(() -> new RuntimeException("공연을 찾을 수 없음 : " + mt20id));

        Long performanceId = fes.getId();

        // 3) 이 유저가 이 공연에 대해 저장한 커스텀 슬롯들
        List<UserSlotCustom> customs =
                userSlotCustomRepository.findByIdUserIdAndIdPerformanceId(userId, performanceId);

        // 4) (날짜 + stageOrder + artist) → inverted 여부로 맵핑
        Map<String, Boolean> invertedMap = customs.stream()
                .collect(Collectors.toMap(
                        c -> PerformanceDetailResponse.slotKey(
                                c.getId().getSlotDate(),
                                c.getId().getStageOrder(),
                                c.getId().getArtist()
                        ),
                        UserSlotCustom::isInverted
                ));

        // 5) seed + inverted 정보를 합쳐서 DTO 생성
        PerformanceDetailResponse response =
                PerformanceDetailResponse.from(fes, invertedMap);   // ✅ 기존 메서드 그대로 사용

        return ResponseEntity.ok(response);
    }

    // ==================== 2) 커스텀 저장 (POST) ====================
    @PostMapping("/{mt20id}/custom-slots")
    @Transactional
    @Operation(
            summary = "타임테이블 커스텀 저장",
            description = """
                특정 페스티벌(mt20id)에 대해 유저가 선택한 슬롯(색상 반전 여부)을 저장합니다.
                같은 공연에 대해 기존에 저장된 커스텀 데이터는 모두 삭제한 뒤, 새로 저장합니다.
                """
    )
    @ApiResponse(
            responseCode = "200",
            description = "저장 성공 (body 없음)"
    )
    public ResponseEntity<Void> saveSlotCustom(
            @Parameter(description = "KOPIS 공연 ID", example = "PF263558")
            @PathVariable String mt20id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "반전된 슬롯 목록",
                    required = true,
                    content = @Content(schema = @Schema(implementation = SlotCustomSaveRequest.class))
            )
            @RequestBody SlotCustomSaveRequest request,

            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {

        Long userId = userDetails.getUser().getUserId();   // 🔥 여기서 userId 가져옴

        PerformanceDetail fes = performanceDetailRepository.findByMt20id(mt20id)
                .orElseThrow(() -> new RuntimeException("공연을 찾을 수 없음 : " + mt20id));

        Long performanceId = fes.getId();

        // 기존 데이터 삭제
        userSlotCustomRepository.deleteByIdUserIdAndIdPerformanceId(userId, performanceId);

        // 새로 저장
        if (request.getInvertedSlots() != null && !request.getInvertedSlots().isEmpty()) {
            List<UserSlotCustom> entities = request.getInvertedSlots().stream()
                    .map(s -> UserSlotCustom.builder()
                            .id(UserSlotCustomId.builder()
                                    .userId(userId)
                                    .performanceId(performanceId)
                                    .slotDate(s.getDate())
                                    .stageOrder(s.getStageOrder())
                                    .artist(s.getArtist())
                                    .build())
                            .inverted(s.isInverted())
                            .build())
                    .toList();

            userSlotCustomRepository.saveAll(entities);
        }

        return ResponseEntity.ok().build();
    }


    // ==================== 3) 상세 조회 + 커스텀 정보 ====================
    @GetMapping("/{mt20id}/detail")
    @Operation(
            summary = "페스티벌 상세 + 슬롯 반전 여부 조회",
            description = """
                    seed로 저장된 페스티벌 타임테이블(slots)에  
                    해당 유저의 커스텀 정보(색상 반전 여부)를 합쳐서 반환합니다.
                    """
    )
    @ApiResponse(
            responseCode = "200",
            description = "상세 정보 + inverted 정보",
            content = @Content(schema = @Schema(implementation = PerformanceDetailResponse.class))
    )
    public ResponseEntity<PerformanceDetailResponse> getFestivalDetail(
            @Parameter(description = "KOPIS 공연 ID", example = "PF263558")
            @PathVariable String mt20id,

            @Parameter(hidden = true)
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUser().getUserId();   // 🔥 여기서 가져오기

        PerformanceDetail fes = performanceDetailRepository.findByMt20id(mt20id)
                .orElseThrow(() -> new RuntimeException("공연을 찾을 수 없음 : " + mt20id));

        Long performanceId = fes.getId();

        List<UserSlotCustom> customs =
                userSlotCustomRepository.findByIdUserIdAndIdPerformanceId(userId, performanceId);

        Map<String, Boolean> invertedMap = customs.stream()
                .collect(Collectors.toMap(
                        c -> PerformanceDetailResponse.slotKey(
                                c.getId().getSlotDate(),
                                c.getId().getStageOrder(),
                                c.getId().getArtist()
                        ),
                        UserSlotCustom::isInverted
                ));

        PerformanceDetailResponse response =
                PerformanceDetailResponse.from(fes, invertedMap);

        return ResponseEntity.ok(response);
    }
}
