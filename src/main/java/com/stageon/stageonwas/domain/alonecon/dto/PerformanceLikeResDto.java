package com.stageon.stageonwas.domain.alonecon.dto;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

// MYCONCERT
@Schema(description = "공연 좋아요 DTO")
@Getter
public class PerformanceLikeResDto {
    @Schema(description = "공연ID", example = "1")
    private final Long performanceId;

    @Schema(description = "공연제목", example = "검정치마 단독공연")
    private final String title; // prfnm

    @Schema(description = "포스터 URL", example = "https://~")
    private final String posterUrl; // poster

    public PerformanceLikeResDto(PerformanceDetail performance) {
        this.performanceId = performance.getId();
        this.title = performance.getPrfnm();
        this.posterUrl = performance.getPoster();
    }
}