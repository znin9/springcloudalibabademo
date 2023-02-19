package com.example.scdemo.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: znin9
 */
@TableName(value = "ROLE")
@Data
public class Role {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String roleName;

    private String description;

}
