package com.example.facebookclone.services;

import com.example.facebookclone.model.Post;
import org.springframework.stereotype.Service;

@Service
public interface PostServices {

    public void createPost (Post post);

    public Post getPostById(Long id);

    public void deletePost(Post post);

    public Iterable<Post> getAllPost();


}
