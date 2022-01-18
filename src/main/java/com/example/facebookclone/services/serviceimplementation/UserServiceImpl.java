package com.example.facebookclone.services.serviceimplementation;

import com.example.facebookclone.model.UserDetails;
import com.example.facebookclone.repository.UserDetailRepository;
import com.example.facebookclone.services.Services;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class UserServiceImpl implements Services {


    private final UserDetailRepository userDetailRepository;


    @Autowired
    public UserServiceImpl(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    @Override
    public List<UserDetails> findAllUsers() {
        return userDetailRepository.findAll();
    }

    @Override
    public UserDetails saveUser(UserDetails userDetails) {
        if (userDetailRepository.findByEmail(userDetails.getEmail()).isPresent()) {
            System.out.println("You can't repeat this mail");
            return null;
        }
        return userDetailRepository.save(userDetails);
    }

    @Override
    public UserDetails login(String email, String password) {
        return userDetailRepository.findByEmailAndPassword(email, password).orElse(null);
    }

}
