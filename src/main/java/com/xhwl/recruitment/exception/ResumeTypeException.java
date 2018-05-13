package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 简历类型异常
 * @Date: Create in 下午3:49 2018/5/13
 **/
public class ResumeTypeException extends RuntimeException {
    public ResumeTypeException(String msg) {
        super(msg);
    }

    public ResumeTypeException() {
        super();
    }
}
