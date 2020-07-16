package com.yongseong.spring.service;

import com.yongseong.spring.dto.PostDto;
import com.yongseong.spring.entity.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {

    public Page<Post> readAllPosts(Pageable pageable);

    public Post readByPostId(int id);

    public Page<Post> readByPostTitle(String title, Pageable pageable);

    public void createPost(PostDto post);

    public void updateByPostId(int id, PostDto post);

    public void deleteByPostId(int id);

}