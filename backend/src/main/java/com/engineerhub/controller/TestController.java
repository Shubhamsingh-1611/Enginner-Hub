package com.engineerhub.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {
    
    @GetMapping("/jwt")
    public String test() {
        return "JWT working";
    }
}
