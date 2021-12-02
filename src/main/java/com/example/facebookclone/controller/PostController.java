package com.example.facebookclone.controller;

import com.example.facebookclone.model.Post;
import com.example.facebookclone.model.UserDetails;
import com.example.facebookclone.services.serviceimplementation.PostServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {

    private PostServiceImplementation postServiceImplementation;


    public PostController(PostServiceImplementation postServiceImplementation) {
        this.postServiceImplementation = postServiceImplementation;
    }

    @GetMapping("/post")
    public String postPage(HttpSession httpSession) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("user");
        return "post";
    }

    @PostMapping("/creates")
    public String createPost(@ModelAttribute Post post, HttpSession httpSession) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("user");
        Post post1 = new Post();

        post1.setUserDetails(userDetails);
        post1.setTitle(post.getTitle());
        post1.setDate_created(post.getDate_created());
        postServiceImplementation.createPost(post1);
        return "/home";
    }
}
