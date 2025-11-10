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

    // 1. 공연 좋아요 (MY CONCERTS 추가)
    public void likePerformance(Long userId, Long performanceId) {
        // 검증로직
        likeValidationService.checkMaxLikes(userId);

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없음"));
        PerformanceDetail performance = performanceRepository.findById(performanceId).orElseThrow(() -> new RuntimeException("공연 없음"));

        UserPerformanceLikeId id = new UserPerformanceLikeId(userId, performanceId);

        if (userPerformanceLikeRepository.existsById(id)) {
            throw new RuntimeException("이미 좋아요 누른 공연");
        }

        userPerformanceLikeRepository.save(new UserPerformanceLike(user, performance));
    }

    // 2. 공연 좋아요 취소 (MY CONCERTS 삭제)
    public void unlikePerformance(Long userId, Long performanceId) {
        // 검증로직
        likeValidationService.checkMinLikes(userId);

        UserPerformanceLikeId id = new UserPerformanceLikeId(userId, performanceId);

        if (!userPerformanceLikeRepository.existsById(id)) {
            throw new RuntimeException("좋아요 누른 적 없음");
        }
        userPerformanceLikeRepository.deleteById(id);
    }

    // 3. 내 공연 목록 조회
    @Transactional(readOnly = true)
    public List<PerformanceLikeResDto> getMyConcerts(Long userId) {
        return userPerformanceLikeRepository.findAllWithPerformanceByUserId(userId)
                .stream()
                .map(like -> new PerformanceLikeResDto(like.getPerformance()))
                .collect(Collectors.toList());
    }
}