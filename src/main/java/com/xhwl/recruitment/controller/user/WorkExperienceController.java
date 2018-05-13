package com.xhwl.recruitment.controller.user;

import com.xhwl.recruitment.domain.WorkExperienceEntity;
import com.xhwl.recruitment.exception.MException;
import com.xhwl.recruitment.exception.MyNoPermissionException;
import com.xhwl.recruitment.service.PermissionService;
import com.xhwl.recruitment.service.ResumeService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.vo.WorkExperienceVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午11:08 2018/4/14
 **/
@RestController
public class WorkExperienceController {
    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;

    @Autowired
    PermissionService permissionService;

    /**
     * 获取用户的工作经历
     *
     * @param headers
     * @return
     */
    @GetMapping("/work")
    @RequiresAuthentication
    public List<WorkExperienceEntity> getWorkExperience(@RequestHeader HttpHeaders headers) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        return resumeService.getWorkExperiences(userId);
    }

    /**
     * 新建或者修改一条工作经历
     * @param headers
     * @param workExperienceVo
     * @return
     */
    @PostMapping("/work")
    @RequiresAuthentication
    public WorkExperienceEntity changeWorkExperience(@RequestHeader HttpHeaders headers, @RequestBody WorkExperienceVo workExperienceVo){
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if(workExperienceVo.getId()==null){
            return resumeService.addWorkExperience(userId,workExperienceVo);
        }else{
            if(permissionService.workExperiencePermission(userId,workExperienceVo.getId())){
                return resumeService.modifyWorkExperience(workExperienceVo);
            }else{
                throw new MyNoPermissionException("无修改权限");
            }
        }
    }

    /**
     * 删除一条工作记录
     * @param headers
     * @param workExperienceId
     */
    @DeleteMapping("/work/{id}")
    @RequiresAuthentication
    public void deleteWorkExperience(@RequestHeader HttpHeaders headers,@PathVariable("id") Long workExperienceId){
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if(permissionService.workExperiencePermission(userId,workExperienceId)){
            resumeService.deleteWorkExperience(workExperienceId);
        }else{
            throw new MyNoPermissionException("无修改权限");
        }
    }
}
