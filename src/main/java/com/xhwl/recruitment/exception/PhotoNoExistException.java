package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午4:19 2018/5/13
 **/
public class PhotoNoExistException extends RuntimeException {
    public PhotoNoExistException(String msg) {
        super(msg);
    }

    public PhotoNoExistException() {
        super();
    }
}
