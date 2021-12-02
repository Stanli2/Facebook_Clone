package com.example.facebookclone.repository;

import com.example.facebookclone.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    Post findPostById(Long id);
}
