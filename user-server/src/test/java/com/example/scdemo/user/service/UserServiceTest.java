package com.example.scdemo.user.service;

import com.example.scdemo.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

/**
 * @Author: znin9
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService accountService;


    @Test
    void list(){
        List<User> ret = accountService.list();
        System.out.println(ret);
    }

}