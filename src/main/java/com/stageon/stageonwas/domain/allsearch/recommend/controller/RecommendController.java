package com.stageon.stageonwas.domain.allsearch.recommend.controller;


import com.stageon.stageonwas.domain.allsearch.recommend.dto.RecommendedResDto;
import com.stageon.stageonwas.domain.allsearch.recommend.service.RecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    @GetMapping
    public ResponseEntity<List<RecommendedResDto>> getRecommendedKeywords() {
        List<RecommendedResDto> recommendedKeywords = recommendService.getRecommendedKeywords();
        return ResponseEntity.ok(recommendedKeywords);
    }
}