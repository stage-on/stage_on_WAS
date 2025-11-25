package com.stageon.stageonwas.domain.artist.service;

import com.stageon.stageonwas.domain.auth.entity.User;
import com.stageon.stageonwas.domain.auth.repository.UserRepository;
import com.stageon.stageonwas.domain.common.service.LikeValidationService;
import com.stageon.stageonwas.exception.CustomException;
import com.stageon.stageonwas.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistSelectService {
    private final UserRepository userRepository;
    private final ArtistLikeService artistLikeService;         // 좋아요 저장 담당
    private final LikeValidationService likeValidationService; // 개수 검증 담당

    @Transactional // 하나라도 실패하면 전체 롤백
    public void selectArtists(Long userId, List<Long> artistIds) {

        for (Long artistId : artistIds) {
            artistLikeService.likeArtist(userId, artistId);
        }

        likeValidationService.checkMaxLikes(userId); // 10개 초과 체크
        likeValidationService.checkMinLikes(userId); // 2개 미만 체크


        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.upgradeRoleToUser();
    }
}
