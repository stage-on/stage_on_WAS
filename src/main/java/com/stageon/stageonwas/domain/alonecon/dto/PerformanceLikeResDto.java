package com.stageon.stageonwas.domain.alonecon.dto;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;

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

    @Schema(description = "공연 시작일", example = "2025-12-20")
    private final LocalDate prfpdfrom; //공연 시작일

    @Schema(description = "공연 종료일", example = "2025-12-21")
    private final LocalDate prfpdto; //공연 종료일

    @Schema(description = "공연 장소", example = "일산 킨텍스")
    private final String fcltynm; //공연 장소

    @Schema(description = "mt20id", example = "PF123")
    private final String mt20id;

    public PerformanceLikeResDto(PerformanceDetail performance) {
        this.performanceId = performance.getId();
        this.title = performance.getPrfnm();
        this.posterUrl = performance.getPoster();
        this.prfpdfrom = performance.getPrfpdfrom();
        this.prfpdto = performance.getPrfpdto();
        this.fcltynm = performance.getFcltynm();
        this.mt20id = performance.getMt20id();
    }
}