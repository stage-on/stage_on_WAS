package com.stageon.stageonwas.domain.artist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class ArtistSelectReqDto {
    private List<Long> artistIds; // 선택한 아티스트 ID 리스트
}