package com.danilermolenko.buysale.controllers;

import com.danilermolenko.buysale.dto.UserDto;
import com.danilermolenko.buysale.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/registration")
    public String registration(UserDto userDto, Model model){
        if(!userService.addUser(userDto)){
            model.addAttribute("error", "user is already exists");
            return "registration";
        }
        userService.addUser(userDto);
        return "redirect:/login";
    }
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
