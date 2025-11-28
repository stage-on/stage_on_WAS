package com.stageon.stageonwas.domain.allsearch.search.dto;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SearchPerformanceResDto {
    private final Long performanceId;
    private final String title;
    private final String posterUrl;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<String> artistNames;
    @Schema(description = "로그인한 유저의 좋아요 여부", example = "true")
    private final boolean isLiked;

    public SearchPerformanceResDto(PerformanceDetail performance,boolean isLiked) {
        this.performanceId = performance.getId();
        this.title = performance.getPrfnm();
        this.posterUrl = performance.getPoster();
        this.startDate = performance.getPrfpdfrom();
        this.endDate = performance.getPrfpdto();
        this.artistNames = performance.getStyurls().stream()
                .map(PerformanceDetail.ArtPic::getRelatenm)
                .collect(Collectors.toList());
        this.isLiked = isLiked;
    }
}