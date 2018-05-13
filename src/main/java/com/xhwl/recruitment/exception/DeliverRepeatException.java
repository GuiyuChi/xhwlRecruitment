package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 用户重复投递简历的异常
 * @Date: Create in 下午3:58 2018/5/13
 **/
public class DeliverRepeatException extends RuntimeException {
    public DeliverRepeatException(String msg) {
        super(msg);
    }

    public DeliverRepeatException() {
        super();
    }
}
