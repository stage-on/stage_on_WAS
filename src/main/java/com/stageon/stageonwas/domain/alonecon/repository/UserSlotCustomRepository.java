package com.stageon.stageonwas.domain.alonecon.repository;

import com.stageon.stageonwas.domain.alonecon.entity.UserSlotCustom;
import com.stageon.stageonwas.domain.alonecon.entity.UserSlotCustomId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSlotCustomRepository extends JpaRepository<UserSlotCustom, UserSlotCustomId> {

    List<UserSlotCustom> findByIdUserIdAndIdPerformanceId(Long userId, Long performanceId);

    void deleteByIdUserIdAndIdPerformanceId(Long userId, Long performanceId);
}
