package com.xhwl.recruitment.controller.user;

import com.xhwl.recruitment.domain.PersonalInformationEntity;
import com.xhwl.recruitment.exception.FormSubmitFormatException;
import com.xhwl.recruitment.exception.MException;
import com.xhwl.recruitment.exception.ResumeNoExistException;
import com.xhwl.recruitment.service.ResumeService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.util.JWTUtil;
import com.xhwl.recruitment.util.ValidateUtils;
import com.xhwl.recruitment.vo.PersonalInformationVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午2:51 2018/4/12
 **/
@RestController
public class PersonalInformationController {
    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;

    /**
     * 获得个人信息表
     *
     * @param headers
     * @return
     */
    @GetMapping("/person")
    @RequiresAuthentication
    public PersonalInformationEntity getPersonalInformation(@RequestHeader HttpHeaders headers) {
        String username = null;
        if (headers.getFirst("authorization") != null) {
            username = JWTUtil.getUsername(headers.getFirst("authorization"));
        }
        Long userId = userService.getUserId(username);

        if (resumeService.getResume(userId) == null) {
            throw new ResumeNoExistException("需要先创建用户的简历表再查找个人信息表");
        }

        PersonalInformationEntity personalInformationEntity = resumeService.getPersonalInformation(userId);


        return personalInformationEntity;
    }

    /**
     * 上传个人信息表
     * <p>
     *
     * @param headers
     * @return
     */
    @PostMapping("/person")
    @RequiresAuthentication
    public PersonalInformationEntity modifyPersonalInformation(@RequestHeader HttpHeaders headers, @RequestBody PersonalInformationVo personalInformationVo) {
        String username = null;
        if (headers.getFirst("authorization") != null) {
            username = JWTUtil.getUsername(headers.getFirst("authorization"));
        }
        Long userId = userService.getUserId(username);

        if (resumeService.getResume(userId) == null) {
            throw new ResumeNoExistException("需要先创建用户的简历表再创建个人信息表");
        }

        // 表单验证
        if(!formValid(personalInformationVo)){
            throw new FormSubmitFormatException("表单格式错误");
        }


        if (resumeService.getPersonalInformation(userId) == null) {
            return resumeService.addPersonalInformation(userId, personalInformationVo);
        } else {
            //从数据库中找到resume的id然后写入vo中
            personalInformationVo.setId(resumeService.getPersonalInformation(userId).getId());
            return resumeService.modifyPersonalInformation(personalInformationVo);
        }
    }

    /**
     * 表单验证
     *
     * @param vo
     * @return
     */
    private boolean formValid(PersonalInformationVo vo) {
        boolean validRes = true;

        if (!ValidateUtils.Notempty(vo.getName())) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(vo.getSex().toString())) {
            validRes = false;
        }
        if (!ValidateUtils.isValidDate(vo.getBirthday())) {
            validRes = false;
        }
        if (!ValidateUtils.Email(vo.getEmail())) {
            validRes = false;
        }
        if(!ValidateUtils.Mobile(vo.getTelephone())){
            validRes = false;
        }
        if (!ValidateUtils.Notempty(vo.getWorkSeniority())) {
            validRes = false;
        }

        return validRes;
    }
}
