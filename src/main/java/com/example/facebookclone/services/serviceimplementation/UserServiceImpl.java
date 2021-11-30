package com.example.facebookclone.services.serviceimplementation;

import com.example.facebookclone.model.UserDetails;
import com.example.facebookclone.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private UserDetailRepository userDetailRepository;
    private UserDetails userDetails;

    @Autowired
    public UserServiceImpl(UserDetailRepository userDetailRepository) {
        this.userDetailRepository = userDetailRepository;
    }

    public List<UserDetails> findAllUser(){

        return userDetailRepository.findAll();
    }

    public UserDetails saveUser(UserDetails userDetails){

        return userDetailRepository.save(userDetails);
    }

    public Optional<UserDetails> login(UserDetails userDetails) {
        return userDetailRepository.findByEmailAndPassword(userDetails.getEmail(), userDetails.getPassword());
    }
}
