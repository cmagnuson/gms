package com.carlmagnuson.gms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @RequestMapping("/test")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
