package com.example.facebookclone.controller;


import com.example.facebookclone.model.UserDetails;
import com.example.facebookclone.services.serviceimplementation.PostServiceImplementation;
import com.example.facebookclone.services.serviceimplementation.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserDetailController {

    private UserServiceImpl userService;
    private PostServiceImplementation postServiceImplementation;

    public UserDetailController(UserServiceImpl userService, PostServiceImplementation postServiceImplementation) {

        this.userService = userService;
        this.postServiceImplementation = postServiceImplementation;
    }

    @GetMapping("/")
    public String register(Model model) {
        model.addAttribute("registerRequest", new UserDetails());
        return "signup";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginRequest", new UserDetails());
        return "signin";
    }

    @GetMapping("/home")
    public String viewHome(Model model){
        postServiceImplementation.viewHomePage(model);
        return "home";
    }

    @PostMapping("/signup")
    public ModelAndView save(@ModelAttribute UserDetails userDetails) {

        System.out.println(userDetails);
        UserDetails userDetail = new UserDetails();
        userDetail.setFirst_name(userDetails.getFirst_name());
        userDetail.setLast_name(userDetails.getLast_name());
        userDetail.setEmail(userDetails.getEmail());
        userDetail.setPassword(userDetails.getPassword());
        userDetail.setDate_of_birth(userDetails.getDate_of_birth());
        userDetail.setGender(userDetails.getGender());

        UserDetails userDetails1 = userService.saveUser(userDetails);
        System.out.println(userDetails1);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signin");
        modelAndView.addObject("userDetail",userDetails);
        return modelAndView;
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserDetails userDetails, Model model, HttpSession httpSession, Model model1) {
        System.out.println("Login request: " + userDetails);
        UserDetails loggedIn = userService.login(userDetails.getEmail(), userDetails.getPassword());
        if(loggedIn != null) {
            httpSession.setAttribute("user", loggedIn);
            model.addAttribute("userLogin", loggedIn.getFirst_name() + " " + loggedIn.getLast_name());

            postServiceImplementation.viewHomePage(model1);
            return "home";
        } else {
            return "signin";
        }
    }
}
