package com.vumah.vumahapis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class AppController {

    private final RestService restService;

    public AppController(RestService restService) {
        this.restService = restService;
    }

    @GetMapping
    public String testApp() {
        return "Welcome to vumah Api Connector";
    }

    @GetMapping("company-check/{code}")
    public ResponseEntity<?> getBusinessInfo(@PathVariable(value = "code") String code) throws Exception {

        String response = restService.getPostWithCustomHeaders(code);

        System.out.println(response);

        return ResponseEntity.ok(response);
    }

    @GetMapping("vehicle-check/{reg}")
    public ResponseEntity<?> getVehicleInfo(@PathVariable(value = "reg") String reg) throws Exception {

        String response = restService.getVehicleWithCustomHeaders(reg);
        System.out.println(response);

        return ResponseEntity.ok(response);
    }

}