package com.chocola.anonymousforum.data.repository;

import com.chocola.anonymousforum.data.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE title LIKE :standard AND content LIKE :content")
    Page<Post> findAll(Pageable pageable, @Param("standard") String searchStandard, @Param("content") String searchContent);
}
