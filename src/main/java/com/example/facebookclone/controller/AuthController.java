package com.example.facebookclone.controller;


import com.example.facebookclone.model.UserDetails;
import com.example.facebookclone.services.serviceimplementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class AuthController {

    @Autowired
    private UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String home() {
        return "home";


//    public ModelAndView login() {
//        ModelAndView mav = new ModelAndView("signup");
//        mav.addObject("user", userService);
//        return mav;
//    }
//
//    @PostMapping(value = "/home")
//    public String login(@ModelAttribute UserDetails userDetails) {
//
//        UserDetails userDetails1 = new UserDetails();
//        userDetails1.setEmail(userDetails.getEmail());
//        userDetails1.setPassword(userDetails.getPassword());
//
//        Optional<UserDetails> userDetails2 = userService.login(userDetails);
//        System.out.println(userDetails2);
//
//        if(userDetails2.isPresent()) {
//            if(userDetails2.get().getPassword().equals(userDetails1.getPassword())) {
//
//            }
//        }
//
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("home");
//        modelAndView.addObject("userDetail",userDetails);
//        return String.valueOf(modelAndView);
//
////        System.out.print(userDetails1);
////
////        return "redirect:home.html";


    }
}
