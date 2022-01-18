package com.example.facebookclone.services;

import com.example.facebookclone.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostServices {

    void createPost(Post post);

    Post getPostById(Long id);

    void deletePost(Post post);

    List<Post> getAllPost();


}
