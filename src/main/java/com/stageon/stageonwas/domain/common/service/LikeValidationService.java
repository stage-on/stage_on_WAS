package com.stageon.stageonwas.domain.common.service;

import com.stageon.stageonwas.domain.alonecon.repository.UserPerformanceLikeRepository;
import com.stageon.stageonwas.domain.artist.repository.UserArtistLikeRepository;
import com.stageon.stageonwas.exception.CustomException;
import com.stageon.stageonwas.exception.ErrorCode;
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
        if (totalLikes >= 5) { // FIXME : 갯수 늘리기
            throw new CustomException(ErrorCode.LIKE_LIMIT_EXCEEDED);
        }
    }

    public void checkMinLikes(Long userId) {
        long totalLikes = getTotalLikes(userId);
        if (totalLikes <= 2) {
            throw new CustomException(ErrorCode.LIKE_MIN_REQUIRED);
        }
    }
}