package com.yongseong.spring.service;

import com.yongseong.spring.dto.PostDto;
import com.yongseong.spring.entity.Post;
import com.yongseong.spring.repository.PostRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    @Transactional
    public Page<Post> readAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Post readByPostId(int id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Page<Post> readByPostTitle(String title, Pageable pageable) {
        return postRepository.findAllByTitle(title, pageable);
    }

    @Override
    @Transactional
    public void createPost(PostDto postDto) {
        ModelMapper modelMapper = new ModelMapper();
        Post post = modelMapper.map(postDto, Post.class);

        postRepository.save(post);
    }

    @Override
    @Transactional
    public void updateByPostId(int id, PostDto postDto) {
        Post post = readByPostId(id);

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        postRepository.save(post);
    }

    @Override
    @Transactional
    public void deleteByPostId(int id) {
        postRepository.deleteById(id);
    }
}