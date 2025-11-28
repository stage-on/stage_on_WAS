package com.stageon.stageonwas.domain.allsearch.search.dto;

import com.stageon.stageonwas.domain.artist.entity.Artist;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SearchArtistResDto {

    private final Long id;               // 엔티티 id
    private final String bandName;       // 밴드명
    private final String relateUrl;      // 밴드 사진 URL
    private final String sessionMem;     // 구성원
    private final String introBand;      // 소개글
    @Schema(description = "로그인한 유저의 좋아요 여부", example = "true")
    private final boolean isLiked;

    public SearchArtistResDto(Artist artist,boolean isLiked) {
        this.id = artist.getId();
        this.bandName = artist.getBandName();
        this.relateUrl = artist.getRelateUrl();
        this.sessionMem = artist.getSessionMem();
        this.introBand = artist.getIntroBand();
        this.isLiked = isLiked;
    }
}
