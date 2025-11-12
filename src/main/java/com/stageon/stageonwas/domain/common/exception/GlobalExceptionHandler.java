package com.stageon.stageonwas.domain.common.exception;

import com.stageon.stageonwas.domain.common.dto.ErrorResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResDto> handleBusinessException(RuntimeException ex) {

        String errorMessage = ex.getMessage();
        ErrorResDto errorResponse = new ErrorResDto(errorMessage, HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
