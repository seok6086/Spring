package com.sparta.post.dto;

import com.sparta.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordDto {

    private String password;

    public PasswordDto(Post post){

        this.password = post.getPassword();

    }
}