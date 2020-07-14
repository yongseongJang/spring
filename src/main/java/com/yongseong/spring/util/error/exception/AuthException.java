package com.yongseong.spring.util.error.exception;

import com.yongseong.spring.util.error.ErrorCode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AuthException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public AuthException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }
}