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

    // 아티스트 좋아요 (MY BANDS 추가)
    public void likeArtist(Long userId, Long artistId) {
        // 검증로직
        likeValidationService.checkMaxLikes(userId);

        // 실제 유저와 아티스트가 존재하는지 확인
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new CustomException(ErrorCode.ARTIST_NOT_FOUND));

        // 복합 PK 객체 생성
        UserArtistLikeId id = new UserArtistLikeId(userId, artistId);

        // 이미 좋아요를 눌렀는지 확인 (중복 방지)
        if (userArtistLikeRepository.existsById(id)) {
            throw new CustomException(ErrorCode.ALREADY_LIKED);
        }

        userArtistLikeRepository.save(new UserArtistLike(user, artist));
    }

    // 아티스트 좋아요 취소 (MY BANDS 삭제)
    public void unlikeArtist(Long userId, List<Long> artistIds) {
        // 검증로직
        likeValidationService.checkMinLikes(userId);
        int requestSize = artistIds.stream().distinct().toList().size();
        long deletedCount = userArtistLikeRepository.deleteAllByUserUserIdAndArtistIdIn(userId, artistIds);
        if (deletedCount != requestSize) {
            throw new CustomException(ErrorCode.LIKE_NOT_FOUND);
        }

    }

    // 내 밴드 목록 조회
    @Transactional(readOnly = true)
    public List<ArtistLikeResDto> getMyBands(Long userId) {
        return userArtistLikeRepository.findAllWithArtistByUserId(userId)
                .stream()
                .map(like -> new ArtistLikeResDto(like.getArtist()))
                .collect(Collectors.toList());
    }
}