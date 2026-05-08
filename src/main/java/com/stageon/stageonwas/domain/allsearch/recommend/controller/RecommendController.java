package com.stageon.stageonwas.domain.allsearch.recommend.controller;


import com.stageon.stageonwas.domain.allsearch.recommend.api.RecommendApi;
import com.stageon.stageonwas.domain.allsearch.recommend.dto.RecommendedResDto;
import com.stageon.stageonwas.domain.allsearch.recommend.service.RecommendService;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommend")
public class RecommendController implements RecommendApi {

    private final RecommendService recommendService;

    @GetMapping
    public ResponseEntity<List<RecommendedResDto>> getRecommendedKeywords(@AuthenticationPrincipal CustomUserDetails userDetails) {

        List<RecommendedResDto> recommendedKeywords = recommendService.getRecommendedKeywords();
        return ResponseEntity.ok(recommendedKeywords);
    }
}