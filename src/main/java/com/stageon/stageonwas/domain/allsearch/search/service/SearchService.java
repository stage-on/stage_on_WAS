package com.stageon.stageonwas.domain.allsearch.search.service;

import com.stageon.stageonwas.domain.allsearch.history.service.SearchHistoryService;
import com.stageon.stageonwas.domain.allsearch.search.dto.SearchResDto;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.artist.entity.Artist;
import com.stageon.stageonwas.domain.artist.repository.ArtistRepository;
import com.stageon.stageonwas.domain.alonecon.repository.PerformanceDetailRepository;
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

    public SearchResDto getSearchResults(Long userId, String keyword) {
        List<PerformanceDetail> performances = performanceDetailRepository.findByPrfnmContaining(keyword);

        List<Artist> artists = artistRepository.findByArtistNameContaining(keyword);

        searchHistoryService.saveSearchHistory(userId, keyword);

        return new SearchResDto(performances, artists);
    }
}