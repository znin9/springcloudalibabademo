package com.example.scdemo.commons.http;

/**
 * @Author: znin9
 */
public enum ResponseStandardCode {
    // 业务成功
    SUCCESS(2000,"SUCCESS"),
    // 业务失败
    FAIL(2001,"FAIL"),

    // 服务端错误
    ServerError(5000,"服务端错误")
    ;
    private Integer code;
    private String description;

    ResponseStandardCode(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
