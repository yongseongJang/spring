package com.yongseong.spring.service;

import com.yongseong.spring.dto.PostDto;
import com.yongseong.spring.entity.Post;
import com.yongseong.spring.repository.PostRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post readByPostId(int id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Override
    public void createPost(PostDto postDto) {
        ModelMapper modelMapper = new ModelMapper();
        Post post = modelMapper.map(postDto, Post.class);

        postRepository.save(post);
    }

    @Override
    public void updateByPostId(int id, PostDto postDto) {
        Post post = readByPostId(id);

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        postRepository.save(post);
    }

    @Override
    public void deleteByPostId(int id) {
        postRepository.deleteById(id);
    }
}