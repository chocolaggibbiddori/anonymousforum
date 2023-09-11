package com.chocola.anonymousforum.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String password;

    @CreatedDate
    private LocalDateTime createdDate;

    public Post(String title, String content, String writer, String password) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
    }
}
