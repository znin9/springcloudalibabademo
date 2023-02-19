package com.example.scdemo.user.exphandler;

import com.example.scdemo.commons.http.Response;
import com.example.scdemo.commons.http.ResponseStandardCode;
import com.example.scdemo.user.exp.BizException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @Author: znin9
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务全局异常处理
     */
    @ExceptionHandler(value = {BizException.class})
    public Response<Void> bizExceptionHandler(BizException exception) {
        Integer code = exception.getCode();
        if (Objects.isNull(code)) {
            return Response.fail(ResponseStandardCode.FAIL.getCode(), exception.getMessage());
        }
        return Response.fail(code, exception.getMessage());
    }


    /**
     * 异常全局处理
     */
    @ExceptionHandler(value = {Exception.class})
    public Response<Void> exceptionHandler(Exception e) {
        return Response.fail(ResponseStandardCode.ServerError.getCode(), ResponseStandardCode.ServerError.getDescription());
    }
}
