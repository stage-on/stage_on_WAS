package com.stageon.stageonwas.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // ARTIST
    ARTIST_NOT_FOUND(404, "존재하지 않는 아티스트입니다."),

    // PERFORMANCE
    PERFORMANCE_NOT_FOUND(404, "존재하지 않는 공연입니다."),

    // LIKE (좋아요 관련)
    LIKE_LIMIT_EXCEEDED(400, "좋아요는 아티스트와 공연을 합쳐 최대 10개까지만 가능합니다."),
    LIKE_MIN_REQUIRED(400, "좋아요는 최소 2개를 유지해야 합니다."),
    ALREADY_LIKED(409, "이미 좋아요를 누른 대상입니다."),
    LIKE_NOT_FOUND(404, "좋아요 내역이 존재하지 않습니다."),

    // SEARCH (검색 관련)
    SEARCH_HISTORY_NOT_FOUND(404, "존재하지 않는 검색 기록입니다."),

    // 유저
    USER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
    FORBIDDEN_ACCESS(403, "접근 권한이 없습니다."),

    // 500 서버
    INTERNAL_SERVER_ERROR(500, "서버 에러입니다. 관리자에게 문의하세요."),

    // 400 기본
    MISSING_INPUT_VALUE(400, "필수 입력값이 누락되었습니다."),
    INVALID_TYPE_VALUE(400, "입력 타입이 올바르지 않습니다."),
    INVALID_PARAMETER(400, "파라미터 값을 확인해주세요.");

    private final int status;
    private final String message;
}
