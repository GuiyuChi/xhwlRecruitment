package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 用户投递简历时未填写个人信息
 * @Date: Create in 下午3:08 2018/5/8
 **/
public class PersonalInformationNoExistException extends RuntimeException{
    public PersonalInformationNoExistException(String msg) {
        super(msg);
    }

    public PersonalInformationNoExistException() {
        super();
    }
}
