package com.example.scdemo.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author: znin9
 */
@TableName("USER")
@Data
@Accessors(chain = true)
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String uuid;
    private String accessKey;
    private String securityKey;
    private String nickname;
    private String email;
    private String mobile;
    private LocalDateTime lastLoginTime;
    private String lastLoginIp;
    private Integer status;
    private String headPortraitUri;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
    @TableField(value = "IS_DELETED")
    private Integer deleted;
}
