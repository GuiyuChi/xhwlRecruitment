package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 投递时就业意向不完整异常
 * @Date: Create in 下午3:14 2018/5/8
 **/
public class JobIntentionNoExistException extends RuntimeException{
    public JobIntentionNoExistException(String msg) {
        super(msg);
    }

    public JobIntentionNoExistException() {
        super();
    }
}
