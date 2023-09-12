package com.chocola.anonymousforum.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostDto {

    private String title;
    private String content;
    private String writer;
    private String password;
}
