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


    public void likePerformance(Long userId, Long performanceId) {

        likeValidationService.checkMaxLikes(userId);

        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        PerformanceDetail performance = performanceRepository.findById(performanceId).orElseThrow(() -> new CustomException(ErrorCode.PERFORMANCE_NOT_FOUND));

        UserPerformanceLikeId id = new UserPerformanceLikeId(userId, performanceId);

        if (userPerformanceLikeRepository.existsById(id)) {
            throw new CustomException(ErrorCode.ALREADY_LIKED);
        }

        userPerformanceLikeRepository.save(new UserPerformanceLike(user, performance));
    }



    public void unlikePerformance(Long userId, Long performanceId) {

        likeValidationService.checkMinLikes(userId,1);

        UserPerformanceLikeId id = new UserPerformanceLikeId(userId, performanceId);

        if (!userPerformanceLikeRepository.existsById(id)) {
            throw new CustomException(ErrorCode.LIKE_NOT_FOUND);
        }
        userPerformanceLikeRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public List<PerformanceLikeResDto> getMyConcerts(Long userId) {
        return userPerformanceLikeRepository.findAllWithPerformanceByUserId(userId)
                .stream()
                .map(like -> new PerformanceLikeResDto(like.getPerformance(), true)) // ✅ 항상 true
                .collect(Collectors.toList());
    }





    @Transactional(readOnly = true)
    public List<PerformanceLikeResDto> getMyFestivalConcerts(Long userId) {
        return userPerformanceLikeRepository.findAllWithPerformanceByUserId(userId)
                .stream()
                .map(UserPerformanceLike::getPerformance)
                .filter(p -> p.getTypeofcon() != null && p.getTypeofcon() == 2)
                .map(p -> new PerformanceLikeResDto(p, true))  // ✅ 여기서 liked = true
                .collect(Collectors.toList());
    }

}