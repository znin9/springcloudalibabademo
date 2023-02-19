package com.example.scdemo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scdemo.user.entity.User;
import com.example.scdemo.user.pojo.req.LoginReq;
import com.example.scdemo.user.pojo.res.LoginRes;

/**
 * @Author: znin9
 */
public interface UserService extends IService<User> {

    /**
     * 目前只支持电话号码注册
     * 业务逻辑：
     * 1.参数校验
     * 2.调用captcha服务验证验证码是否正确
     * 3.如果正确生成随机的一个用户名,然后入库,返回用户信息
     */
    void register(String mobile, String captcha);

    /**
     * 判断AccessKey是否存在
     *
     * @param ak - accessKey
     */
    boolean isAccessKeyExists(String ak);

    /**
     * 登录
     *
     * @param req - 登录请求对象。
     *            loginType=1 表示 accessKey方式登录
     *            loginType=2 表示 mobile方式登录
     *            loginType=3 表示 email方式登录
     */
    LoginRes login(LoginReq req);


    /**
     * accessKey方式登录
     * 业务逻辑:
     * 1.根据加密方式将securityKey加密
     * 2.根据accessKey查询数据库
     * 3.比较查询的与加密后的securityKey是否相等
     * 4.调用auth服务获取accessKey和securityKey
     *
     * @param accessKey   - 访问标识(用户名)
     * @param securityKey - 访问密钥(密码)
     */
    LoginRes loginByAccessKey(String accessKey, String securityKey);

    // mobile方式登录
    LoginRes loginByMobile(String mobile, String captcha);

    // email方式登录
    LoginRes loginByEmail(String email, String code);

    // 通过AccessKey获取用户
    User getByAk(String accessKey);
}
