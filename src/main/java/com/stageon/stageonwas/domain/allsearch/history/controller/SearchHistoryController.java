package com.stageon.stageonwas.domain.allsearch.history.controller;

import com.stageon.stageonwas.domain.allsearch.history.dto.SearchHistoryResDto;
import com.stageon.stageonwas.domain.allsearch.history.service.SearchHistoryService;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal; // 👈 @AuthenticationPrincipal import
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchHistoryController {

    private final SearchHistoryService searchHistoryService;

    @GetMapping({"/history"})
    public ResponseEntity<List<SearchHistoryResDto>> getRecentSearches(
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getId();

        List<SearchHistoryResDto> recentSearches = searchHistoryService.getRecentSearches(userId);
        return ResponseEntity.ok(recentSearches);
    }

    @DeleteMapping("/history/{historyId}")
    public ResponseEntity<String> deleteSearchHistory(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable Long historyId) {

        Long userId = userDetails.getId();

        searchHistoryService.deleteSearchHistory(userId, historyId);
        return ResponseEntity.ok("검색 기록 삭제 성공");
    }
}