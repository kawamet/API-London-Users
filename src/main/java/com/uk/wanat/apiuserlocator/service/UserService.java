package com.uk.wanat.apiuserlocator.service;

import com.uk.wanat.apiuserlocator.UserProvider;
import com.uk.wanat.apiuserlocator.model.User;
import com.uk.wanat.apiuserlocator.model.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserProvider userProvider;
    private ModelMapper modelMapper;

    @Autowired
    public UserService(UserProvider userProvider, ModelMapper modelMapper) {
        this.userProvider = userProvider;
        this.modelMapper = modelMapper;
    }

    public List<UserDTO> convertToDtoAllUsers(){
         return getAllUsers().stream().map(e -> convertToDto(e)).collect(Collectors.toList());
    }

    public List<UserDTO> convertToDtoLondonUser(){
        return getLondonUsers().stream().map(e -> convertToDto(e)).collect(Collectors.toList());
    }



    private UserDTO convertToDto(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setId(user.getId());
        return userDTO;
    }
    private List<User> getAllUsers() {
        return userProvider.getAllUsers();
    }

    private List<User> getLondonUsers() {
        return userProvider.getLondonUsers();
    }
}
