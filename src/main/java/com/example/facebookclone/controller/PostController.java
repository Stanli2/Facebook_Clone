package com.example.facebookclone.controller;

import com.example.facebookclone.model.Comments;
import com.example.facebookclone.model.Post;
import com.example.facebookclone.model.UserDetails;
import com.example.facebookclone.services.serviceimplementation.PostServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("Session user is " + userDetails.getFirst_name() + " " + userDetails.getLast_name());
        return "post";
    }



    @PostMapping("/creates")
    public String createPost(@ModelAttribute Post post, HttpSession httpSession, Model model, Model model1) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("user");
        Post post1 = new Post();

        post1.setUserDetails(userDetails);
        post1.setTitle(post.getTitle());
        post1.setMessage(post.getMessage());
        postServiceImplementation.createPost(post1);

        model.addAttribute("postUser", post1.getUserDetails().getFirst_name() + " " + post1.getUserDetails().getLast_name());
        model.addAttribute("postTitle", post1.getTitle());
        model.addAttribute("postContent", post1.getMessage());
        System.out.println("Post was created with id: " + post1.getId() + ". Created by " + post1.getUserDetails().getFirst_name());

        postServiceImplementation.viewHomePage(model1);

        return "/home";
    }

    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable String postId, HttpSession session) {
        UserDetails userDetails = (UserDetails) session.getAttribute("user");
        Post post = postServiceImplementation.getPostById(Long.parseLong(postId));
        boolean validUser = post.getUserDetails().equals(userDetails);
        if (validUser){
            postServiceImplementation.deletePost(post);
        }
        return "home";
    }

    @PostMapping("/update/{postId}")
    public String updatePost(@PathVariable String postId, HttpSession session, @RequestParam(value = "content") String message) {
        UserDetails userDetails = (UserDetails) session.getAttribute("user");
        Post post = postServiceImplementation.getPostById(Long.parseLong(postId));
        boolean validOwner = post.getUserDetails().equals(userDetails);
        if(validOwner) {
            post.setMessage(message);
            post.setUserDetails(userDetails);
            postServiceImplementation.updatePost(post.getId());
        }
        return "home";
    }
}
