package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午2:21 2018/4/12
 **/
public class MException extends RuntimeException {
    public MException(String msg) {
        super(msg);
    }

    public MException() {
        super();
    }
}
