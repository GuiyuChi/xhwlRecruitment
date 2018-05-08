package com.xhwl.recruitment.controller.user;

import com.xhwl.recruitment.domain.InternshipExperienceEntity;
import com.xhwl.recruitment.exception.MException;
import com.xhwl.recruitment.service.PermissionService;
import com.xhwl.recruitment.service.ResumeService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.vo.InternshipExperienceVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 上午12:28 2018/4/15
 **/
@RestController
public class InternshipExperienceController {
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
    @GetMapping("/internship")
    @RequiresAuthentication
    public List<InternshipExperienceEntity> getInternshipExperience(@RequestHeader HttpHeaders headers) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        return resumeService.getInternshipExperiences(userId);
    }

    /**
     * 新建或者修改一条实习经历
     * @param headers
     * @param internshipExperienceVo
     * @return
     */
    @PostMapping("/internship")
    @RequiresAuthentication
    public InternshipExperienceEntity changeInternshipExperience(@RequestHeader HttpHeaders headers, @RequestBody InternshipExperienceVo internshipExperienceVo) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if (internshipExperienceVo.getId() == null) {
            //新建
            return resumeService.addInternshipExperience(userId, internshipExperienceVo);
        } else {
            //修改
            if (permissionService.internshipExperiencePermission(userId, internshipExperienceVo.getId())) {
                return resumeService.modifyInternshipExperience(internshipExperienceVo);
            } else {
                throw new MException("无修改权限");
            }
        }
    }

    /**
     * 删除
     * @param headers
     * @param internshipId
     */
    @DeleteMapping("/internship/{id}")
    @RequiresAuthentication
    public void deleteInternshipExperience(@RequestHeader HttpHeaders headers,@PathVariable("id") Long internshipId){
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if(permissionService.internshipExperiencePermission(userId,internshipId)){
            resumeService.deleteInternshipExperience(internshipId);
        }else{
            throw new MException("无修改权限");
        }
    }
}
