package com.vumah.vumahapis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class AppController {

    private RestService restService;

    public AppController(RestService restService) {
        this.restService = restService;
    }

    @GetMapping
    public String testApp() {
        return "Hello Welcome to GCC Api for more access ask the Admins!";
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getBusinessInfo(@PathVariable(value = "code") String code) throws Exception {

        String response = restService.getPostWithCustomHeaders(code);

        System.out.println(response);

        return ResponseEntity.ok(response);
    }

}

//public.ecr.aws/p9x6c1p0/vumah