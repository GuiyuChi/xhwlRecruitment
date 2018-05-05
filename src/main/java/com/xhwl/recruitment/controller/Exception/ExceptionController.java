package com.xhwl.recruitment.controller.Exception;

import com.xhwl.recruitment.bean.ResponseBean;
import com.xhwl.recruitment.exception.*;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午12:41 2018/4/8
 **/

@RestControllerAdvice
public class ExceptionController {

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseBean handle401(ShiroException e) {
        return new ResponseBean(401, e.getMessage(), null);
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseBean handle401() {
        return new ResponseBean(401, "Unauthorized", null);
    }

    //捕获 DepartmentException 岗位相关错误
    @ExceptionHandler(DepartmentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean catchDepartmentException(HttpServletRequest request, Throwable ex) {
        return new ResponseBean(422, ex.getMessage(), null);
    }

    //捕获 UserRepeatException 用户或管理员重复注册的异常
    @ExceptionHandler(UserRepeatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean catchUserRepeatException(Throwable ex){
        return new ResponseBean(423, ex.getMessage(), null);
    }

    //捕获 NoPermissionException 管理员权限不足
    @ExceptionHandler(MyNoPermissionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean catchNoPermissionException(Throwable ex){
        return new ResponseBean(402, ex.getMessage(), null);
    }

    //捕获 CaptchaException 图像验证码异常
    @ExceptionHandler(CaptchaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean catchCaptchaException(Throwable ex){
        return new ResponseBean(423, ex.getMessage(), null);
    }

    //捕获 PhoneCaptchaException 短信验证码异常
    @ExceptionHandler(PhoneCaptchaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean catchPhoneCaptchaException(Throwable ex){
        return new ResponseBean(424, ex.getMessage(), null);
    }

    //捕获 ImperfectException 信息不完整异常
    @ExceptionHandler(ImperfectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean catchImperfectException(Throwable ex){
        return new ResponseBean(425, ex.getMessage(), null);
    }
    //捕获 UserNoExistException 用户不存在异常
    @ExceptionHandler(UserNoExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean catchUserNoExistException(Throwable ex){
        return new ResponseBean(426, ex.getMessage(), null);
    }
    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseBean globalException(HttpServletRequest request, Throwable ex) {
        return new ResponseBean(getStatus(request).value(), ex.getMessage(), null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
