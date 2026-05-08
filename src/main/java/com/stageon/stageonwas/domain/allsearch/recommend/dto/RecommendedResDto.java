package com.stageon.stageonwas.domain.allsearch.recommend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendedResDto {
    @Schema(description = "공연명", example = "카더가든 공연")
    private final String keyword;
}
