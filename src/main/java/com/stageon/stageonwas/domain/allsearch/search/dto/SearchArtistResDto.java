package com.stageon.stageonwas.domain.allsearch.search.dto;

import com.stageon.stageonwas.domain.artist.entity.Artist;
import lombok.Getter;

@Getter
public class SearchArtistResDto {
    private final Long artistId;
    private final String artistName;
    private final String artistPictureUrl;
    private final String artistIntroduction;
    private final String artistMember;

    public SearchArtistResDto(Artist artist) {
        this.artistId = artist.getArtistId();
        this.artistName = artist.getArtistName();
        this.artistPictureUrl = artist.getArtistPictureUrl();
        this.artistIntroduction = artist.getArtistIntroduction();
        this.artistMember = artist.getArtistMember();
    }
}