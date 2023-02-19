package com.example.scdemo.user.pojo.req;

import lombok.Data;

/**
 * @Author: znin9
 */
@Data
public class LoginReq {

    private Integer loginType;

    private String identify;

    private String credential;
}
