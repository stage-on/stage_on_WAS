package com.stageon.stageonwas.domain.alonecon.dto;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ArtistPicResponse {

    private LocalDate date;
    private String relatenm;
    private String url;

    public static ArtistPicResponse from(PerformanceDetail.ArtistPic src) {
        return new ArtistPicResponse(src.getDate(), src.getRelatenm(), src.getUrl());
    }
}
