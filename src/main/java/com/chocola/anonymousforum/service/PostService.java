package com.chocola.anonymousforum.service;

import com.chocola.anonymousforum.data.SearchStandard;
import com.chocola.anonymousforum.data.dto.CreatePostDto;
import com.chocola.anonymousforum.data.dto.ModifyPostDto;
import com.chocola.anonymousforum.data.entity.Post;
import com.chocola.anonymousforum.data.repository.PostRepository;
import com.chocola.anonymousforum.exception.NoMatchPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Page<Post> findAll(int page, String searchStandard, String searchContent) {
        SearchStandard standard = SearchStandard.valueOf(searchStandard);
        return postRepository.findAll(PageRequest.of(page, 10), standard.getValue(), searchContent);
    }

    public void save(CreatePostDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setWriter(dto.getWriter());
        post.setPassword(dto.getPassword());

        postRepository.save(post);
    }

    public void delete(Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow();

        checkPostExisted(post);
        checkPassword(post, password);

        post.setExist(false);
    }

    public void modify(Long id, ModifyPostDto dto) {
        Post post = postRepository.findById(id).orElseThrow();

        checkPostExisted(post);

        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setWriter(dto.getWriter());
        post.setPassword(dto.getPassword());
    }

    private void checkPostExisted(Post post) {
        if (!post.isExist()) {
            throw new NoSuchElementException("삭제된 게시글입니다.");
        }
    }

    private void checkPassword(Post post, String password) {
        if (!post.getPassword().equals(password)) {
            throw new NoMatchPasswordException("비밀번호가 일치하지 않습니다.");
        }
    }
}
