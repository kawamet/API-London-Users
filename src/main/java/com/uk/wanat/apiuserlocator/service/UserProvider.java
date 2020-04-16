package com.uk.wanat.apiuserlocator.service;


import com.uk.wanat.apiuserlocator.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserProvider {

    public static final String URL_ALL = "https://bpdts-test-app.herokuapp.com/users";
    public static final String ULR_LODNON = "https://bpdts-test-app.herokuapp.com/city/London/users";

    public List<User> getAllUsers(){
        return getData(URL_ALL);
    }

    public List<User> getLondonUsers(){
        return getData(ULR_LODNON);
    }

    private List<User> getData(String URL) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<User>> rateResponse =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        return rateResponse.getBody();
    }

}
