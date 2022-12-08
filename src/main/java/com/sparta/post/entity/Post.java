package com.sparta.post.entity;


import com.sparta.post.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Getter
@Entity
@NoArgsConstructor

public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //제목
    @Column(nullable = false)
    private String title;
    //유저이름
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private Long userId;

    //패스워드
    @Column(nullable = false)
    private String password;

    //내용
    @Column(nullable = false)
    private String contents;

    public Post(PostRequestDto requestDto, Long id, String username) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.userId = id;
    }


    public void updatePost(PostRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
    }
}