package com.example.facebookclone.services.serviceimplementation;


import com.example.facebookclone.model.Post;
import com.example.facebookclone.repository.PostRepository;
import com.example.facebookclone.services.PostServices;

@org.springframework.stereotype.Service
public class PostServiceImplementation implements PostServices{

    private PostRepository postRepository;

    public PostServiceImplementation(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void createPost(Post post) {

    }

    @Override
    public Post getPostById(Long id) {
        return null;
    }

    @Override
    public void deletePost(Post post) {

    }

    @Override
    public Iterable<Post> getAllPost() {
        return null;
    }
}
