package com.example.scdemo.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: znin9
 */
@TableName(value = "PERMISSION")
@Data
public class Permission {
    @TableId(type = IdType.AUTO)
    private Long id;
    // 权限名
    private String permissionName;
    // 描述
    private String description;
}
