package com.example.facebookclone.services.serviceimplementation;


import com.example.facebookclone.model.Comments;
import com.example.facebookclone.model.Post;
import com.example.facebookclone.repository.CommentRespository;
import com.example.facebookclone.repository.PostRepository;
import com.example.facebookclone.services.PostServices;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Service
public class PostServiceImplementation implements PostServices {

    private PostRepository postRepository;
    private CommentRespository commentRespository;

    public PostServiceImplementation(PostRepository postRepository, CommentRespository commentRespository) {
        this.postRepository = postRepository;
        this.commentRespository = commentRespository;
    }

    @Override
    public void createPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findPostById(id);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }


    public void viewHomePage(Model model) {
        Post post = new Post();
        Comments comments = new Comments();
        CommentServiceImplementation serviceImplementation = new CommentServiceImplementation(commentRespository);
        List<Post> posts = this.getAllPost();
        Collections.reverse(posts);
        List<Comments> listOfComment = serviceImplementation.getAllComments();

        model.addAttribute("posts", posts);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("comment", listOfComment);


    }
}
