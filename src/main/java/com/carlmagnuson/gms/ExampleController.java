package com.carlmagnuson.gms;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @RequestMapping("/test")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
