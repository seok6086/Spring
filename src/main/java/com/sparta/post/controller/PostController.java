package com.sparta.post.controller;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.ResponseDto;
import com.sparta.post.dto.ResponsePostDto;
import com.sparta.post.entity.Post;
import com.sparta.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //전체 게시글 조회
    @GetMapping("/api/posts")
    public List<ResponsePostDto> findAllPost() {
        return postService.findAllPost();
    }

    //선택 게시글 조회
    @GetMapping("/api/posts/{id}")
    public ResponsePostDto findOnePost(@PathVariable Long id) {
        return postService.findOnePost(id);
    }

    //게시글 작성
    @PostMapping("/api/posts")
    public ResponsePostDto createPost(@RequestBody PostRequestDto postDto, HttpServletRequest request) {
        return postService.createPost(postDto, request);

    }

    //게시글 수정
    @PutMapping("/api/posts/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return postService.updatePost(id, requestDto, request);
    }


    //게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public ResponseDto deletePost(@PathVariable Long id, HttpServletRequest request) {
        return postService.deletePost(id, request);
    }

}