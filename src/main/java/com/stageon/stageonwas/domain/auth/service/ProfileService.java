package com.stageon.stageonwas.domain.auth.service;

import com.stageon.stageonwas.domain.auth.dto.ProfileResDto;
import com.stageon.stageonwas.domain.auth.entity.User;
import com.stageon.stageonwas.domain.auth.repository.UserRepository;
import com.stageon.stageonwas.exception.CustomException;
import com.stageon.stageonwas.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final UserRepository userRepository;

    public ProfileResDto getMyProfile(String email, String provider) {
        User user = userRepository.findUserByEmailAndProvider(email, provider)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return new ProfileResDto(user);
    }
}