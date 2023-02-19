package com.example.scdemo.user.conf;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: znin9
 */
@EnableDiscoveryClient(autoRegister = true)
@Configuration
public class NacosConfiguration {
}
