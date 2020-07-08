package com.yongseong.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yongseong.spring.entity.Post;

@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Integer> {

}