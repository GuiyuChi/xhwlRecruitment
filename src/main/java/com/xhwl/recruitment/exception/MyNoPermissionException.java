package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 权限不足的异常
 * @Date: Create in 上午9:01 2018/5/1
 **/
public class MyNoPermissionException extends RuntimeException{
    public MyNoPermissionException(String msg) {
        super(msg);
    }

    public MyNoPermissionException() {
        super();
    }
}
