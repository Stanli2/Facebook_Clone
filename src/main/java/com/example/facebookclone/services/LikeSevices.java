package com.example.facebookclone.services;

import com.example.facebookclone.model.UserDetails;

public interface LikeSevices {
    boolean likePost(UserDetails user, Long postId, String action);
}
