package com.yongseong.spring.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.yongseong.spring.dto.PostDto;
import com.yongseong.spring.entity.Post;
import com.yongseong.spring.repository.PostRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceImplTest {
    @InjectMocks
    private PostServiceImpl postServiceImpl;

    @Spy
    private ModelMapper modelMapper;

    @Mock
    private PostRepository postRepository;

    @Test
    public void create_post_test() {
        PostDto postDto = new PostDto();
        postDto.setTitle("spring");
        postDto.setContent("test");

        Post post = modelMapper.map(postDto, Post.class);

        when(postRepository.save(any(Post.class))).thenReturn(post);

        postServiceImpl.createPost(postDto);

        verify(postRepository).save(post);
    }
}