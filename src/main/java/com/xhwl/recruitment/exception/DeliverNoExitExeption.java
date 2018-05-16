package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 投递记录不存在的异常
 * @Date: Create in 下午6:16 2018/5/16
 **/
public class DeliverNoExitExeption extends RuntimeException {
    public DeliverNoExitExeption(String msg) {
        super(msg);
    }

    public DeliverNoExitExeption() {
        super();
    }
}
