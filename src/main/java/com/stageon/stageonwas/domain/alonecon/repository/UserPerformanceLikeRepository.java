package com.stageon.stageonwas.domain.alonecon.repository;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.entity.UserPerformanceLike;
import com.stageon.stageonwas.domain.alonecon.entity.UserPerformanceLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserPerformanceLikeRepository extends JpaRepository<UserPerformanceLike, UserPerformanceLikeId> {
    // 특정 유저가 좋아요 누른 공연 목록 조회
    @Query("SELECT upl FROM UserPerformanceLike upl " +
            "JOIN FETCH upl.performance p " +
            "WHERE upl.user.userId = :userId")
    List<UserPerformanceLike> findAllWithPerformanceByUserId(@Param("userId") Long userId);

    long countByUser_UserId(Long userId);

    // 이메일 관련
    @Query("SELECT upl FROM UserPerformanceLike upl " +
            "JOIN FETCH upl.user u " +
            "JOIN FETCH upl.performance p " +
            "WHERE p.tkstdate = :tkstdate")
    List<UserPerformanceLike> findAllWithUserAndPerformanceByPerformanceTkstdate(@Param("tkstdate") LocalDate tkstdate);

    // 검색시 해당 공연에 좋아요가 되어있는지 확인시 필요
    @Query("SELECT pl.id.performanceId FROM UserPerformanceLike pl WHERE pl.id.userId = :userId")
    List<Long> findPerformanceIdsByUserId(@Param("userId") Long userId);
}