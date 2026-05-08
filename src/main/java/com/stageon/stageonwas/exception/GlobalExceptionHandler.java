package com.stageon.stageonwas.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 1. 커스텀 예외 (우리가 직접 발생시킨 예외)
     */
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResDto> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        ErrorResDto errorDto = new ErrorResDto(
                errorCode.getMessage(),
                errorCode.getStatus()
        );

        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    /**
     * 2. 유효성 검사
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResDto> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_PARAMETER; // "파라미터 값을 확인해주세요."

        Map<String, String> errors = new HashMap<>();
        for(FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        // ErrorDto 생성자에 ErrorCode의 메시지와 상태코드를 사용
        ErrorResDto errorDto = new ErrorResDto(
                errorCode.getMessage(),
                errorCode.getStatus(),
                errors
        );

        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    /**
     * 3. 필수 파라미터 누락 (MissingServletRequestParameterException)
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ErrorResDto> handleMissingParam(MissingServletRequestParameterException ex) {
        ErrorCode errorCode = ErrorCode.MISSING_INPUT_VALUE; // "필수 입력값이 누락되었습니다."

        // 상세 메시지 조합: "page 필수 입력값이 누락되었습니다."
        String detailMsg = ex.getParameterName() + " " + errorCode.getMessage();

        ErrorResDto errorDto = new ErrorResDto(detailMsg, errorCode.getStatus());

        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    /**
     * 4. 파라미터 타입 불일치 (MethodArgumentTypeMismatchException)
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResDto> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_TYPE_VALUE; // "입력 타입이 올바르지 않습니다."

        // 상세 메시지 조합: "age 입력 타입이 올바르지 않습니다."
        String detailMsg = ex.getName() + " " + errorCode.getMessage();

        ErrorResDto errorDto = new ErrorResDto(detailMsg, errorCode.getStatus());

        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(errorCode.getStatus()));
    }

    /**
     * 5. 그 외 서버 내부 에러
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResDto> handleServerException(Exception ex) {
        log.error("INTERNAL_SERVER_ERROR", ex);

        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;

        ErrorResDto errorDto = new ErrorResDto(
                errorCode.getMessage(),
                errorCode.getStatus()
        );

        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}