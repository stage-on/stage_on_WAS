package com.stageon.stageonwas.domain.common.service;

import com.stageon.stageonwas.domain.alonecon.repository.UserPerformanceLikeRepository;
import com.stageon.stageonwas.domain.artist.repository.UserArtistLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeValidationService {

    private final UserArtistLikeRepository userArtistLikeRepository;
    private final UserPerformanceLikeRepository userPerformanceLikeRepository;

    private long getTotalLikes(Long userId) {
        long artistLikes = userArtistLikeRepository.countByUser_UserId(userId);
        long performanceLikes = userPerformanceLikeRepository.countByUser_UserId(userId);
        return artistLikes + performanceLikes;
    }

    public void checkMaxLikes(Long userId) {
        long totalLikes = getTotalLikes(userId);
        if (totalLikes >= 5) {
            throw new RuntimeException("좋아요는 아티스트와 공연을 합쳐 최대 5개까지만 가능합니다.");
        }
    }

    public void checkMinLikes(Long userId) {
        long totalLikes = getTotalLikes(userId);
        if (totalLikes <= 2) {
            throw new RuntimeException("좋아요는 최소 2개를 유지해야 합니다.");
        }
    }
}