package com.chocola.anonymousforum.service;

import com.chocola.anonymousforum.data.SearchStandard;
import com.chocola.anonymousforum.data.dto.CreatePostDto;
import com.chocola.anonymousforum.data.entity.Post;
import com.chocola.anonymousforum.data.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
