package com.stageon.stageonwas.domain.artist.service;

import com.stageon.stageonwas.domain.artist.dto.ArtistLikeResDto;
import com.stageon.stageonwas.domain.artist.entity.Artist;
import com.stageon.stageonwas.domain.artist.entity.UserArtistLike;
import com.stageon.stageonwas.domain.artist.entity.UserArtistLikeId;
import com.stageon.stageonwas.domain.artist.repository.ArtistRepository;
import com.stageon.stageonwas.domain.artist.repository.UserArtistLikeRepository;
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
public class ArtistLikeService {
    private final UserArtistLikeRepository userArtistLikeRepository;
    private final UserRepository userRepository;
    private final ArtistRepository artistRepository;
    private final LikeValidationService likeValidationService; // 좋아요 2~10개 검증을 위함


    public void likeArtist(Long userId, Long artistId) {

        likeValidationService.checkMaxLikes(userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTIST_NOT_FOUND));


        UserArtistLikeId id = new UserArtistLikeId(userId, artistId);


        if (userArtistLikeRepository.existsById(id)) {
            throw new CustomException(ErrorCode.ALREADY_LIKED);
        }

        userArtistLikeRepository.save(new UserArtistLike(user, artist));
    }


    public void unlikeArtist(Long userId, List<Long> artistIds) {
        // 검증로직
        int deleteCount = artistIds.stream().distinct().toList().size();
        likeValidationService.checkMinLikes(userId, deleteCount);
        int requestSize = artistIds.stream().distinct().toList().size();
        long deletedCount = userArtistLikeRepository.deleteAllByUserUserIdAndArtistIdIn(userId, artistIds);
        if (deletedCount != requestSize) {
            throw new CustomException(ErrorCode.LIKE_NOT_FOUND);
        }

    }


    @Transactional(readOnly = true)
    public List<ArtistLikeResDto> getMyBands(Long userId) {
        return userArtistLikeRepository.findAllWithArtistByUserId(userId)
                .stream()
                .map(like -> new ArtistLikeResDto(like.getArtist()))
                .collect(Collectors.toList());
    }
}