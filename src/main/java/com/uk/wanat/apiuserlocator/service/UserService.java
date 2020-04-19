package com.uk.wanat.apiuserlocator.service;

import com.uk.wanat.apiuserlocator.model.User;
import com.uk.wanat.apiuserlocator.DTO.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    public static final double LONDON_LAT = 51.50853;
    public static final double LONDON_LONG = -0.12574;

    private UserProvider userProvider;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserProvider userProvider, ModelMapper modelMapper) {
        this.userProvider = userProvider;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> getDtoLondonAreaUsers(){
         return getUserWithInDistance().stream().map(e -> convertToDto(e)).collect(Collectors.toList());
    }

    public List<UserDTO> getDtoLondonUser(){
        return getLondonUsers().stream().map(e -> convertToDto(e)).collect(Collectors.toList());
    }


    private UserDTO convertToDto(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setId(user.getId());
        return userDTO;
    }

    private List<User> getLondonUsers() {
        return userProvider.getLondonUsers();
    }

    private List<User> getUserWithInDistance(){
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
