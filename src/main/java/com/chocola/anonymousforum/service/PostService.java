package com.chocola.anonymousforum.service;

import com.chocola.anonymousforum.data.SearchStandard;
import com.chocola.anonymousforum.data.dto.CreatePostDto;
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
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostRepository postRepository;

    public Page<Post> findAll(int page, String searchStandard, String searchContent) {
        SearchStandard standard = SearchStandard.valueOf(searchStandard);
        return postRepository.findAll(PageRequest.of(page, 10), standard.getValue(), searchContent);
    }

    @Transactional
    public void save(CreatePostDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        post.setWriter(dto.getWriter());
        post.setPassword(dto.getPassword());

        postRepository.save(post);
    }

    @Transactional
    public void delete(Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow();

        if (!post.isExist()) {
            throw new NoSuchElementException("삭제된 게시글입니다.");
        }

        if (!post.getPassword().equals(password)) {
            throw new NoMatchPasswordException("비밀번호가 일치하지 않습니다.");
        }

        post.setExist(false);
    }
}
