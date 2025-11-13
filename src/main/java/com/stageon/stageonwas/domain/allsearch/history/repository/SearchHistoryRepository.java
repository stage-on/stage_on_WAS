package com.stageon.stageonwas.domain.allsearch.history.repository;

import com.stageon.stageonwas.domain.allsearch.history.entity.SearchHistory;
import com.stageon.stageonwas.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

    // 최근 검색어 목록 조회 (최신순 5개)
    List<SearchHistory> findTop5ByUserOrderByCreatedAtDesc(User user);

    // 중복 저장을 막기 위해, 가장 최근 검색어 1개 조회
    Optional<SearchHistory> findTop1ByUserOrderByCreatedAtDesc(User user);
}