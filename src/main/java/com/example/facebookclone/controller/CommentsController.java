package com.example.facebookclone.controller;

import com.example.facebookclone.model.Comments;
import com.example.facebookclone.model.Post;
import com.example.facebookclone.model.UserDetails;
import com.example.facebookclone.services.serviceimplementation.CommentServiceImplementation;
import com.example.facebookclone.services.serviceimplementation.PostServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentsController {

    private CommentServiceImplementation commentServiceImplementation;
    private PostServiceImplementation postServiceImplementation;


    public CommentsController(CommentServiceImplementation commentServiceImplementation, PostServiceImplementation postServiceImplementation) {
        this.commentServiceImplementation = commentServiceImplementation;
        this.postServiceImplementation = postServiceImplementation;
    }

    @GetMapping("/createCommentForm/{id}")
    public String commentForm(@PathVariable(value = "id") Long id, Model model) {
        Post post = postServiceImplementation.getPostById(id);
        model.addAttribute("post", post);
        return "comment_form";
    }

    @PostMapping("/createComment/{id}")
    public String createComment(@ModelAttribute Comments comments, HttpSession session, Model model, @PathVariable String id) {
        UserDetails userDetails = (UserDetails) session.getAttribute("user");
        Post post = postServiceImplementation.getPostById(Long.parseLong(id));
        Comments comments1 = new Comments();

        comments1.setUser(userDetails);
        comments1.setPost(post);
        comments1.setComment(comments.getComment());
        commentServiceImplementation.createComment(comments1);

        postServiceImplementation.viewHomePage(model);

        return "home";
    }
}
