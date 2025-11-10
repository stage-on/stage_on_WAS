package com.stageon.stageonwas.domain.alonecon.dto;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import lombok.Getter;

// MYCONCERT
@Getter
public class PerformanceLikeResDto {
    private Long performanceId;
    private String title; // prfnm
    private String posterUrl; // poster

    public PerformanceLikeResDto(PerformanceDetail performance) {
        this.performanceId = performance.getId();
        this.title = performance.getPrfnm();
        this.posterUrl = performance.getPoster();
    }
}