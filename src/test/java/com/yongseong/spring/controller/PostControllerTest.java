package com.yongseong.spring.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yongseong.spring.entity.Post;
import com.yongseong.spring.service.PostServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {
    @Autowired
    @InjectMocks
    private PostController postController;

    private MockMvc mockMvc;

    @Mock
    private PostServiceImpl postServiceImpl;

    @Before
    public void createController() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
    }

    @Test
    public void readByPostIdTest() throws Exception {
        Post post = new Post("title", "content");

        when(postServiceImpl.readByPostId(anyInt())).thenReturn(post);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/posts/1").contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
    }
}