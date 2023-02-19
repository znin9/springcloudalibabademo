package com.example.scdemo.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.scdemo.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: znin9
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
