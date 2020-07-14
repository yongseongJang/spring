package com.yongseong.spring.service;

import java.util.Optional;

import javax.transaction.Transactional;

import com.yongseong.spring.dto.UserDetailsDto;
import com.yongseong.spring.entity.User;
import com.yongseong.spring.repository.UserRepository;
import com.yongseong.spring.util.error.ErrorCode;
import com.yongseong.spring.util.error.exception.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userReposotiry;

    @Override
    @Transactional
    public UserDetailsDto loadUserByUsername(String userEmail) {
        Optional<User> existed = userReposotiry.findByEmail(userEmail);
        if (!existed.isPresent()) {
            throw new AuthException(ErrorCode.EMAIL_NOT_FOUND);
        }

        User user = existed.get();

        return UserDetailsDto.build(user);
    }

}