package com.xhwl.recruitment.controller;

import com.xhwl.recruitment.exception.MException;
import com.xhwl.recruitment.service.ResumeService;
import com.xhwl.recruitment.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午4:22 2018/4/20
 **/
@RestController
public class SelfAssessment {
    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;

    /**
     * 获取用户的自我介绍
     * @param headers
     * @return
     */
    @GetMapping("/selfAssessment")
    @RequiresAuthentication
    public String getSelfAssessment(@RequestHeader HttpHeaders headers){
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        return resumeService.getSelfAssessment(userId);
    }

    /**
     * 修改用户的自我介绍
     * @param headers
     * @param
     * @return
     */
    @PostMapping("/selfAssessment")
    @RequiresAuthentication
    public String modifySelfAssessment(@RequestHeader HttpHeaders headers, @RequestBody HashMap hashMap){
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        String selfAssessment = (String)hashMap.get("selfAssessment");
        if(selfAssessment==null) throw new MException("自我介绍格式错误");
        return resumeService.modifySelfAssessment(userId,selfAssessment);
    }
}
