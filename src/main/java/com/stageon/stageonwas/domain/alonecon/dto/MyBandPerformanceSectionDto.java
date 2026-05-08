package com.stageon.stageonwas.domain.alonecon.dto;

import com.stageon.stageonwas.domain.artist.entity.Artist;
import lombok.Getter;

import java.util.List;

@Getter
public class MyBandPerformanceSectionDto {

    private final Long artistId;
    private final String artistName;
    private final String artistImageUrl;
    private final List<PerformancePeriodDto> performances;

    public MyBandPerformanceSectionDto(Artist artist, List<PerformancePeriodDto> performances) {
        this.artistId = artist.getId();
        this.artistName = artist.getBandName();
        this.artistImageUrl = artist.getRelateUrl();
        this.performances = performances;
    }
}
