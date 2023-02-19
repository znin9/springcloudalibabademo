package com.example.scdemo.user.controller;

import com.example.scdemo.commons.http.Response;
import com.example.scdemo.user.entity.User;
import com.example.scdemo.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: znin9
 */
@RestController
public class TestRestController {

    @Resource
    private UserService accountService;

    @GetMapping("/hello")
    public Object hello(){
        List<User> ret = accountService.list();
        System.out.println(ret);
        return ret;
    }

    @GetMapping("/test")
    public Response<String> test(@RequestParam("name") String name){
        return Response.success("hello" + name);
    }
}
