package com.yongseong.spring.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class UserDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class LoginDto {
        @NotBlank
        private String email;
        @NotBlank
        private String password;

        public LoginDto(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SignUpDto {
        @NotBlank
        private String email;

        @NotBlank
        private String password;

        @NotBlank
        private String userName;

        private Set<String> role;
    }
}