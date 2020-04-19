package com.uk.wanat.apiuserlocator.exception;

import com.uk.wanat.apiuserlocator.DTO.UserDTO;
import com.uk.wanat.apiuserlocator.controller.UserController;
import com.uk.wanat.apiuserlocator.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class ResourceNotFoundExceptionTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void givenNull_whenGetDtoLondonUser_thenReturnNotFound() throws Exception {

        ArrayList<UserDTO> allLondonUsers = null;

        given(userService.getDtoLondonUser()).willReturn(allLondonUsers);

        mvc.perform(get("/londonUsers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}