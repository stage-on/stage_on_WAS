package com.stageon.stageonwas.domain.alonecon.service;

import com.stageon.stageonwas.domain.alonecon.dto.MyBandPerformanceSectionDto;
import com.stageon.stageonwas.domain.alonecon.dto.PerformancePeriodDto;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.repository.PerformanceDetailRepository;
import com.stageon.stageonwas.domain.artist.entity.UserArtistLike;
import com.stageon.stageonwas.domain.artist.repository.UserArtistLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyBandPerformanceService {

    private final UserArtistLikeRepository userArtistLikeRepository;
    private final PerformanceDetailRepository performanceDetailRepository;


    public List<MyBandPerformanceSectionDto> getMyBandPerformances(Long userId) {


        List<UserArtistLike> likes = userArtistLikeRepository.findAllWithArtistByUserId(userId);


        return likes.stream()
                .map(UserArtistLike::getArtist)
                .filter(Objects::nonNull)
                .distinct()
                .map(artist -> {
                    String bandName = artist.getBandName();
                    if (bandName == null || bandName.isBlank()) {
                        return new MyBandPerformanceSectionDto(artist, List.of());
                    }


                    List<PerformanceDetail> details =
                            performanceDetailRepository.findByArtistNameAnywhere(bandName);


                    List<PerformancePeriodDto> performanceDtos = details.stream()
                            .sorted(Comparator.comparing(
                                    PerformanceDetail::getPrfpdfrom,
                                    Comparator.nullsLast(LocalDate::compareTo)
                            ))
                            .limit(10)
                            .map(PerformancePeriodDto::new)
                            .collect(Collectors.toList());


                    return new MyBandPerformanceSectionDto(artist, performanceDtos);
                })
                .collect(Collectors.toList());
    }
}
