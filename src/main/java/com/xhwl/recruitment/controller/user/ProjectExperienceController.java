package com.xhwl.recruitment.controller.user;

import com.xhwl.recruitment.domain.ProjectExperienceEntity;
import com.xhwl.recruitment.exception.FormSubmitFormatException;
import com.xhwl.recruitment.exception.MException;
import com.xhwl.recruitment.exception.MyNoPermissionException;
import com.xhwl.recruitment.service.PermissionService;
import com.xhwl.recruitment.service.ResumeService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.util.ValidateUtils;
import com.xhwl.recruitment.vo.ProjectExperienceVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午10:00 2018/4/14
 **/
@RestController
public class ProjectExperienceController {
    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;

    @Autowired
    PermissionService permissionService;

    /**
     * 获取项目经历
     *
     * @param headers
     * @return
     */
    @GetMapping("/project")
    @RequiresAuthentication
    public List<ProjectExperienceEntity> getProjectExperiences(@RequestHeader HttpHeaders headers) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        return resumeService.getProjectExperiences(userId);
    }

    /**
     * 新建或修改项目经历
     *
     * @param headers
     * @param projectExperienceVo
     * @return
     */
    @PostMapping("/project")
    @RequiresAuthentication
    public ProjectExperienceEntity changeProjectExperience(@RequestHeader HttpHeaders headers, @RequestBody ProjectExperienceVo projectExperienceVo) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));

        //表单验证
        if (!formValid(projectExperienceVo)) {
            throw new FormSubmitFormatException("表单格式错误");
        }
        if (projectExperienceVo.getId() == null) {
            //新建
            return resumeService.addProjectExperience(userId, projectExperienceVo);
        } else {
            //修改
            if (permissionService.projectExperiencePermission(userId, projectExperienceVo.getId())) {
                return resumeService.modifyProjectExperience(projectExperienceVo);
            } else {
                throw new MyNoPermissionException("无修改权限");
            }
        }
    }

    /**
     * 删除项目经历
     *
     * @param headers
     * @param projectExperienceId
     */
    @DeleteMapping("/project/{id}")
    @RequiresAuthentication
    public void deleteProjectExperience(@RequestHeader HttpHeaders headers, @PathVariable("id") Long projectExperienceId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if (permissionService.projectExperiencePermission(userId, projectExperienceId)) {
            resumeService.deleteProjectExperience(projectExperienceId);
        } else {
            throw new MyNoPermissionException("无修改权限");
        }
    }

    /**
     * 表单验证
     *
     * @param vo
     * @return
     */
    private boolean formValid(ProjectExperienceVo vo) {
        boolean validRes = true;
        if (!ValidateUtils.Notempty(vo.getProjectName())) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(vo.getProjectRole())) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(vo.getProjectDescription())) {
            validRes = false;
        }
        return validRes;
    }
}
