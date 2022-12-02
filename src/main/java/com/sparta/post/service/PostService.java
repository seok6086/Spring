package com.sparta.post.service;

import com.sparta.post.dto.PasswordDto;
import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.ResponsePostDto;
import com.sparta.post.entity.Post;
import com.sparta.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;


    // 전체 게시물 조회
    @Transactional
    public List<ResponsePostDto> findAllPost() {
        List<Post> post = postRepository.findAll();

        List<ResponsePostDto> result = new ArrayList<>();

        for (Post p : post) {
            result.add(new ResponsePostDto(p));
        }
        return result;
    }

    //선택 게시물 조회
    @Transactional
    public ResponsePostDto findOnePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        return new ResponsePostDto(post);
    }


    //등록
    @Transactional
    public ResponsePostDto createPost(PostRequestDto postDto) {
        Post post = new Post(postDto);
        postRepository.save(post);
        return new ResponsePostDto(post);
    }

    //수정
    @Transactional
    public ResponsePostDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (post.getPassword().equals(requestDto.getPassword())) {
            post.updatePost(requestDto);

        } else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return new ResponsePostDto(post);
    }

    //삭제
    @Transactional
    public String deletePost(Long id, PasswordDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (post.getPassword().equals(requestDto.getPassword())) {
            postRepository.deleteById(id);

        } else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        return ("게시글 삭제 성공!");
    }
}