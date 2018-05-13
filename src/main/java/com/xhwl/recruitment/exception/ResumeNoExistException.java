package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 简历不存在的异常
 * @Date: Create in 下午2:59 2018/5/8
 **/
public class ResumeNoExistException extends RuntimeException{
    public ResumeNoExistException(String msg) {
        super(msg);
    }

    public ResumeNoExistException() {
        super();
    }
}
