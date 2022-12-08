package com.sparta.post.service;

import com.sparta.post.dto.PasswordDto;
import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.ResponseDto;
import com.sparta.post.dto.ResponsePostDto;
import com.sparta.post.entity.Post;
import com.sparta.post.entity.User;
import com.sparta.post.jwt.JwtUtil;
import com.sparta.post.repository.PostRepository;
import com.sparta.post.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;


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
    public ResponsePostDto createPost(PostRequestDto postDto, HttpServletRequest request) {
        //request로 토큰 가져오고
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null){
            if(jwtUtil.validateToken(token)){
                claims = jwtUtil.getUserInfoFromToken(token);
            }else{
                throw new IllegalArgumentException("Token fail");
            }
            //토큰에서 가져온 정보로 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않은 회원입니다.")
            );
            
            Post post = postRepository.saveAndFlush(new Post(postDto, user.getId(), user.getUsername()));

            return new ResponsePostDto(post);
        }else{
            return null;
        }
    }

    //수정
    @Transactional
    public Post updatePost(Long id, PostRequestDto requestDto, HttpServletRequest request) {
        //Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        //토큰이 있는 경우에만 게시글 수정 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                //토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            //토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = (Post) postRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 게시글은 존재하지 않습니다.")
            );

            post.updatePost(requestDto);
            return post;
        } else {
            return null;
        }
    }

    //삭제
    @Transactional
    public ResponseDto deletePost(Long id, HttpServletRequest request) {
        //Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        //토큰이 있는 경우에만 게시글 삭제 가능
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                //토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            //토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Post post = (Post) postRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 게시글은 존재하지 않습니다.")
            );

            postRepository.deleteById(id);
            return new ResponseDto("게시글 삭제 성공", HttpStatus.OK.value());
        } else {
            return null;
        }
    }

    }











 /*
 public String deletePost(Long id, PasswordDto requestDto){
    Post post = postRepository.findbyId(id).orElseThrow(
        () -> new IllegalArgunentException("아이디가 존재하지 않습니다.")
        );
        if(post.getPassword().equals(requestDto.getPassword())){
        postRepository.deletByUd(id);
        }else {
            throw new IllehalArgumentException("비밀번호가 틀렸습니다.");
            }
            return("게시글 삭제 성공 ! " )
 }
  */