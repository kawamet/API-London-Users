package com.uk.wanat.apiuserlocator.controller;


import com.uk.wanat.apiuserlocator.DTO.UserDTO;
import com.uk.wanat.apiuserlocator.exception.ResourceNotFoundException;
import com.uk.wanat.apiuserlocator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> returnUsers() {
        if (userService.getDtoLondonUser() == null){
            throw new ResourceNotFoundException();
        }
        return userService.getDtoLondonUser();
    }

    @GetMapping
    @RequestMapping("/londonAreaUsers")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> returnLondonAreaUsers() {
        if (userService.getDtoLondonAreaUsers() == null){
            throw new ResourceNotFoundException();
        }
        return userService.getDtoLondonAreaUsers();
    }
}
