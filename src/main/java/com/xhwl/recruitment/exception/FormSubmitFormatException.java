package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 表单提交格式异常
 * @Date: Create in 下午3:19 2018/6/4
 **/
public class FormSubmitFormatException extends RuntimeException{
    public FormSubmitFormatException(String msg) {
        super(msg);
    }

    public FormSubmitFormatException() {
        super();
    }
}
