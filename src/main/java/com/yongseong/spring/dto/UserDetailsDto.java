package com.yongseong.spring.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.yongseong.spring.entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsDto implements UserDetails {
    private static final long serialVersionUID = 1L;

    private UserDto.LoginDto loginDto;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsDto(UserDto.LoginDto loginDto, Collection<? extends GrantedAuthority> authorities) {
        this.loginDto = loginDto;
        this.authorities = authorities;
    }

    public static UserDetailsDto build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

        UserDto.LoginDto loginDto = new UserDto.LoginDto(user.getEmail(), user.getPassword());
        return new UserDetailsDto(loginDto, authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return loginDto.getEmail();
    }

    @Override
    public String getPassword() {
        return loginDto.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}