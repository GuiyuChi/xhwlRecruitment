package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 投递记录的一般性异常
 * @Date: Create in 下午8:34 2018/5/23
 **/
public class DeliverException extends RuntimeException{
    public DeliverException(String msg) {
        super(msg);
    }

    public DeliverException() {
        super();
    }
}
