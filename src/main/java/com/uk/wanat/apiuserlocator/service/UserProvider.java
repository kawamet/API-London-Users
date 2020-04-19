package com.uk.wanat.apiuserlocator.service;


import com.uk.wanat.apiuserlocator.handler.RestTemplateResponseErrorHandler;
import com.uk.wanat.apiuserlocator.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserProvider {

    public static final String URL_ALL = "https://bpdts-test-app.herokuapp.com/users";
    public static final String ULR_LODNON = "https://bpdts-test-app.herokuapp.com/city/London/users";
    private RestTemplate restTemplate;

    private static final Logger logger = LoggerFactory.getLogger(UserProvider.class);

    public UserProvider(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public List<User> getAllUsers(){
        return getData(URL_ALL);
    }

    public List<User> getLondonUsers(){
        return getData(ULR_LODNON);
    }

    private List<User> getData(String URL) {
        restTemplate = new RestTemplate();
        ResponseEntity<List<User>> rateResponse =
                restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});

        return rateResponse.getStatusCode() == HttpStatus.OK ? rateResponse.getBody() : null;
    }



}
