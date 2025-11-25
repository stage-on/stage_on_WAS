package com.stageon.stageonwas.domain.auth.service;

import com.stageon.stageonwas.domain.auth.dto.UserCheckResDto;
import com.stageon.stageonwas.domain.auth.entity.Role;
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
public class UserCheckService {
    private final UserRepository userRepository;

    public UserCheckResDto checkLoginStatus(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // Role이 GUEST면 true, 아니면 false
        boolean isFirstLogin = (user.getRole() == Role.GUEST);

        return UserCheckResDto.builder()
                .email(user.getEmail())
                .name(user.getUsername())
                .profileImgUrl(user.getProfileImage())
                .isFirstLogin(isFirstLogin)
                .build();
    }
}
