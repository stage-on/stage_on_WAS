package com.stageon.stageonwas.domain.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이미 존재하는 유저인지 확인 DTO")
public class UserCheckResDto {
    private String email;
    private String name;
    @Schema(description = "첫 로그인 인지?",example = "true")
    private boolean isFirstLogin;
    private String profileImgUrl;
}
