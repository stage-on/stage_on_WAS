package com.stageon.stageonwas.domain.alonecon.repository;

import com.stageon.stageonwas.domain.alonecon.entity.UserSlotCustom;
import com.stageon.stageonwas.domain.alonecon.entity.UserSlotCustomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserSlotCustomRepository extends JpaRepository<UserSlotCustom, UserSlotCustomId> {

    // 1) 특정 유저 + 공연에 대한 커스텀 슬롯들
    List<UserSlotCustom> findByIdUserIdAndIdPerformanceId(Long userId, Long performanceId);

    // 2) 특정 유저 + 공연에 대한 커스텀 슬롯 싹 지우기 (덮어쓰기용)
    void deleteByIdUserIdAndIdPerformanceId(Long userId, Long performanceId);

    // 3) "이 유저가 한 번이라도 커스텀한 공연 id 목록" (중복 제거)
    @Query("select distinct cs.id.performanceId " +
            "from UserSlotCustom cs " +
            "where cs.id.userId = :userId")
    List<Long> findDistinctPerformanceIdsByUserId(@Param("userId") Long userId);
}
