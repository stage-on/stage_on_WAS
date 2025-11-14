package com.stageon.stageonwas.domain.allsearch.recommend.dto;

import lombok.Getter;

@Getter
public class RecommendedResDto {
    private final String keyword;


    public RecommendedResDto(String keyword) {
        this.keyword = keyword;
    }
}
