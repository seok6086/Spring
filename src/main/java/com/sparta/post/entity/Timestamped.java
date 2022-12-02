package com.sparta.post.entity;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter //접근 자동 생성
@MappedSuperclass   //Entity가 자동으로 Column으로 인식
@EntityListeners(AuditingEntityListener.class)  //생성, 변경 시간을 자동으로 업데이트
public class Timestamped {

    @CreatedDate                //생성일자
    private LocalDateTime createdAt;

    @LastModifiedDate           //수정 일자
    private LocalDateTime modifiedAt;
}