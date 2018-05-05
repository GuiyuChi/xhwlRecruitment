package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 图形验证码异常
 * @Date: Create in 下午7:57 2018/5/4
 **/
public class CaptchaException extends RuntimeException {
    public CaptchaException(String msg) {
        super(msg);
    }

    public CaptchaException() {
        super();
    }
}
