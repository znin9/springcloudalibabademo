package com.example.scdemo.commons.dto.captcha;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: znin9
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaCheckDTO {

    private String mobile;

    private String code;
}
