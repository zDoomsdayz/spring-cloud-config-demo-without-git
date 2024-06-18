package com.example.client.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Value("${test-service.message}")
    private String message;

    @RequestMapping("/hello")
    public String sayHi() {
        return "hello " + message;
    }
}
