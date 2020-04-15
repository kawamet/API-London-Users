package com.uk.wanat.apiuserlocator.controller;


import com.uk.wanat.apiuserlocator.model.User;
import com.uk.wanat.apiuserlocator.UserProvider;
import com.uk.wanat.apiuserlocator.model.UserDTO;
import com.uk.wanat.apiuserlocator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserControler {

    @Autowired
    private final UserService userService;
    private final UserProvider userProvider;
    public static final double LONDON_LAT = 51.50853;
    public static final double LONDON_LONG = -0.12574;


    public UserControler(UserProvider userProvider, UserService userService) {
        this.userService = userService;
        this.userProvider = userProvider;
    }

    @GetMapping
    @RequestMapping("/londonUsers")
    public List<UserDTO> returnUsers(){
        return userService.convertToDtoLondonUser();
    }


    //todo have to move method to services
    //todo tests
    @GetMapping
    @RequestMapping("/londonArea")
    public List<User> returnLondonAreaUsers(){
        List<User> usersLondonAreaList =  new ArrayList<>();

        for (User user : userProvider.getAllUsers()) {
            double dist = org.apache.lucene.util.SloppyMath.haversinMeters(LONDON_LAT, LONDON_LONG, user.getLatitude(), user.getLongitude()) * 0.000621371;
            if (dist < 50){
                usersLondonAreaList.add(user);
            }
        }
        return usersLondonAreaList;
    }


}
