package com.sparta.post.dto;

import com.sparta.post.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponsePostDto {

    private String username;
    private String contents;
    private String password;
    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
    private Long id;


    public ResponsePostDto(Post post){
        this.username = post.getUsername();
        this.contents = post.getContents();
        this.password = post.getPassword();
        this.title = post.getTitle();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.id = post.getId();
    }
}