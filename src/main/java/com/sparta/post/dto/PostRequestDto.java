package com.sparta.post.dto;

import com.sparta.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String username;
    private String contents;
    private String password;
    private String title;
    private Long id;

    public PostRequestDto(Post post){
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.password = post.getPassword();
        this.title = post.getTitle();
        this.id=post.getId();
    }

}