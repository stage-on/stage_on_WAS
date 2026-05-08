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
    private final ArtistLikeService artistLikeService;
    private final LikeValidationService likeValidationService;

    @Transactional
    public void selectArtists(Long userId, List<Long> artistIds) {

        for (Long artistId : artistIds) {
            artistLikeService.likeArtist(userId, artistId);
        }

        likeValidationService.checkMinLikes(userId,0);


        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        user.upgradeRoleToUser();
        userRepository.save(user);
    }
}
