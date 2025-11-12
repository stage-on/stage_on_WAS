package com.stageon.stageonwas.domain.common.dto;

import lombok.Getter;

@Getter
public class ErrorResDto {
    private String message;
    private int status;

    public ErrorResDto(String message, int status) {
        this.message = message;
        this.status = status;
    }
}