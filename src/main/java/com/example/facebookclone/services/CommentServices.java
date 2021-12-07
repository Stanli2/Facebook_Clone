package com.example.facebookclone.services;

import com.example.facebookclone.model.Comments;

import java.util.List;

public interface CommentServices {

    void createComment(Comments comments);

    Comments getCommentById(Long id);

    void deleteComment(Comments comments);

    List<Comments> getAllComments();
}

