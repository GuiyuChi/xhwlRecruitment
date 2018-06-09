package com.xhwl.recruitment.controller.user;

import com.xhwl.recruitment.domain.EducationExperienceEntity;
import com.xhwl.recruitment.exception.FormSubmitFormatException;
import com.xhwl.recruitment.exception.MException;
import com.xhwl.recruitment.exception.MyNoPermissionException;
import com.xhwl.recruitment.exception.ResumeNoExistException;
import com.xhwl.recruitment.service.PermissionService;
import com.xhwl.recruitment.service.ResumeService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.util.JWTUtil;
import com.xhwl.recruitment.util.ValidateUtils;
import com.xhwl.recruitment.vo.EducationExperenceVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: guiyu
 * @Description: 教育经历的url方法
 * @Date: Create in 上午11:03 2018/4/13
 **/
@RestController
public class EducationExperienceController {
    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;

    @Autowired
    PermissionService permissionService;

    /**
     * 获取用户的教育经历
     *
     * @param headers
     * @return
     */
    @GetMapping("/education")
    @RequiresAuthentication
    public List<EducationExperienceEntity> getEducationExperiences(@RequestHeader HttpHeaders headers) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        return resumeService.getEducationExperiences(userId);
    }

    /**
     * 新建或修改用户的教育经历
     *
     * @param headers
     * @param educationExperenceVo
     * @return
     */
    @PostMapping("/education")
    @RequiresAuthentication
    public EducationExperienceEntity modifyEducationExperience(@RequestHeader HttpHeaders headers, @RequestBody EducationExperenceVo educationExperenceVo) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));

        //表单验证
        if(!formValid(educationExperenceVo)){
            throw new FormSubmitFormatException("表单格式错误");
        }
        if (educationExperenceVo.getId() != null) {
            //存在id，为修改
            if (permissionService.EducationExperiencePermission(userId, educationExperenceVo.getId())) {
                return resumeService.modifyEducationExperience(educationExperenceVo);
            } else {
                throw new MyNoPermissionException("无修改权限");
            }
        } else {
            //没有输入id，为新建
            if (resumeService.getResume(userId) == null) {
                throw new ResumeNoExistException("需要先创建用户的简历表再创建教育信息表");
            }
            return resumeService.addEducationExperence(userId, educationExperenceVo);
        }
    }

    /**
     * 删除用户的教育经历
     *
     * @param headers
     * @param educationExperienceId
     */
    @DeleteMapping("/education/{id}")
    @RequiresAuthentication
    public void deleteEducationExperience(@RequestHeader HttpHeaders headers, @PathVariable("id") Long educationExperienceId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if (permissionService.EducationExperiencePermission(userId, educationExperienceId)) {
            resumeService.deleteEducationExperience(educationExperienceId);
        } else {
            throw new MyNoPermissionException("无修改权限");
        }
    }

    private boolean formValid(EducationExperenceVo vo) {
        boolean validRes = true;

        if (!ValidateUtils.isValidDate(vo.getStartTime())) {
            validRes = false;
        }
        if (!ValidateUtils.isValidDate(vo.getEndTime())) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(vo.getSchool())) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(vo.getSpeciality())) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(vo.getEducationHistory().toString())) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(vo.getRank().toString())){
            validRes = false;
        }
        return validRes;
    }
}
