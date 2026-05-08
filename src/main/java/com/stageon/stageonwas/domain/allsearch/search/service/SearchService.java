package com.stageon.stageonwas.domain.allsearch.search.service;

import com.stageon.stageonwas.domain.allsearch.history.service.SearchHistoryService;
import com.stageon.stageonwas.domain.allsearch.search.dto.SearchResDto;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.repository.UserPerformanceLikeRepository;
import com.stageon.stageonwas.domain.artist.entity.Artist;
import com.stageon.stageonwas.domain.artist.repository.ArtistRepository;
import com.stageon.stageonwas.domain.alonecon.repository.PerformanceDetailRepository;
import com.stageon.stageonwas.domain.artist.repository.UserArtistLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SearchService {

    private final ArtistRepository artistRepository;
    private final PerformanceDetailRepository performanceDetailRepository;
    private final SearchHistoryService searchHistoryService;

    private final UserPerformanceLikeRepository performanceLikeRepository;
    private final UserArtistLikeRepository userArtistLikeRepository;

    @Transactional
    public SearchResDto getSearchResults(Long userId, String keyword) {
        // 검색 결과 조회
        List<PerformanceDetail> performances = performanceDetailRepository.findByPrfnmContaining(keyword);
        List<Artist> artists = artistRepository.findByBandNameContaining(keyword);

        // 검색 기록 저장 (userId는 무조건 있다고 가정)
        searchHistoryService.saveSearchHistory(userId, keyword);

        // 좋아요 목록 조회
        List<Long> likedPerfIds = performanceLikeRepository.findPerformanceIdsByUserId(userId);
        List<Long> likedArtistIds = userArtistLikeRepository.findArtistIdsByUserId(userId);

        // 4. DTO 생성 및 반환
        return new SearchResDto(performances, artists, likedPerfIds, likedArtistIds);
    }
}