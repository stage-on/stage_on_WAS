package com.stageon.stageonwas.domain.artist.dto;

import com.stageon.stageonwas.domain.artist.entity.Artist;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "아티스트 좋아요 DTO")
public class ArtistLikeResDto {

    @Schema(description = "아티스트 ID", example = "5")
    private final Long artistId;

    @Schema(description = "아티스트 이름", example = "데이식스 (DAY6)")
    private final String artistName;

    @Schema(description = "아티스트 사진 URL", example = "https://image.example.com/day6.jpg")
    private final String artistPictureUrl;

    public ArtistLikeResDto(Artist artist) {
        this.artistId = artist.getArtistId();
        this.artistName = artist.getArtistName();
        this.artistPictureUrl = artist.getArtistPictureUrl();
    }
}