package com.example.facebookclone.controller;

import com.example.facebookclone.model.Comments;
import com.example.facebookclone.model.Post;
import com.example.facebookclone.model.UserDetails;
import com.example.facebookclone.services.serviceimplementation.CommentServiceImplementation;
import com.example.facebookclone.services.serviceimplementation.PostServiceImplementation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class CommentsController {

    private final CommentServiceImplementation commentServiceImplementation;
    private final PostServiceImplementation postServiceImplementation;


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

        return "redirect:/home";
    }

    @GetMapping("/deleteComment/{commentId}")
    public String deleteComment(@PathVariable String commentId, HttpSession session, Model model) {
        UserDetails userDetails = (UserDetails) session.getAttribute("user");
        Comments comments = commentServiceImplementation.getCommentById(Long.parseLong(commentId));
        boolean validUser = comments.getUser().equals(userDetails);
        if (validUser) {
            commentServiceImplementation.deleteComment(comments);
        }
        postServiceImplementation.viewHomePage(model);
        return "redirect:/home";
    }

    @GetMapping("/editComment/{commentId}")
    public String editCommentForm(@PathVariable(value = "commentId") Long commentId, Model model) {
        Comments comments = commentServiceImplementation.getCommentById(commentId);

        model.addAttribute("comment", comments);

        return "editComment";
    }

    @PostMapping("/updateComment/{commentId}")
    public String updateComment(@PathVariable String commentId, HttpSession session, @RequestParam(value = "comment") String comment, Model model) {
        UserDetails userDetails = (UserDetails) session.getAttribute("user");
        Comments comments = commentServiceImplementation.getCommentById(Long.parseLong(commentId));
        boolean validOwner = comments.getUser().equals(userDetails);
        if (validOwner) {
            comments.setComment(comment);
            commentServiceImplementation.createComment(comments);

        }
        postServiceImplementation.viewHomePage(model);
        return "redirect:/home";
    }

}

