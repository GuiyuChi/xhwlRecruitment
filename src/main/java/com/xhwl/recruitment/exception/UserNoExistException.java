package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 用户不存在的异常
 * @Date: Create in 上午11:25 2018/5/5
 **/
public class UserNoExistException extends RuntimeException {
    public UserNoExistException(String msg) {
        super(msg);
    }

    public UserNoExistException() {
        super();
    }
}
