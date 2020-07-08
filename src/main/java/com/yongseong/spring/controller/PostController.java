package com.yongseong.spring.controller;

import javax.validation.Valid;

import com.yongseong.spring.dto.PostDto;
import com.yongseong.spring.entity.Post;
import com.yongseong.spring.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> readByPostId(@PathVariable("id") int id) {
        Post post = postService.readByPostId(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<HttpStatus> testApi() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<HttpStatus> createPost(@RequestBody @Valid PostDto postDto) {
        postService.createPost(postDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<HttpStatus> updateByPostId(@PathVariable("id") int id, @RequestBody @Valid PostDto postDto) {
        postService.updateByPostId(id, postDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<HttpStatus> deleteByPostId(@PathVariable("id") int id) {
        postService.deleteByPostId(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}