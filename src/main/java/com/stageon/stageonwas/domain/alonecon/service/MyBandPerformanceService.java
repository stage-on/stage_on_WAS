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

    /**
     * 내가 좋아요 누른 밴드별로 공연 리스트를 묶어서 반환
     * - 각 밴드 이름으로 PerformanceDetailRepository.findByArtistNameAnywhere() 호출
     * - styurls / slots / artistPics 에서 이름이 매칭되는 공연들을 가져옴
     * - 밴드별로 최대 10개, 시작일 가까운 순 정렬
     */
    public List<MyBandPerformanceSectionDto> getMyBandPerformances(Long userId) {

        // 1. 좋아요 누른 밴드(Artist)들 가져오기 (fetch join)
        List<UserArtistLike> likes = userArtistLikeRepository.findAllWithArtistByUserId(userId);

        // 2. 좋아요한 Artist 기준으로 섹션 생성
        return likes.stream()
                .map(UserArtistLike::getArtist)
                .filter(Objects::nonNull)
                .distinct() // 같은 밴드를 여러 번 좋아요 했을 경우 대비
                .map(artist -> {
                    String bandName = artist.getBandName();
                    if (bandName == null || bandName.isBlank()) {
                        // 밴드 이름이 없으면 공연 빈 리스트
                        return new MyBandPerformanceSectionDto(artist, List.of());
                    }

                    // 3. 이 밴드가 포함된 공연들 검색
                    List<PerformanceDetail> details =
                            performanceDetailRepository.findByArtistNameAnywhere(bandName);

                    // 4. 시작일 기준 정렬 후 상위 10개만, PeriodDto 로 변환
                    List<PerformancePeriodDto> performanceDtos = details.stream()
                            .sorted(Comparator.comparing(
                                    PerformanceDetail::getPrfpdfrom,
                                    Comparator.nullsLast(LocalDate::compareTo)
                            ))
                            .limit(10)
                            .map(PerformancePeriodDto::new)
                            .collect(Collectors.toList());

                    // 5. 섹션 DTO 생성
                    return new MyBandPerformanceSectionDto(artist, performanceDtos);
                })
                .collect(Collectors.toList());
    }
}
