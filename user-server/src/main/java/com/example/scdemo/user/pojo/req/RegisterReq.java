package com.example.scdemo.user.pojo.req;

import lombok.Data;

/**
 * @Author: znin9
 */
@Data
public class RegisterReq {

    // 手机号
    private String mobile;

    // 验证码
    private String captcha;
}
