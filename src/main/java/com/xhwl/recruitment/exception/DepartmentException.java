package com.xhwl.recruitment.exception;

/**
 * @Author: guiyu
 * @Description: 管理员页面中关于岗位的错误
 * @Date: Create in 下午10:22 2018/4/30
 **/
public class DepartmentException extends RuntimeException{
    public DepartmentException(String msg) {
        super(msg);
    }

    public DepartmentException() {
        super();
    }
}
