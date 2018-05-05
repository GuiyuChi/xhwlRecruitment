package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 手机验证码异常
 * @Date: Create in 下午8:01 2018/5/4
 **/
public class PhoneCaptchaException extends RuntimeException {
    public PhoneCaptchaException(String msg) {
        super(msg);
    }

    public PhoneCaptchaException() {
        super();
    }
}
