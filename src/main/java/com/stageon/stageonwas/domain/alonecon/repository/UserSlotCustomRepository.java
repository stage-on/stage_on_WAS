package com.stageon.stageonwas.domain.alonecon.repository;

import com.stageon.stageonwas.domain.alonecon.entity.UserSlotCustom;
import com.stageon.stageonwas.domain.alonecon.entity.UserSlotCustomId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserSlotCustomRepository extends JpaRepository<UserSlotCustom, UserSlotCustomId> {

   List<UserSlotCustom> findByIdUserIdAndIdPerformanceId(Long userId, Long performanceId);


    void deleteByIdUserIdAndIdPerformanceId(Long userId, Long performanceId);


    @Query("select distinct cs.id.performanceId " +
            "from UserSlotCustom cs " +
            "where cs.id.userId = :userId")
    List<Long> findDistinctPerformanceIdsByUserId(@Param("userId") Long userId);
}
