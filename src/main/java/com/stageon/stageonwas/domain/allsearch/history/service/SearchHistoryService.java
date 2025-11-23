package com.stageon.stageonwas.domain.allsearch.history.service;

import com.stageon.stageonwas.domain.allsearch.history.dto.SearchHistoryResDto;
import com.stageon.stageonwas.domain.allsearch.history.entity.SearchHistory;
import com.stageon.stageonwas.domain.allsearch.history.repository.SearchHistoryRepository;
import com.stageon.stageonwas.domain.auth.entity.User;
import com.stageon.stageonwas.domain.auth.repository.UserRepository;
import com.stageon.stageonwas.exception.CustomException;
import com.stageon.stageonwas.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchHistoryService {

    private final UserRepository userRepository;
    private final SearchHistoryRepository searchHistoryRepository;

    @Transactional(readOnly = true)
    public List<SearchHistoryResDto> getRecentSearches(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        List<SearchHistory> histories = searchHistoryRepository.findTop5ByUserOrderByCreatedAtDesc(user);

        return histories.stream()
                .map(SearchHistoryResDto::new)
                .collect(Collectors.toList());
    }

    public void saveSearchHistory(Long userId, String keyword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 중복 방지 로직
        Optional<SearchHistory> mostRecent = searchHistoryRepository.findTop1ByUserOrderByCreatedAtDesc(user);

        if (mostRecent.isEmpty() || !mostRecent.get().getKeyword().equals(keyword)) {
            searchHistoryRepository.save(new SearchHistory(user, keyword));
        }
    }

    // 최근 검색어 삭제
    public void deleteSearchHistory(Long userId, Long historyId) {
        SearchHistory history = searchHistoryRepository.findById(historyId)
                .orElseThrow(() -> new CustomException(ErrorCode.SEARCH_HISTORY_NOT_FOUND));

        // 본인의 검색 기록인지 확인하는 로직
        if (!history.getUser().getUserId().equals(userId)) {
            throw new CustomException(ErrorCode.FORBIDDEN_ACCESS);
        }

        searchHistoryRepository.delete(history);
    }
}