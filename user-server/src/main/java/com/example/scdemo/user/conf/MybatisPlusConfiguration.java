package com.example.scdemo.user.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: znin9
 */
@Configuration
@MapperScan(basePackages = {"com.example.scdemo.user.mapper"})
public class MybatisPlusConfiguration {
}
