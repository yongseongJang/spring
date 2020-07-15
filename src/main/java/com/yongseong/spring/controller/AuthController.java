package com.yongseong.spring.controller;

import javax.validation.Valid;

import com.yongseong.spring.dto.UserDto;
import com.yongseong.spring.service.AuthService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody @Valid UserDto.LoginDto loginDto) {

        String jwt = authService.authenticateUser(loginDto);

        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> registerUser(@RequestBody @Valid UserDto.SignUpDto signUpDto) {

        authService.registerUser(signUpDto);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/log")
    public ResponseEntity<HttpStatus> logTest() {
        logger.info("test logback logger info");
        logger.error("test logback logger error");

        return ResponseEntity.ok(HttpStatus.OK);
    }
}