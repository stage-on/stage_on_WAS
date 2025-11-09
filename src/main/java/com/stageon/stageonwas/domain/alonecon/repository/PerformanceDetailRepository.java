package com.stageon.stageonwas.domain.alonecon.repository;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceDetailRepository extends JpaRepository<PerformanceDetail, Long> {

}