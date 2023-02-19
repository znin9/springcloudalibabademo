package com.example.scdemo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scdemo.commons.dto.captcha.CaptchaCheckDTO;
import com.example.scdemo.commons.http.Response;
import com.example.scdemo.commons.http.ResponseStandardCode;
import com.example.scdemo.user.entity.User;
import com.example.scdemo.user.exp.BizException;
import com.example.scdemo.user.feign.CaptchaService;
import com.example.scdemo.user.mapper.UserMapper;
import com.example.scdemo.user.pojo.req.LoginReq;
import com.example.scdemo.user.pojo.res.LoginRes;
import com.example.scdemo.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author: znin9
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private CaptchaService captchaService;

    /**
     * 随机用户名前缀
     */
    public static final String RANDOM_ACCESS_KEY_PREFIX = "uid_";

    // 用户状态:1-正常 2-冻结
    public static final int USER_STATUS_1 = 1;
    public static final int USER_STATUS_2 = 2;

    // 登录类型
    public static final int LOGIN_TYPE_AK = 1;
    public static final int LOGIN_TYPE_MOBILE = 2;
    public static final int LOGIN_TYPE_EMAIL = 3;

    @Override
    public void register(String mobile, String captcha) {
        // 1.校验验证码是否正确(调用captcha服务)
        Response<Boolean> res = captchaService.check(new CaptchaCheckDTO(mobile, captcha));
        if (Boolean.FALSE.equals(res.getData())) {
            log.debug("用户输入的验证码:${} 不存在", captcha);
            throw new BizException(ResponseStandardCode.FAIL.getCode(), "验证码错误");
        }
        // 2.生成随机非重复的用户名
        String ak = randomUsername();
        String uuid = ak.split("_")[1];
        User user = new User();
        user.setAccessKey(ak)
                .setUuid(uuid)
                .setSecurityKey("")
                .setMobile(mobile)
                .setStatus(USER_STATUS_1)
                .setGmtCreate(LocalDateTime.now())
                .setGmtModified(LocalDateTime.now());
        // 3.数据入库
        if (Boolean.FALSE.equals(save(user))) {
            log.info("注册用户失败,数据库插入失败。用户手机号:${}", mobile);
            throw new BizException(ResponseStandardCode.FAIL.getCode(), "数据库插入失败");
        }
    }

    @Override
    public boolean isAccessKeyExists(String ak) {
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getAccessKey, ak));
        return !Objects.isNull(user);
    }

    @Override
    public LoginRes login(LoginReq req) {
        switch (req.getLoginType()) {
            case LOGIN_TYPE_AK:
                return loginByAccessKey(req.getIdentify(), req.getCredential());
            case LOGIN_TYPE_MOBILE:
                return loginByMobile(req.getIdentify(), req.getCredential());
            case LOGIN_TYPE_EMAIL:
                return loginByEmail(req.getIdentify(), req.getCredential());
            default:
                throw new BizException(ResponseStandardCode.FAIL.getCode(), "没有这种登录方式,loginType=[1,2,3]");
        }
    }

    @Override
    public LoginRes loginByAccessKey(String accessKey, String securityKey) {
        // 1.根据accessKey查询数据库
        User user = getByAk(accessKey);
        if (Objects.isNull(user)){
            throw new BizException(ResponseStandardCode.FAIL.getCode(), "用户名不存在");
        }
        // 2.加密传入的securityKey

        // 3.调用auth服务获取token
        return null;
    }

    @Override
    public LoginRes loginByMobile(String mobile, String captcha) {
        return null;
    }

    @Override
    public LoginRes loginByEmail(String email, String code) {
        return null;
    }

    @Override
    public User getByAk(String accessKey) {
        return getOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getAccessKey, accessKey)
                        .eq(User::getStatus, USER_STATUS_1));
    }

    // 生成随机可用的用户名
    private String randomUsername() {
        String ak;
        do {
            ak = RANDOM_ACCESS_KEY_PREFIX + UUID.randomUUID();
        } while (Boolean.TRUE.equals(isAccessKeyExists(ak)));
        return ak;
    }

}
