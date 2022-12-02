package com.sparta.post.controller;

import com.sparta.post.dto.PasswordDto;
import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.ResponsePostDto;
import com.sparta.post.entity.Post;
import com.sparta.post.repository.PostRepository;
import com.sparta.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ResponsePostDto createPost(@RequestBody PostRequestDto postDto) {
        return postService.createPost(postDto);

    }

    //게시글 수정
    @PutMapping("/api/posts/{id}")
    public ResponsePostDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    }


    //게시글 삭제
    @DeleteMapping("/api/posts/{id}")
    public String deletePost(@PathVariable Long id, @RequestBody PasswordDto requestDto) {
        return postService.deletePost(id, requestDto);
    }

}