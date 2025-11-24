package com.stageon.stageonwas.domain.auth.dto;

import com.stageon.stageonwas.domain.auth.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "프로필에 띄울 DTO")
@Getter
public class ProfileResDto {
    @Schema(description = "유저명", example = "김이박")
    private final String name;

    @Schema(description = "이메일", example = "1234@gmail.com")
    private final String email;

    @Schema(description = "프로필 URL", example = "https://~~~")
    private final String profileImage;

    @Builder
    public ProfileResDto(User user) {
        this.name = user.getUsername();
        this.email = user.getEmail();
        this.profileImage = user.getProfileImage() != null ? user.getProfileImage() : "";
    }
}
