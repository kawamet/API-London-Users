package com.uk.wanat.apiuserlocator.service;

import com.uk.wanat.apiuserlocator.DTO.UserDTO;
import com.uk.wanat.apiuserlocator.model.User;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDtoUnitTest {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertUserEntityToUserDto_thenCorrect() {
        User user = new User();
        user.setId(1);
        user.setFirstName("Will");
        user.setLastName("Smith");

        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getFirstName(), userDTO.getFirstName());
        assertEquals(user.getLastName(), userDTO.getLastName());
    }
}
