package com.example.facebookclone.repository;

import com.example.facebookclone.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostById(Long id);
}
