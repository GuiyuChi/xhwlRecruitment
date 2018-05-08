package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 用户投递时未填写教育经历异常
 * @Date: Create in 下午3:11 2018/5/8
 **/
public class EducationNoExistException extends RuntimeException {
    public EducationNoExistException(String msg) {
        super(msg);
    }

    public EducationNoExistException() {
        super();
    }
}
