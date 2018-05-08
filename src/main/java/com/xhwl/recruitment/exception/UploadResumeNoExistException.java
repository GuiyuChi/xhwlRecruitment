package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 投递时用户简历附件未上传的异常
 * @Date: Create in 下午3:26 2018/5/8
 **/
public class UploadResumeNoExistException extends RuntimeException {
    public UploadResumeNoExistException(String msg) {
        super(msg);
    }

    public UploadResumeNoExistException() {
        super();
    }
}
