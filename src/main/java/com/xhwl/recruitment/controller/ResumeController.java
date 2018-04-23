package com.xhwl.recruitment.controller;

import com.xhwl.recruitment.domain.ResumeEntity;
import com.xhwl.recruitment.exception.MException;
import com.xhwl.recruitment.service.ResumeService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.util.JWTUtil;
import com.xhwl.recruitment.vo.ResumeVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午4:23 2018/4/7
 **/
@RestController
public class ResumeController {
    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;

    /**
     * 获取简历,没有具体的信息
     * @return
     */
    @GetMapping("/resume")
    @RequiresAuthentication
    public ResumeEntity getResume(@RequestHeader HttpHeaders headers){
        String username = null;
        if(headers.getFirst("authorization")!=null){
            username = JWTUtil.getUsername(headers.getFirst("authorization"));
        }
        Long userId = userService.getUserId(username);
        return resumeService.getResume(userId);
    }

    /**
     * 新建简历
     */
    @PutMapping("/resume")
    @RequiresAuthentication
    public ResumeEntity createResume(@RequestHeader HttpHeaders headers,@RequestParam("form") int resumesForm){
        String username = null;
        if(headers.getFirst("authorization")!=null){
            username = JWTUtil.getUsername(headers.getFirst("authorization"));
        }
        Long userId = userService.getUserId(username);

        //已经创建过该用户的简历
        if(resumeService.getResume(userId)!=null){
            throw new MException("已经创建过该用户的简历");

        }

        return resumeService.createNewResume(userId,resumesForm);
    }

    /**
     * 修改简历类型，或者上传文件
     * @param headers
     * @param resumeVo
     * @return
     */
    @PostMapping("/resume")
    @RequiresAuthentication
    public ResumeEntity modifyResume(@RequestHeader HttpHeaders headers, @RequestBody ResumeVo resumeVo){

        String username = null;
        if(headers.getFirst("authorization")!=null){
            username = JWTUtil.getUsername(headers.getFirst("authorization"));
        }
        Long userId = userService.getUserId(username);

        if(resumeService.getResume(userId) == null){
            throw new MException("需要先创建用户的简历表再修改");
        }

        if(userId != resumeVo.getUserId()){
            throw new MException("无修改权限");
        }
        return resumeService.modifyResume(resumeVo);
    }

//    @PutMapping("")
//    public HashMap<String,String> modifyResumesForm(@RequestHeader HttpHeaders headers,@RequestParam("type") byte resumesForm){
//        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
//    }

}
