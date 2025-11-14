package com.stageon.stageonwas.domain.allsearch.recommend.service;

import com.stageon.stageonwas.domain.allsearch.recommend.dto.RecommendedResDto;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.repository.PerformanceDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecommendService {

    private final PerformanceDetailRepository performanceDetailRepository;

    public List<RecommendedResDto> getRecommendedKeywords() {

        List<String> keywords = getRandomKeywordsFromPerformances(5);
        return keywords.stream()
                .map(RecommendedResDto::new)
                .collect(Collectors.toList());
    }

    private List<String> getRandomKeywordsFromPerformances(long count) {
        List<String> performanceNames = new ArrayList<>();
        long performanceTotal = performanceDetailRepository.count();

        if (performanceTotal > 0) {
            Random random = new Random();
            for (int i = 0; i < count; i++) {
                int randomOffset = random.nextInt((int) performanceTotal);
                Page<PerformanceDetail> perfPage = performanceDetailRepository.findAll(PageRequest.of(randomOffset, 1));

                if (perfPage.hasContent()) {
                    performanceNames.add(perfPage.getContent().get(0).getPrfnm());
                }
            }
        }
        return performanceNames;
    }
}