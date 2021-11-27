package com.vumah.vumahapis;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.util.*;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        // set connection and read timeouts
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(500))
                .setReadTimeout(Duration.ofSeconds(500))
                .build();
    }

    public String getPostsPlainJSON() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        return this.restTemplate.getForObject(url, String.class);
    }

    public String getPostWithCustomHeaders(String code) throws Exception {

        String user = "21640";
        String pwd = "SIxh0Uim47LB6IsDN6sFDdueUQOBiB0a";

        String url = "https://api.endole.co.uk/company/" + code;
        String encoding = Base64.getEncoder().encodeToString((user + ":" + pwd).getBytes("UTF-8"));


        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        // set custom header
        headers.set("Authorization", "Basic " + encoding);

        // build the request
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        // use `exchange` method for HTTP call

        try {
            ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                return "failed-onGet";
            }
        } catch (Exception e) {
            System.out.println(e);
            return "failed";
        }
    }

}
