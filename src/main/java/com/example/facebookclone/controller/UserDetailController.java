package com.example.facebookclone.controller;


import com.example.facebookclone.model.UserDetails;
import com.example.facebookclone.services.serviceimplementation.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UserDetailController {

    private UserServiceImpl userService;

    public UserDetailController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index() {

        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST)
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

    @PostMapping
}
