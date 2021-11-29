package com.vumah.vumahapis;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Base64;
import java.util.Collections;

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


//    public String getAll() {
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("https://api.vehiclesmart.com/rest/vehicleData
//                        ?reg=<your-registration-goes-here>
//                &appid=your-api-key
//                &isRefreshing=false
//                &dvsaFallbackMode=false")
//                .get()
//                .addHeader("accept", "application/json")
//                .addHeader("cache-control", "no-cache")
//                .build();
//        Response response = client.newCall(request).execute();
//        return response;
//    }

    public String getPostsPlainJSON() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        return this.restTemplate.getForObject(url, String.class);
    }

    public String getVehicleData() {
        String url = "https://api.vehiclesmart.com/rest/vehicleData?reg=MA68HXM&appid=c6jKwNxbtuNS384aNdd21Q&isRefreshing=false&dvsaFallbackMode=false";

        Object response = this.restTemplate.getForObject(url, Object.class);

        System.out.println("Vehicle: " + response);

        return response.toString();
    }

    public String getVehicleWithCustomHeaders(String code) throws Exception {

        String url = "https://api.vehiclesmart.com/rest/vehicleData?reg=MA68HXM&appid=c6jKwNxbtuNS384aNdd21Q&isRefreshing=false&dvsaFallbackMode=false";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set custom header
        headers.set("accept", "accept");
        headers.set("cache-control", "no-cache");

        // build the request
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        // use `exchange` method for HTTP call

        try {
            ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                return "There is no such a company with such a company code: " + code;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "There is no such a company with such a company code: " + code;
        }
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
                return "There is no such a company with such a company code: " + code;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "There is no such a company with such a company code: " + code;
        }
    }

}
