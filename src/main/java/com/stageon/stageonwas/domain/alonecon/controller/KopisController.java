package com.stageon.stageonwas.domain.alonecon.controller;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/kopis")
@RequiredArgsConstructor
public class KopisController {

    private final KopisService svc;
    private final PerformanceDetailRepository performanceRepo;

    private final MyBandPerformanceService myBandPerformanceService;
    // ===========================
// 🎤 아티스트 이름 검색 - 기간 DTO 전용
// ===========================

    @GetMapping("/performances/my-bands/sections")
    public ResponseEntity<List<MyBandPerformanceSectionDto>> getMyBandPerformanceSections(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getId();
        List<MyBandPerformanceSectionDto> sections =
                myBandPerformanceService.getMyBandPerformances(userId);

        return ResponseEntity.ok(sections);
    }

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

    /** 로컬 단건 상세 */
    @GetMapping("/performances/detail/{mt20id}")
    public ResponseEntity<PerformanceDetail> getLocalDetail(@PathVariable("mt20id") String mt20id) {
        return performanceRepo.findByMt20id(mt20id.trim())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
