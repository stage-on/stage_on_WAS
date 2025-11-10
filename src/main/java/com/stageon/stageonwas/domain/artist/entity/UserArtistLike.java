package com.stageon.stageonwas.domain.artist.entity;

import com.stageon.stageonwas.domain.auth.entity.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_artist_like")
public class UserArtistLike {
    @EmbeddedId
    private UserArtistLikeId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId") // UserArtistLikeId의 'userId' 필드에 매핑
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("artistId") // UserArtistLikeId의 'artistId' 필드에 매핑
    @JoinColumn(name = "artist_id")
    private Artist artist;

    public UserArtistLike(User user, Artist artist) {
        this.user = user;
        this.artist = artist;
        this.id = new UserArtistLikeId(user.getUserId(), artist.getArtistId());
    }
}