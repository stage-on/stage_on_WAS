package com.stageon.stageonwas.domain.artist.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
// 아티스트 좋아요 ID 클래스 (복합 PK용)
public class UserArtistLikeId implements Serializable {
    private Long userId;
    private Long artistId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserArtistLikeId that = (UserArtistLikeId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(artistId, that.artistId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, artistId);
    }
}
