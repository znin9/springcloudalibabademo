package com.example.scdemo.user.controller;

import com.example.scdemo.commons.http.Response;
import com.example.scdemo.user.pojo.req.RegisterReq;
import com.example.scdemo.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: znin9
 */
@RequestMapping("/user")
@RestController
public class UserRestController {

    @Resource
    private UserService userService;

    /**
     * 目前只支持电话号码注册
     * 业务逻辑：
     *  1.参数校验
     *  2.调用captcha服务验证验证码是否正确
     *  3.如果正确生成随机的一个用户名,然后入库,返回用户信息
     */
    @PostMapping("/register")
    public Response<Void> register(@RequestBody RegisterReq req){
        userService.register(req.getMobile(),req.getCaptcha());
        return Response.success();
    }

}
