package com.stageon.stageonwas.domain.allsearch.search.dto;

import com.stageon.stageonwas.domain.artist.entity.Artist;
import lombok.Getter;

@Getter
public class SearchArtistResDto {

    private final Long id;               // 엔티티 id
    private final String bandName;       // 밴드명
    private final String relateUrl;      // 밴드 사진 URL
    private final String sessionMem;     // 구성원
    private final String introBand;      // 소개글

    public SearchArtistResDto(Artist artist) {
        this.id = artist.getId();
        this.bandName = artist.getBandName();
        this.relateUrl = artist.getRelateUrl();
        this.sessionMem = artist.getSessionMem();
        this.introBand = artist.getIntroBand();
    }
}
