package com.example.facebookclone.repository;

import com.example.facebookclone.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRespository extends JpaRepository<Comments, Long> {
    Comments findCommentsById(Long id);
//    List<Comments> findAllComments();
}
