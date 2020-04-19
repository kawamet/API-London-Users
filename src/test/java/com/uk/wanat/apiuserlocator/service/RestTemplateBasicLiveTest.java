package com.uk.wanat.apiuserlocator.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.uk.wanat.apiuserlocator.handler.RestTemplateResponseErrorHandler;
import com.uk.wanat.apiuserlocator.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class RestTemplateBasicLiveTest {


    private RestTemplate restTemplate;
    public static final String URL_ALL = "https://bpdts-test-app.herokuapp.com/users";
    public static final String ULR_LODNON = "https://bpdts-test-app.herokuapp.com/city/London/users";

    @Before
    public void beforeTest() {
        restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
    }

    // URL_ALL

    @Test
    public void givenResourceURL_ALL_whenSendGetForRequestEntity_thenStatusOk() throws IOException {
         final ResponseEntity<List<User>> rateResponse =
                restTemplate.exchange(URL_ALL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});

        assertThat(rateResponse.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void givenResourceURL_ALL_whenSendGetForRequestEntity_thenBodyCorrect() throws IOException {
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> response = template.getForEntity(URL_ALL, String.class);

        final ObjectMapper mapper = new JsonMapper();
        final JsonNode root = mapper.readTree(response.getBody());
        final JsonNode name = root.path("first_name");
        assertThat(name.asText(), notNullValue());
    }

    @Test
    public void givenResourceURL_ALL_whenRetrievingResource_thenCorrect() throws IOException {
        final List<User> rateResponse =
                restTemplate.exchange(URL_ALL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {}).getBody();

        assertThat(rateResponse.get(0).getFirstName(), notNullValue());
        assertThat(rateResponse.get(0).getClass(), is(User.class));
    }

    // HEAD, OPTIONS

    @Test
    public void givenUserService_whenCallHeadForHeaders_thenReceiveAllHeadersForThatResource() {
        final HttpHeaders httpHeaders = restTemplate.headForHeaders(URL_ALL);
        assertTrue(httpHeaders.getContentType()
            .includes(MediaType.APPLICATION_JSON));
    }

    //URL_LONDON

    @Test
    public void givenResourceULR_LODNON_whenSendGetForRequestEntity_thenStatusOk() throws IOException {
        final ResponseEntity<List<User>> rateResponse =
                restTemplate.exchange(ULR_LODNON, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});

        assertThat(rateResponse.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void givenResourceULR_LODNON_whenSendGetForRequestEntity_thenBodyCorrect() throws IOException {
        final RestTemplate template = new RestTemplate();
        final ResponseEntity<String> response = template.getForEntity(ULR_LODNON, String.class);

        final ObjectMapper mapper = new JsonMapper();
        final JsonNode root = mapper.readTree(response.getBody());
        final JsonNode name = root.path("first_name");
        assertThat(name.asText(), notNullValue());
    }

    @Test
    public void givenResourceULR_LODNON_whenRetrievingResource_thenCorrect() throws IOException {
        final List<User> rateResponse =
                restTemplate.exchange(ULR_LODNON, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {}).getBody();

        assertThat(rateResponse.get(0).getFirstName(), notNullValue());
        assertThat(rateResponse.get(0).getClass(), is(User.class));
    }

    // HEAD, OPTIONS

    @Test
    public void givenuUserService_ULR_LODNON_whenCallHeadForHeaders_thenReceiveAllHeadersForThatResource() {
        final HttpHeaders httpHeaders = restTemplate.headForHeaders(ULR_LODNON);
        assertTrue(httpHeaders.getContentType()
                .includes(MediaType.APPLICATION_JSON));
    }


    // Simply setting restTemplate timeout using ClientHttpRequestFactory

    ClientHttpRequestFactory getClientHttpRequestFactory() {
        final int timeout = 5;
        final HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout * 1000);
        return clientHttpRequestFactory;
    }

}
