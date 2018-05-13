package com.xhwl.recruitment.controller.user;

import com.xhwl.recruitment.domain.JobIntentionEntity;
import com.xhwl.recruitment.exception.MException;
import com.xhwl.recruitment.exception.ResumeNoExistException;
import com.xhwl.recruitment.service.ResumeService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.util.JWTUtil;
import com.xhwl.recruitment.vo.JobIntentionVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 上午10:13 2018/4/20
 **/
@RestController
public class JobIntentionController {
    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;

    @GetMapping("/intention")
    @RequiresAuthentication
    public JobIntentionEntity getJobIntention(@RequestHeader HttpHeaders headers){
        String username = null;
        if (headers.getFirst("authorization") != null) {
            username = JWTUtil.getUsername(headers.getFirst("authorization"));
        }
        Long userId = userService.getUserId(username);

        return resumeService.getJobIntension(userId);
    }

    @PostMapping("/intention")
    @RequiresAuthentication
    public JobIntentionEntity changeJobIntention(@RequestHeader HttpHeaders headers, @RequestBody JobIntentionVo jobIntentionVo){
        String username = null;
        if (headers.getFirst("authorization") != null) {
            username = JWTUtil.getUsername(headers.getFirst("authorization"));
        }
        Long userId = userService.getUserId(username);

        if (resumeService.getResume(userId) == null) {
            throw new ResumeNoExistException("需要先创建用户的简历表再创建工作意向表");
        }

        if(resumeService.getJobIntension(userId)==null){
            return resumeService.addJobIntension(userId,jobIntentionVo);
        }else{
            //从数据库中找到resume的id然后写入vo中
            jobIntentionVo.setId(resumeService.getJobIntension(userId).getId());
            return resumeService.modifyJobIntension(jobIntentionVo);
        }
    }
}
