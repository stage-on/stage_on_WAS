package com.stageon.stageonwas.domain.allsearch.history.controller;

import com.stageon.stageonwas.domain.allsearch.history.dto.SearchHistoryResDto;
import com.stageon.stageonwas.domain.allsearch.history.service.SearchHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchHistoryController {
    private final SearchHistoryService searchHistoryService;

    // 검색기록 목록 조회
    @GetMapping({"/history"})
    public ResponseEntity<List<SearchHistoryResDto>> getRecentSearches(@RequestParam Long userId) {
        List<SearchHistoryResDto> recentSearches = searchHistoryService.getRecentSearches(userId);
        return ResponseEntity.ok(recentSearches);
    }

    // 검색기록 삭제
    @DeleteMapping("/history/{historyId}")
    public ResponseEntity<String> deleteSearchHistory(
            @RequestParam Long userId,
            @PathVariable Long historyId) {

        searchHistoryService.deleteSearchHistory(userId, historyId);
        return ResponseEntity.ok("검색 기록 삭제 성공");
    }
}
