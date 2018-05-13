package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 岗位不存在的异常
 * @Date: Create in 下午4:06 2018/5/13
 **/
public class PositionNoExistException extends RuntimeException{
    public PositionNoExistException(String msg) {
        super(msg);
    }

    public PositionNoExistException() {
        super();
    }
}
