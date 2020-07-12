package com.yongseong.spring.util.error;

import com.yongseong.spring.util.error.exception.BusinessException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.builder().status(errorCode.getStatus())
                .message(errorCode.getMessage()).build();
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }
}