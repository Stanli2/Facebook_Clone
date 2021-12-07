package com.example.facebookclone.services.serviceimplementation;

import com.example.facebookclone.model.Comments;
import com.example.facebookclone.repository.CommentRespository;
import com.example.facebookclone.services.CommentServices;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImplementation implements CommentServices {

    private CommentRespository commentRespository;

    public CommentServiceImplementation(CommentRespository commentRespository) {
        this.commentRespository = commentRespository;
    }

    @Override
    public void createComment(Comments comments) {
        commentRespository.save(comments);
    }

    @Override
    public Comments getCommentById(Long id) {
        return commentRespository.findCommentsById(id);
    }

    @Override
    public void deleteComment(Comments comments) {
        commentRespository.delete(comments);
    }

    @Override
    public List<Comments> getAllComments() {
        return commentRespository.findAll();
    }
}
