package com.sparta.post.repository;

import com.sparta.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();

    Optional<Object> findByIdAndUserId(Long id, Long id1);
}