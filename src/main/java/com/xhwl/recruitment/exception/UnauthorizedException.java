package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午12:26 2018/4/8
 **/
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
