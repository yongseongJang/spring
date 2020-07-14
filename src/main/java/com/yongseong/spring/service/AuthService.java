package com.yongseong.spring.service;

import com.yongseong.spring.dto.UserDto.LoginDto;
import com.yongseong.spring.dto.UserDto.SignUpDto;

public interface AuthService {
    public String authenticateUser(LoginDto loginDto);

    public void registerUser(SignUpDto signUpDto);
}