package com.example.scdemo.captcha.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @GetMapping("/test")
    public Object test(){
        return "captcha application";
    }
}
