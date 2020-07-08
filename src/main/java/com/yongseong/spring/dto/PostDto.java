package com.yongseong.spring.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDto {
    @NotBlank
    private String title;

    private String content;

}