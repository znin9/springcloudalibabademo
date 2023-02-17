package com.example.scdemo.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @GetMapping("/test")
    public Object test(){
        return "auth application";
    }
}
