package com.stageon.stageonwas.domain.allsearch.search.dto;

import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.artist.entity.Artist;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SearchResDto {

    private final SearchResult<SearchPerformanceResDto> performances;
    private final SearchResult<SearchArtistResDto> artists;

    public SearchResDto(List<PerformanceDetail> performanceList, List<Artist> artistList) {

        List<SearchPerformanceResDto> performanceDtos = performanceList.stream()
                .map(SearchPerformanceResDto::new)
                .collect(Collectors.toList());

        List<SearchArtistResDto> artistDtos = artistList.stream()
                .map(SearchArtistResDto::new)
                .collect(Collectors.toList());

        this.performances = new SearchResult<>(performanceDtos.size(), performanceDtos);
        this.artists = new SearchResult<>(artistDtos.size(), artistDtos);
    }

    @Getter
    private static class SearchResult<T> {
        private final int count;
        private final List<T> items;

        private SearchResult(int count, List<T> items) {
            this.count = count;
            this.items = items;
        }
    }
}