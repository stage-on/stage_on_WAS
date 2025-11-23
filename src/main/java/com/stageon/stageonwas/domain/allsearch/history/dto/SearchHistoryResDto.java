package com.stageon.stageonwas.domain.allsearch.history.dto;

import com.stageon.stageonwas.domain.allsearch.history.entity.SearchHistory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "검색 기록DTO")
public class SearchHistoryResDto {
    @Schema(description = "검색 기록 ID", example = "1")
    private final Long id;
    @Schema(description = "검색한 키워드", example = "검정치마")
    private final String keyword;

    public SearchHistoryResDto(SearchHistory history) {
        this.id = history.getId();
        this.keyword = history.getKeyword();
    }
}