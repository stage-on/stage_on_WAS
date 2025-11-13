package com.stageon.stageonwas.domain.alonecon.repository;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceDetailRepository extends JpaRepository<PerformanceDetail, Long> {
    List<PerformanceDetail> findByPrfnmContaining(String keyword);
}