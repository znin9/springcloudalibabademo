package com.example.scdemo.auth.feign;

import com.example.scdemo.commons.http.Response;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: znin9
 */
@FeignClient(name = "user-server")
@Component
public interface TestService {

    @GetMapping("/test")
    Response<String> test(@RequestParam("name") String name);
}
