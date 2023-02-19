package com.example.scdemo.commons.http;

import lombok.Data;

import java.io.Serializable;


/**
 * RestController 统一响应对象
 *
 * @Author: znin9
 */
@Data
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean success;
    private Integer code;
    private String description;
    private T data;
    private Long timestamp = System.currentTimeMillis();

    public Response() {
    }

    private Response(Boolean success, Integer code, String description, T data) {
        this.success = success;
        this.code = code;
        this.description = description;
        this.data = data;
    }

    public static <T> Response<T> success() {
        return new Response<>(true, ResponseStandardCode.SUCCESS.getCode(), ResponseStandardCode.FAIL.getDescription(), null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(true, ResponseStandardCode.SUCCESS.getCode(), ResponseStandardCode.FAIL.getDescription(), data);
    }

    public static <T> Response<T> success(int code, String description) {
        return new Response<>(true, code, description, null);
    }

    public static <T> Response<T> success(int code, String description, T data) {
        return new Response<>(true, code, description, data);
    }

    public static <T> Response<T> fail() {
        return new Response<>(false, ResponseStandardCode.FAIL.getCode(), ResponseStandardCode.FAIL.getDescription(), null);
    }

    public static <T> Response<T> fail(int code, String description) {
        return new Response<>(false, code, description, null);
    }

    public static <T> Response<T> fail(T data) {
        return new Response<>(false, ResponseStandardCode.FAIL.getCode(), ResponseStandardCode.FAIL.getDescription(), data);
    }

    public static <T> Response<T> fail(int code, String description, T data) {
        return new Response<>(false, code, description, data);
    }


}
