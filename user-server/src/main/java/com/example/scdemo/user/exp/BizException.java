package com.example.scdemo.user.exp;

/**
 * @Author: znin9
 */
public class BizException extends RuntimeException{

    private Integer code;

    public BizException() {
    }


    public BizException(String message) {
        super(message);
    }

    public BizException(Integer code,String message){
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
