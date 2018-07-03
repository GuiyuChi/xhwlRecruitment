package com.xhwl.recruitment.controller.Exception;

import com.xhwl.recruitment.bean.ResponseBean;
import com.xhwl.recruitment.exception.*;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
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
    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(ShiroException.class)
    public ResponseBean handle401(ShiroException e) {
        return new ResponseBean(401, e.getMessage(), null);
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.FOUND)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseBean handle401() {
        return new ResponseBean(401, "Unauthorized", null);
    }


    // 捕获 FormSubmitFormatException 表单格式异常
    @ExceptionHandler(FormSubmitFormatException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchFormSubmitFormatException(Throwable ex) {
        return new ResponseBean(430, ex.getMessage(), null);
    }

    //捕获 DepartmentException 部门相关错误
    @ExceptionHandler(DepartmentException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchDepartmentException(HttpServletRequest request, Throwable ex) {
        return new ResponseBean(431, ex.getMessage(), null);
    }

    //捕获 UserRepeatException 用户或管理员重复注册的异常
    @ExceptionHandler(UserRepeatException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchUserRepeatException(Throwable ex){
        return new ResponseBean(432, ex.getMessage(), null);
    }

    //捕获 NoPermissionException 管理员权限不足
    @ExceptionHandler(MyNoPermissionException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchNoPermissionException(Throwable ex){
        return new ResponseBean(433, ex.getMessage(), null);
    }

    // 捕获 DeliverException 投递相关的通用异常
    @ExceptionHandler(DeliverException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchDeliverException(Throwable ex) {
        return new ResponseBean(434, ex.getMessage(), null);
    }



    //捕获 CaptchaException 图像验证码异常
    @ExceptionHandler(CaptchaException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchCaptchaException(Throwable ex){
        return new ResponseBean(423, ex.getMessage(), null);
    }

    //捕获 PhoneCaptchaException 短信验证码异常
    @ExceptionHandler(PhoneCaptchaException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchPhoneCaptchaException(Throwable ex){
        return new ResponseBean(424, ex.getMessage(), null);
    }

    //捕获 ImperfectException 信息不完整异常
    @ExceptionHandler(ImperfectException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchImperfectException(Throwable ex){
        return new ResponseBean(425, ex.getMessage(), null);
    }

    //捕获 UserNoExistException 用户不存在异常
    @ExceptionHandler(UserNoExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchUserNoExistException(Throwable ex){
        return new ResponseBean(426, ex.getMessage(), null);
    }




    //捕获 DeliverRepeatException 用户重复投递
    @ExceptionHandler(DeliverRepeatException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchDeliverRepeatException(Throwable ex){
        return new ResponseBean(440, ex.getMessage(), null);
    }

    //捕获 ResumeTypeException 投递时简历类型不符合的异常
    @ExceptionHandler(ResumeTypeException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchResumeTypeException(Throwable ex){
        return new ResponseBean(441, ex.getMessage(), null);
    }

    //捕获 ResumeNoExistException 投递时简历不存在异常
    @ExceptionHandler(ResumeNoExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchResumeNoExistException(Throwable ex){
        return new ResponseBean(442, ex.getMessage(), null);
    }

    //捕获 PersonalInformationNoExistException 用户投递简历时未填写个人信息异常
    @ExceptionHandler(PersonalInformationNoExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchPersonalInformationNoExistException(Throwable ex){
        return new ResponseBean(443, ex.getMessage(), null);
    }

    //捕获 EducationNoExistException 用户投递简历时未填写教育经历异常
    @ExceptionHandler(EducationNoExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchEducationNoExistException(Throwable ex){
        return new ResponseBean(444, ex.getMessage(), null);
    }

    //捕获 JobIntentionNoExistException 用户投递时未填写就业意向的异常
    @ExceptionHandler(JobIntentionNoExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchJobIntentionNoExistException(Throwable ex){
        return new ResponseBean(445, ex.getMessage(), null);
    }

    //捕获 UploadResumeNoExistException 用户投递时未上传简历附件的异常
    @ExceptionHandler(UploadResumeNoExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchUploadResumeNoExistException(Throwable ex){
        return new ResponseBean(446, ex.getMessage(), null);
    }

    //捕获 PositionNoExistException 岗位不存在异常
    @ExceptionHandler(PositionNoExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchPositionNoExistException(Throwable ex){
        return new ResponseBean(447, ex.getMessage(), null);
    }

    //捕获 PhotoNoExistException 用户未上传照片的异常
    @ExceptionHandler(PhotoNoExistException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchPhotoNoExistException(Throwable ex){
        return new ResponseBean(448, ex.getMessage(), null);
    }

    //捕获 DeliverNoExitExeption 投递记录不存在的异常
    @ExceptionHandler(DeliverNoExitExeption.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseBean catchDeliverNoExitExeption(Throwable ex){
        return new ResponseBean(449, ex.getMessage(), null);
    }





    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.FOUND)
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
