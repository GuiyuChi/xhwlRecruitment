package com.xhwl.recruitment.controller;

import com.xhwl.recruitment.domain.ResumeEntity;
import com.xhwl.recruitment.exception.MException;
import com.xhwl.recruitment.service.DwResumeService;
import com.xhwl.recruitment.service.ResumeService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.util.JWTUtil;
import com.xhwl.recruitment.vo.ResumeVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

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

    @Autowired
    DwResumeService dwResumeService;

    /**
     * 获取简历,没有具体的信息
     *
     * @return
     */
    @GetMapping("/resume")
    @RequiresAuthentication
    public ResumeEntity getResume(@RequestHeader HttpHeaders headers) {
        String username = null;
        if (headers.getFirst("authorization") != null) {
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
    public ResumeEntity createResume(@RequestHeader HttpHeaders headers, @RequestParam("form") int resumesForm) {
        String username = null;
        if (headers.getFirst("authorization") != null) {
            username = JWTUtil.getUsername(headers.getFirst("authorization"));
        }
        Long userId = userService.getUserId(username);

        //已经创建过该用户的简历
        if (resumeService.getResume(userId) != null) {
            throw new MException("已经创建过该用户的简历");

        }

        return resumeService.createNewResume(userId, resumesForm);
    }

    /**
     * 修改简历类型，或者上传文件
     *
     * @param headers
     * @param resumeVo
     * @return
     */
    @PostMapping("/resume")
    @RequiresAuthentication
    public ResumeEntity modifyResume(@RequestHeader HttpHeaders headers, @RequestBody ResumeVo resumeVo) {

        String username = null;
        if (headers.getFirst("authorization") != null) {
            username = JWTUtil.getUsername(headers.getFirst("authorization"));
        }
        Long userId = userService.getUserId(username);

        if (resumeService.getResume(userId) == null) {
            throw new MException("需要先创建用户的简历表再修改");
        }

        if (userId != resumeVo.getUserId()) {
            throw new MException("无修改权限");
        }
        return resumeService.modifyResume(resumeVo);
    }

    /**
     * 用户修改简历类型
     *
     * @param headers
     * @param resumesForm
     * @return
     */
    @PutMapping("/resumesForm/{type}")
    @RequiresAuthentication
    public HashMap<String, String> modifyResumesForm(@RequestHeader HttpHeaders headers, @PathVariable("type") Integer resumesForm) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));

        if (resumeService.getResume(userId) == null) throw new MException("未创建简历");
        if (!(resumesForm == 1 || resumesForm == 2 || resumesForm == 3)) throw new MException("简历类型选择错误");
        HashMap<String, String> res = new LinkedHashMap<>();

        res.put("resumesForm", String.valueOf(resumeService.modifyResumesForm(userId, resumesForm)));
        return res;
    }

    @GetMapping("/resumetest/{form}")
    public ResumeEntity createResume2(@RequestHeader HttpHeaders headers, @PathVariable("form") int resumesForm) {
        Long userId = 9L;

        //已经创建过该用户的简历
        if (resumeService.getResume(userId) != null) {
            throw new MException("已经创建过该用户的简历");

        }

        return resumeService.createNewResume(userId, resumesForm);
    }


    /**
     * 管理员从投递记录获取用户的简历信息
     *
     * @param deliverId
     * @return
     */
    @GetMapping("/admin/getResume/{deliverId}")
    public List<Object> adminGetResumeBydeliver(@PathVariable("deliverId") Long deliverId) {
        return dwResumeService.adminGetResume(deliverId);
    }
}
