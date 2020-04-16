package com.uk.wanat.apiuserlocator.controller;


import com.uk.wanat.apiuserlocator.DTO.UserDTO;
import com.uk.wanat.apiuserlocator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @RequestMapping("/londonUsers")
    public List<UserDTO> returnUsers() {
        return userService.getDtoLondonUser();
    }

    @GetMapping
    @RequestMapping("/londonAreaUsers")
    public List<UserDTO> returnLondonAreaUsers() {
        return userService.getDtoLondonAreaUsers();
    }

}
