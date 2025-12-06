package com.stageon.stageonwas.domain.artist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class ArtistSelectReqDto {
    @Schema(description = "아티스트 ID 리스트", example = "[133, 134, 135]")
    private List<Long> artistIds;
}