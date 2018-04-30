package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 用户或管理员重复注册的异常
 * @Date: Create in 下午11:03 2018/4/30
 **/
public class UserRepeatException extends RuntimeException {
    public UserRepeatException(String msg) {
        super(msg);
    }

    public UserRepeatException() {
        super();
    }
}
