package com.yongseong.spring.controller;

import javax.validation.Valid;

import com.yongseong.spring.dto.PostDto;
import com.yongseong.spring.entity.Post;
import com.yongseong.spring.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public ResponseEntity<Page<Post>> readAllPosts(Pageable pageable) {
        Page<Post> allPosts = postService.readAllPosts(pageable);

        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Post> readByPostId(@PathVariable("id") int id) {
        Post post = postService.readByPostId(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<Page<Post>> readByPostTitle(@PathVariable("title") String title, Pageable pageable) {
        Page<Post> allPosts = postService.readByPostTitle(title, pageable);
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<HttpStatus> createPost(@RequestBody @Valid PostDto postDto) {
        postService.createPost(postDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateByPostId(@PathVariable("id") int id, @RequestBody @Valid PostDto postDto) {
        postService.updateByPostId(id, postDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteByPostId(@PathVariable("id") int id) {
        postService.deleteByPostId(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}