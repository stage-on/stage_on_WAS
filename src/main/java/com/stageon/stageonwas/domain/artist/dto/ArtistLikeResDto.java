package com.stageon.stageonwas.domain.artist.dto;

import com.stageon.stageonwas.domain.artist.entity.Artist;
import lombok.Getter;

@Getter
// MYBAND 조회시
public class ArtistLikeResDto {
    private Long artistId;
    private String artistName;
    private String artistPictureUrl;

    public ArtistLikeResDto(Artist artist) {
        this.artistId = artist.getArtistId();
        this.artistName = artist.getArtistName();
        this.artistPictureUrl = artist.getArtistPictureUrl();
    }
}
