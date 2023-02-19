package com.example.scdemo.user.pojo.res;

import lombok.Data;

/**
 * @Author: znin9
 */
@Data
public class LoginRes {

    // 访问令牌
    private String accessToken;

    // 刷新令牌
    private String refreshToken;
}
