package com.yongseong.spring.util.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(400, "Invalid input value"),

    // Authentication
    EMAIL_NOT_FOUND(400, "Email is not found"),

    // User
    EMAIL_DUPLICATION(422, "Email is duplication"), USERNAME_DUPLICATION(422, "User name is duplicaition"),
    ROLE_NOT_FOUND(400, "Role is not found");

    private int status;
    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}