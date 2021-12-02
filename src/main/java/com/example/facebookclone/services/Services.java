package com.example.facebookclone.services;


import com.example.facebookclone.model.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Services {
    List<UserDetails> findAllUsers();

    UserDetails saveUser(UserDetails userDetails);

    UserDetails login(String email, String password);
}
