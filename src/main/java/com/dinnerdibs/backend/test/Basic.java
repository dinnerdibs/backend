package com.dinnerdibs.backend.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Basic {
    @GetMapping(path = "/")
    public String helloWorld() {
        return "Hello, world!";
    }

    @GetMapping(path = "/test")
    public String test() {
        return "Temporary endpoint for testing purpose!";
    }
}
