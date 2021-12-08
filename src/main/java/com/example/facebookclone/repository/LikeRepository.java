package com.example.facebookclone.repository;

import com.example.facebookclone.model.Likes;
import com.example.facebookclone.model.Post;
import com.example.facebookclone.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {

}
