package com.yongseong.spring.service;

import com.yongseong.spring.dto.PostDto;
import com.yongseong.spring.entity.Post;

public interface PostService {

    public Post readByPostId(int id);

    public void createPost(PostDto post);

    public void updateByPostId(int id, PostDto post);

    public void deleteByPostId(int id);
}