package com.uk.wanat.apiuserlocator.controller;

import com.uk.wanat.apiuserlocator.DTO.UserDTO;
import com.uk.wanat.apiuserlocator.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void givenUsers_whenGetDtoLondonUser_thenReturnJsonArray() throws Exception {

        UserDTO userDTO = new UserDTO(135, "Mechelle", "Boam");
        List<UserDTO> allLondonUsers = Arrays.asList(userDTO);

        given(userService.getDtoLondonUser()).willReturn(allLondonUsers);

        mvc.perform(get("/londonUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(userDTO.getFirstName())));
    }

    @Test
    public void givenUsers_whenGetDtoLondonAreaUsers_thenReturnJsonArray()
            throws Exception {

        UserDTO userDTO = new UserDTO(135, "Mechelle", "Boam");
        List<UserDTO> allLondonAreaUsers = Arrays.asList(userDTO);

        given(userService.getDtoLondonAreaUsers()).willReturn(allLondonAreaUsers);

        mvc.perform(get("/londonAreaUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(userDTO.getFirstName())));
    }

}