package com.chocola.anonymousforum.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;
    private String content;

    @Column(nullable = false, length = 20)
    private String writer;

    @Column(nullable = false, length = 30)
    private String password;
    private boolean exist = true;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    public Post(String title, String content, String writer, String password) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.password = password;
    }
}
