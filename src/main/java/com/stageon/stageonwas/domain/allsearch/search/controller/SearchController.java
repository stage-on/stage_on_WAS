package com.stageon.stageonwas.domain.allsearch.search.controller;

import com.stageon.stageonwas.domain.allsearch.search.dto.SearchResDto;
import com.stageon.stageonwas.domain.allsearch.search.service.SearchService;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
public class SearchController {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<SearchResDto> getSearchResults(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam String query) {

        Long userId = userDetails.getId();
        SearchResDto results = searchService.getSearchResults(userId, query);

        return ResponseEntity.ok(results);
    }
}