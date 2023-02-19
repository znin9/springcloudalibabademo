package com.example.scdemo.auth.conf;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @Author: znin9
 */
@EnableFeignClients(basePackages = {"com.example.scdemo.auth.feign"})
@Configuration
public class OpenFeignConfiguration {

    // @Bean("restTemplate")
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter jackson2 = new MappingJackson2HttpMessageConverter();
        jackson2.setSupportedMediaTypes(
                Arrays.asList(
                        MediaType.TEXT_HTML,
                        MediaType.TEXT_PLAIN
                )
        );

        restTemplate.getMessageConverters().add(jackson2);
        return restTemplate;
    }
}
