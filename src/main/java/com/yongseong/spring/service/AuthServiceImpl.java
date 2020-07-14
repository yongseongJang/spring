package com.yongseong.spring.service;

import java.util.HashSet;
import java.util.Set;

import com.yongseong.spring.dto.UserDto.LoginDto;
import com.yongseong.spring.dto.UserDto.SignUpDto;
import com.yongseong.spring.entity.Role;
import com.yongseong.spring.entity.User;
import com.yongseong.spring.repository.RoleRepository;
import com.yongseong.spring.repository.UserRepository;
import com.yongseong.spring.util.ERole;
import com.yongseong.spring.util.error.ErrorCode;
import com.yongseong.spring.util.error.exception.BusinessException;
import com.yongseong.spring.util.jwt.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    @Transactional
    public String authenticateUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateJwtToken(authentication);
    }

    @Override
    @Transactional
    public void registerUser(SignUpDto signUpDto) {
        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new BusinessException(ErrorCode.EMAIL_DUPLICATION);
        }

        if (userRepository.existsByUserName(signUpDto.getUserName())) {
            throw new BusinessException(ErrorCode.USERNAME_DUPLICATION);
        }

        User user = User.builder().email(signUpDto.getEmail()).password(encoder.encode(signUpDto.getPassword()))
                .userName(signUpDto.getUserName()).build();

        Set<String> strRoles = signUpDto.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new BusinessException());
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND));
                        roles.add(userRole);

                        break;
                    default:
                        userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new BusinessException(ErrorCode.ROLE_NOT_FOUND));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
    }

}