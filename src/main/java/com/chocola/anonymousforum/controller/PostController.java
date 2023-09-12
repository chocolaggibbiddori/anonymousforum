package com.chocola.anonymousforum.controller;

import com.chocola.anonymousforum.data.dto.CreatePostDto;
import com.chocola.anonymousforum.data.entity.Post;
import com.chocola.anonymousforum.exception.NoMatchPasswordException;
import com.chocola.anonymousforum.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.*;

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

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id, @RequestParam String password) {
        log.info("password={}", password);
        postService.delete(id, password);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(NoSuchElementException.class)
    public String exceptionHandle(NoSuchElementException e) {
        log.error("[PostController] Error!! : {}", e.getMessage());
        return "옳지 않은 파라미터입니다.";
    }

    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(NoMatchPasswordException.class)
    public String exceptionHandle(NoMatchPasswordException e) {
        log.error("[PostController] Error!! : {}", e.getMessage());
        return "비밀번호가 일치하지 않습니다.";
    }
}
