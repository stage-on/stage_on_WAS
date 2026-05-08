package com.stageon.stageonwas.domain.alonecon.repository;
import com.stageon.stageonwas.domain.alonecon.repository.UserPerformanceLikeRepository;
// (패키지 경로는 실제 프로젝트 구조에 맞게 조정)

import java.util.HashSet;
import java.util.Set;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.entity.UserPerformanceLike;
import com.stageon.stageonwas.domain.alonecon.entity.UserPerformanceLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface UserPerformanceLikeRepository extends JpaRepository<UserPerformanceLike, UserPerformanceLikeId> {
    @Query("SELECT upl FROM UserPerformanceLike upl " +
            "JOIN FETCH upl.performance p " +
            "WHERE upl.user.userId = :userId")
    List<UserPerformanceLike> findAllWithPerformanceByUserId(@Param("userId") Long userId);

    long countByUser_UserId(Long userId);


    @Query("SELECT upl FROM UserPerformanceLike upl " +
            "JOIN FETCH upl.user u " +
            "JOIN FETCH upl.performance p " +
            "WHERE p.tkstdate = :tkstdate")
    List<UserPerformanceLike> findAllWithUserAndPerformanceByPerformanceTkstdate(@Param("tkstdate") LocalDate tkstdate);


    @Query("SELECT pl.id.performanceId FROM UserPerformanceLike pl WHERE pl.id.userId = :userId")
    List<Long> findPerformanceIdsByUserId(@Param("userId") Long userId);
}