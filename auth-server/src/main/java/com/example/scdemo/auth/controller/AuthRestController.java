package com.example.scdemo.auth.controller;

import com.example.scdemo.commons.http.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: znin9
 */
@RequestMapping("/auth")
@RestController
public class AuthRestController {

    /**
     * 登录功能
     * 业务逻辑:
     *  1.判断当前客户是否登录
     *  2.调用user服务获取用户信息
     *  3.根据不同登录规则进行校验
     *  4.校验通过后将用户信息和accessToken,RefreshToken存入redis
     *  5.返回客户端accessToken,refreshToken
     */
    @PostMapping("/login")
    public Response<?> login(){
        return Response.success();
    }
}
