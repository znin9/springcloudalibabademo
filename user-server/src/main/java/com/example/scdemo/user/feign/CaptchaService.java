package com.example.scdemo.user.feign;

import com.example.scdemo.commons.dto.captcha.CaptchaCheckDTO;
import com.example.scdemo.commons.http.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: znin9
 */
@FeignClient(name = "captcha-server")
@Component
public interface CaptchaService {

    // 检查验证码是否存在
    @PostMapping("/check")
    Response<Boolean> check(@RequestBody CaptchaCheckDTO dto);
}
