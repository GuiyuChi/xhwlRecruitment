package com.xhwl.recruitment.controller;

import com.xhwl.recruitment.domain.TrainingExperienceEntity;
import com.xhwl.recruitment.exception.MException;
import com.xhwl.recruitment.service.PermissionService;
import com.xhwl.recruitment.service.ResumeService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.vo.TrainingExperienceVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Target;
import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午9:06 2018/4/14
 **/
@RestController
public class TrainingExperienceController {
    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;

    @Autowired
    PermissionService permissionService;

    /**
     * 获取用户的培训经历
     *
     * @param headers
     * @return
     */
    @GetMapping("/training")
    @RequiresAuthentication
    public List<TrainingExperienceEntity> getTrainingExperiences(@RequestHeader HttpHeaders headers) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        return resumeService.getTrainExperiences(userId);
    }

    /**
     * 新建或者修改一条培训经历
     *
     * @param headers
     * @param trainingExperienceVo
     * @return
     */
    @PostMapping("/training")
    @RequiresAuthentication
    public TrainingExperienceEntity changeTrainingExperience(@RequestHeader HttpHeaders headers, @RequestBody TrainingExperienceVo trainingExperienceVo) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));

        if (trainingExperienceVo.getId() == null) {
            //id为空，表示新建
            return resumeService.addTrainingExperience(userId, trainingExperienceVo);
        } else {
            //id不为空，表示修改
            if (permissionService.trainingExperiencePermission(userId, trainingExperienceVo.getId())) {
                return resumeService.modifyTrainingExperience(trainingExperienceVo);
            } else {
                throw new MException("无修改权限");
            }
        }
    }

    /**
     * 删除一条培训经历
     *
     * @param headers
     * @param trainingExperienceId
     */
    @DeleteMapping("/training/{id}")
    @RequiresAuthentication
    public void deleteTrainingExperience(@RequestHeader HttpHeaders headers, @PathVariable("id") Long trainingExperienceId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if (permissionService.trainingExperiencePermission(userId, trainingExperienceId)) {
            resumeService.deleteTrainingExperience(trainingExperienceId);
        }else{
            throw new MException("无修改权限");
        }

    }

}
