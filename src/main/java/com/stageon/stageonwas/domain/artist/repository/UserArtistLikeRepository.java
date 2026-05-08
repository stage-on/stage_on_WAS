package com.stageon.stageonwas.domain.artist.repository;

import com.stageon.stageonwas.domain.artist.entity.Artist;
import com.stageon.stageonwas.domain.artist.entity.UserArtistLike;
import com.stageon.stageonwas.domain.artist.entity.UserArtistLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserArtistLikeRepository extends JpaRepository<UserArtistLike, UserArtistLikeId> {

    @Query("SELECT ual FROM UserArtistLike ual " +
            "JOIN FETCH ual.artist a " +
            "WHERE ual.user.userId = :userId")
    List<UserArtistLike> findAllWithArtistByUserId(@Param("userId") Long userId);

    long countByUser_UserId(Long userId);

    long deleteAllByUserUserIdAndArtistIdIn(Long userId, List<Long> artistIds);

    @Query("select ual.artist from UserArtistLike ual where ual.user.userId = :userId")
    List<Artist> findLikedArtistsByUserId(@Param("userId") Long userId);


    @Query("SELECT ual.id.artistId FROM UserArtistLike ual WHERE ual.id.userId = :userId")
    List<Long> findArtistIdsByUserId(@Param("userId") Long userId);
}
