package com.stageon.stageonwas.domain.alonecon.controller;

import com.stageon.stageonwas.domain.alonecon.api.KopisApi;
import com.stageon.stageonwas.domain.alonecon.dto.MyBandPerformanceSectionDto;
import com.stageon.stageonwas.domain.alonecon.dto.PerformanceFestivalDto;
import com.stageon.stageonwas.domain.alonecon.dto.PerformancePeriodDto;
import com.stageon.stageonwas.domain.alonecon.dto.PerformanceSimpleDto;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.repository.PerformanceDetailRepository;
import com.stageon.stageonwas.domain.alonecon.repository.UserPerformanceLikeRepository;
import com.stageon.stageonwas.domain.alonecon.service.KopisService;
import com.stageon.stageonwas.domain.alonecon.service.MyBandPerformanceService;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Validated
@RestController
@RequestMapping("/api/v1/kopis")
@RequiredArgsConstructor
public class KopisController implements KopisApi {

    private final KopisService svc;
    private final PerformanceDetailRepository performanceRepo;
    private final UserPerformanceLikeRepository userPerformanceLikeRepository;
    private final MyBandPerformanceService myBandPerformanceService;

    @Override
    @GetMapping("/performances/festivals")
    public ResponseEntity<List<PerformanceFestivalDto>> getAllFestivals(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        List<PerformanceDetail> festivals =
                performanceRepo.findByTypeofcon(2);

        if (festivals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Long userId = (userDetails == null) ? null : userDetails.getId();

        Set<Long> likedIdSet = new HashSet<>();
        if (userId != null) {
            List<Long> likedIds =
                    userPerformanceLikeRepository.findPerformanceIdsByUserId(userId);
            likedIdSet.addAll(likedIds);
        }

        List<PerformanceFestivalDto> result = festivals.stream()
                .map(p -> new PerformanceFestivalDto(
                        p,
                        likedIdSet.contains(p.getId())
                ))
                .toList();

        return ResponseEntity.ok(result);
    }

    @Override
    @GetMapping("/performances/my-bands/sections")
    public ResponseEntity<List<MyBandPerformanceSectionDto>> getMyBandPerformanceSections(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getId();
        List<MyBandPerformanceSectionDto> sections =
                myBandPerformanceService.getMyBandPerformances(userId);

        return ResponseEntity.ok(sections);
    }


    @Override
    @GetMapping("/random5")
    public List<PerformanceDetail> getRandom5Performances() {
        List<PerformanceDetail> all = performanceRepo.findAll();
        Collections.shuffle(all);
        return all.stream()
                .limit(5)
                .toList();
    }


    @Override
    @GetMapping("/performances/search/artist/period")
    public ResponseEntity<List<PerformancePeriodDto>> searchArtistPeriod(
            @RequestParam("q") @NotBlank String keyword,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        String q = keyword.trim();

        List<PerformanceDetail> list =
                performanceRepo.findByArtistNameAnywhere(q);

        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        Long userId = userDetails != null ? userDetails.getId() : null;
        Set<Long> likedIdSet = new HashSet<>();

        if (userId != null) {
            likedIdSet.addAll(userPerformanceLikeRepository.findPerformanceIdsByUserId(userId));
        }

        List<PerformancePeriodDto> result = list.stream()
                .map(p -> new PerformancePeriodDto(p, likedIdSet.contains(p.getId())))
                .toList();

        return ResponseEntity.ok(result);
    }



    @Override
    @GetMapping("/performances/search/artist")
    public ResponseEntity<List<?>> searchArtist(
            @RequestParam("q") String keyword,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        List<PerformanceDetail> list =
                performanceRepo.findByArtistNameAnywhere(keyword.trim());


        Long userId = (userDetails == null) ? null : userDetails.getId();
        Set<Long> likedIdSet = new HashSet<>();
        if (userId != null) {
            likedIdSet.addAll(userPerformanceLikeRepository.findPerformanceIdsByUserId(userId));
        }


        List<?> result = list.stream()
                .map(p -> {
                    Integer t = p.getTypeofcon();
                    boolean liked = likedIdSet.contains(p.getId());

                    if (t != null && t == 1) {
                        return new PerformanceSimpleDto(p, liked);      // ✅ 새 생성자
                    } else {
                        return new PerformanceFestivalDto(p, liked);    // ✅ 새 생성자
                    }
                })
                .toList();

        return ResponseEntity.ok(result);
    }



    @Override
    @GetMapping("/performances/search")
    public ResponseEntity<List<PerformanceDetail>> searchByPrfnm(
            @RequestParam("q") @NotBlank String keyword,
            @AuthenticationPrincipal CustomUserDetails userDetails   // ✅ 추가
    ) {
        String q = keyword.trim();
        List<PerformanceDetail> results =
                performanceRepo.findTop50ByPrfnmContainingIgnoreCaseOrderByPrfpdfromDesc(q);

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // ▼ 로그인 유저의 좋아요 공연 id 들
        Long userId = (userDetails == null) ? null : userDetails.getId();

        Set<Long> likedIdSet = new HashSet<>();
        if (userId != null) {
            likedIdSet.addAll(userPerformanceLikeRepository.findPerformanceIdsByUserId(userId));
        }

        // ▼ 엔티티에 liked 세팅
        results.forEach(p -> p.setLiked(likedIdSet.contains(p.getId())));

        return ResponseEntity.ok(results);
    }


    @Override
    @GetMapping("/performances/detail/{mt20id}")
    public ResponseEntity<PerformanceDetail> getLocalDetail(
            @PathVariable("mt20id") String mt20id,
            @AuthenticationPrincipal CustomUserDetails userDetails   // ✅ 추가
    ) {
        Long userId = (userDetails == null) ? null : userDetails.getId();

        Set<Long> likedIdSet = new HashSet<>();
        if (userId != null) {
            likedIdSet.addAll(userPerformanceLikeRepository.findPerformanceIdsByUserId(userId));
        }

        return performanceRepo.findByMt20id(mt20id.trim())
                .map(p -> {
                    if (userId != null) {
                        p.setLiked(likedIdSet.contains(p.getId()));   // ✅ liked 세팅
                    }
                    return ResponseEntity.ok(p);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
