package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 信息填写不完整的异常
 * @Date: Create in 上午9:41 2018/5/5
 **/
public class ImperfectException extends RuntimeException {
    public ImperfectException(String msg) {
        super(msg);
    }

    public ImperfectException() {
        super();
    }
}
