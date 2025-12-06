package com.stageon.stageonwas.domain.alonecon.controller;

import com.stageon.stageonwas.domain.alonecon.api.FestivalTimetableApi;
import com.stageon.stageonwas.domain.alonecon.dto.PerformanceDetailResponse;
import com.stageon.stageonwas.domain.alonecon.dto.SlotCustomSaveRequest;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.entity.UserSlotCustom;
import com.stageon.stageonwas.domain.alonecon.entity.UserSlotCustomId;
import com.stageon.stageonwas.domain.alonecon.repository.PerformanceDetailRepository;
import com.stageon.stageonwas.domain.alonecon.repository.UserSlotCustomRepository;
import com.stageon.stageonwas.security.details.CustomUserDetails;
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
public class FestivalTimetableController implements FestivalTimetableApi {

    private final PerformanceDetailRepository performanceDetailRepository;
    private final UserSlotCustomRepository userSlotCustomRepository;

    @Override
    @GetMapping("/custom")
    public ResponseEntity<List<PerformanceDetail>> getMyCustomFestivals(
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

    @Override
    @GetMapping("/{mt20id}/my-detail")
    public ResponseEntity<PerformanceDetailResponse> getMyFestivalDetail(
            @PathVariable String mt20id,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUser().getUserId();

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

    @Override
    @PostMapping("/{mt20id}/custom-slots")
    @Transactional
    public ResponseEntity<Void> saveSlotCustom(
            @PathVariable String mt20id,
            @RequestBody SlotCustomSaveRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUser().getUserId();

        PerformanceDetail fes = performanceDetailRepository.findByMt20id(mt20id)
                .orElseThrow(() -> new RuntimeException("공연을 찾을 수 없음 : " + mt20id));

        Long performanceId = fes.getId();

        userSlotCustomRepository.deleteByIdUserIdAndIdPerformanceId(userId, performanceId);

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

    @Override
    @GetMapping("/{mt20id}/detail")
    public ResponseEntity<PerformanceDetailResponse> getFestivalDetail(
            @PathVariable String mt20id,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUser().getUserId();

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
