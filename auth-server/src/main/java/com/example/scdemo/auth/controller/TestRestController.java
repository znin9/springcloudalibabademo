package com.example.scdemo.auth.controller;

import com.example.scdemo.auth.feign.TestService;
import com.example.scdemo.commons.http.Ajax;
import com.example.scdemo.commons.http.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestRestController {

    @Resource
    private TestService testService;

    @GetMapping("/test")
    public Object test(){
        return "auth application";
    }

    @GetMapping("/header")
    public Object testGatewayAddRequestHeader(@RequestHeader(name = "gatewayAdd",required = false) String header){
        System.out.println(header);
        return header;
    }

    @GetMapping("/testOpenFeign")
    public String testOpenFeign(@RequestParam("name") String name){
        Response<String> res = testService.test(name);
        return res.getData();
    }

    @GetMapping("/testOpenFeign2")
    public Ajax test2(@RequestParam("name") String name){
        Ajax ajax = testService.test2(name);
        return ajax;
    }

    @GetMapping("/testReponse")
    public Response<String> testResponse(){
        return Response.success("hello");
    }
}
