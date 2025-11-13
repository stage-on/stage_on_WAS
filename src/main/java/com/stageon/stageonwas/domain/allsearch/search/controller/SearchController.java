package com.stageon.stageonwas.domain.allsearch.search.controller;

import com.stageon.stageonwas.domain.allsearch.search.dto.SearchResDto;
import com.stageon.stageonwas.domain.allsearch.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<SearchResDto> getSearchResults(
            @RequestParam Long userId,
            @RequestParam String query) {

        SearchResDto results = searchService.getSearchResults(userId, query);
        return ResponseEntity.ok(results);
    }
}