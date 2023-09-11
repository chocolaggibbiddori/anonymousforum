package com.chocola.anonymousforum.data.repository;

import com.chocola.anonymousforum.data.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
