package com.stageon.stageonwas.domain.allsearch.history.dto;

import com.stageon.stageonwas.domain.allsearch.history.entity.SearchHistory;
import lombok.Getter;

@Getter
public class SearchHistoryResDto {
    private final Long id;
    private final String keyword;

    public SearchHistoryResDto(SearchHistory history) {
        this.id = history.getId();
        this.keyword = history.getKeyword();
    }
}