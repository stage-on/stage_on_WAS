package com.stageon.stageonwas.domain.allsearch.search.controller;

import com.stageon.stageonwas.domain.allsearch.search.api.SearchApi;
import com.stageon.stageonwas.domain.allsearch.search.dto.SearchResDto;
import com.stageon.stageonwas.domain.allsearch.search.service.SearchService;
import com.stageon.stageonwas.security.details.CustomUserDetails;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
@Validated
public class SearchController implements SearchApi {

    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<SearchResDto> getSearchResults(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam
            @NotBlank(message = "검색어는 필수 입력값이며, 공백만으로는 검색할 수 없습니다.")String query) {

        Long userId = userDetails.getId();
        SearchResDto results = searchService.getSearchResults(userId, query);

        return ResponseEntity.ok(results);
    }
}