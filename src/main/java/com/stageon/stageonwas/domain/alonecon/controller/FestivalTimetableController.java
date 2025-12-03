package com.stageon.stageonwas.domain.alonecon.controller;

import com.stageon.stageonwas.domain.alonecon.dto.PerformanceDetailResponse;
import com.stageon.stageonwas.domain.alonecon.dto.SlotCustomSaveRequest;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.entity.UserSlotCustom;
import com.stageon.stageonwas.domain.alonecon.entity.UserSlotCustomId;
import com.stageon.stageonwas.domain.alonecon.repository.PerformanceDetailRepository;
import com.stageon.stageonwas.domain.alonecon.repository.UserSlotCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/festivals")
@RequiredArgsConstructor
public class FestivalTimetableController {

    private final PerformanceDetailRepository performanceDetailRepository;
    private final UserSlotCustomRepository userSlotCustomRepository;

    // -------------------- 1) 커스텀 저장 (POST) --------------------
    @PostMapping("/{mt20id}/custom-slots")
    public ResponseEntity<Void> saveSlotCustom(
            @PathVariable String mt20id,
            @RequestBody SlotCustomSaveRequest request,
            @RequestParam Long userId   // 🔥 여기! 인증 대신 userId를 직접 받기
    ) {

        PerformanceDetail fes = performanceDetailRepository.findByMt20id(mt20id)
                .orElseThrow(() -> new RuntimeException("공연을 찾을 수 없음 : " + mt20id));

        Long performanceId = fes.getId();

        // 기존 데이터 다 지우고
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

    // -------------------- 2) 타임테이블 + 커스텀 정보 조회 (GET) --------------------
    @GetMapping("/{mt20id}/detail")
    public ResponseEntity<PerformanceDetailResponse> getFestivalDetail(
            @PathVariable String mt20id,
            @RequestParam Long userId    // 🔥 여기도 동일
    ) {

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
