package com.example.facebookclone.controller;


import com.example.facebookclone.model.Post;
import com.example.facebookclone.model.UserDetails;
import com.example.facebookclone.services.serviceimplementation.PostServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class PostController {

    private final PostServiceImplementation postServiceImplementation;


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
    public String createPost(@ModelAttribute Post post, HttpSession httpSession, Model model) {
        System.out.println("Code reaches here");
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("user");
        Post post1 = new Post();

        post1.setUserDetails(userDetails);
        post1.setTitle(post.getTitle());
        post1.setMessage(post.getMessage());
        postServiceImplementation.createPost(post1);

        postServiceImplementation.viewHomePage(model);

        return "redirect:/home";
    }

    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable String postId, HttpSession session) {
        UserDetails userDetails = (UserDetails) session.getAttribute("user");
        Post post = postServiceImplementation.getPostById(Long.parseLong(postId));
        boolean validUser = post.getUserDetails().equals(userDetails);
        if (validUser) {
            postServiceImplementation.deletePost(post);
        }
        return "redirect:/home";
    }

    @GetMapping("/editform/{id}")
    public String editpost(@PathVariable(value = "id") Long id, Model model) {
        Post post = postServiceImplementation.getPostById(id);

        //set post as a model to pre-populate the form
        model.addAttribute("post", post);
        return "editpost";

    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable String id, HttpSession session, @RequestParam(value = "message") String message, Model model, @RequestParam(value = "title") String title) {
        UserDetails userDetails = (UserDetails) session.getAttribute("user");
        Post post = postServiceImplementation.getPostById(Long.parseLong(id));
        boolean validOwner = post.getUserDetails().equals(userDetails);
        if (validOwner) {
            post.setTitle(title);
            post.setMessage(message);
            post.setUserDetails(userDetails);
            postServiceImplementation.createPost(post);

        }
        postServiceImplementation.viewHomePage(model);
        return "redirect:/home";
    }

}
