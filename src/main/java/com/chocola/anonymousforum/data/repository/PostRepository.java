package com.chocola.anonymousforum.data.repository;

import com.chocola.anonymousforum.data.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByTitleContainsAndExistIsTrue(Pageable pageable, String searchContent);
    Page<Post> findAllByCreatedDateLessThanAndExistIsTrue(Pageable pageable, LocalDateTime localDateTime);
}
