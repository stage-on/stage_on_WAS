package com.stageon.stageonwas.domain.alonecon.service;

import com.stageon.stageonwas.domain.alonecon.dto.PerformanceLikeResDto;
import com.stageon.stageonwas.domain.alonecon.entity.PerformanceDetail;
import com.stageon.stageonwas.domain.alonecon.entity.UserPerformanceLike;
import com.stageon.stageonwas.domain.alonecon.entity.UserPerformanceLikeId;
import com.stageon.stageonwas.domain.alonecon.repository.PerformanceDetailRepository;
import com.stageon.stageonwas.domain.alonecon.repository.UserPerformanceLikeRepository;
import com.stageon.stageonwas.domain.auth.entity.User;
import com.stageon.stageonwas.domain.auth.repository.UserRepository;
import com.stageon.stageonwas.domain.common.service.LikeValidationService;
import com.stageon.stageonwas.exception.CustomException;
import com.stageon.stageonwas.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PerformanceLikeService {

    private final UserPerformanceLikeRepository userPerformanceLikeRepository;
    private final UserRepository userRepository;
    private final PerformanceDetailRepository performanceRepository;
    private final LikeValidationService likeValidationService;

    // 공연 좋아요 (MY CONCERTS 추가)
    public void likePerformance(Long userId, Long performanceId) {
        // 검증로직
        likeValidationService.checkMaxLikes(userId);

        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        PerformanceDetail performance = performanceRepository.findById(performanceId).orElseThrow(() -> new CustomException(ErrorCode.PERFORMANCE_NOT_FOUND));

        UserPerformanceLikeId id = new UserPerformanceLikeId(userId, performanceId);

        if (userPerformanceLikeRepository.existsById(id)) {
            throw new CustomException(ErrorCode.ALREADY_LIKED);
        }

        userPerformanceLikeRepository.save(new UserPerformanceLike(user, performance));
    }

    // 공연 좋아요 취소 (MY CONCERTS 삭제)
    public void unlikePerformance(Long userId, Long performanceId) {
        // 검증로직
        likeValidationService.checkMinLikes(userId);

        UserPerformanceLikeId id = new UserPerformanceLikeId(userId, performanceId);

        if (!userPerformanceLikeRepository.existsById(id)) {
            throw new CustomException(ErrorCode.LIKE_NOT_FOUND);
        }
        userPerformanceLikeRepository.deleteById(id);
    }

    // 내 공연 목록 조회
    @Transactional(readOnly = true)
    public List<PerformanceLikeResDto> getMyConcerts(Long userId) {
        return userPerformanceLikeRepository.findAllWithPerformanceByUserId(userId)
                .stream()
                .map(like -> new PerformanceLikeResDto(like.getPerformance()))
                .collect(Collectors.toList());
    }
}