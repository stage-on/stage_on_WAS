package com.stageon.stageonwas.domain.artist.repository;

import com.stageon.stageonwas.domain.artist.entity.UserArtistLike;
import com.stageon.stageonwas.domain.artist.entity.UserArtistLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserArtistLikeRepository extends JpaRepository<UserArtistLike, UserArtistLikeId> {
    // 특정 유저가 좋아요 누른 아티스트 목록 조회
    // JPQL을 사용하여 'N+1 문제'를 방지하는 fetch join
    @Query("SELECT ual FROM UserArtistLike ual " +
            "JOIN FETCH ual.artist a " +
            "WHERE ual.user.userId = :userId")
    List<UserArtistLike> findAllWithArtistByUserId(@Param("userId") Long userId);

    long countByUser_UserId(Long userId);
}
