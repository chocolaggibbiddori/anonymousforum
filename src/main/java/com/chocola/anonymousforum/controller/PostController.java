package com.chocola.anonymousforum.controller;

import com.chocola.anonymousforum.data.dto.CreatePostDto;
import com.chocola.anonymousforum.data.entity.Post;
import com.chocola.anonymousforum.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;

    @ResponseStatus(OK)
    @GetMapping
    public List<Post> posts(@RequestParam(required = false, defaultValue = "1") int page,
                            @RequestParam(required = false, defaultValue = "DEFAULT") String searchStandard,
                            @RequestParam(required = false, defaultValue = "") String searchContent) {
        Page<Post> posts = postService.findAll(page - 1, searchStandard, searchContent);
        return posts.getContent();
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public void writePost(@RequestBody CreatePostDto dto) {
        postService.save(dto);
    }
}
