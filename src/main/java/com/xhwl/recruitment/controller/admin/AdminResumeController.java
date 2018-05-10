package com.xhwl.recruitment.controller.admin;

import com.xhwl.recruitment.service.DwResumeService;
import com.xhwl.recruitment.service.ResumeService;
import com.xhwl.recruitment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author: guiyu
 * @Description: 管理员查看投递用户的简历，以及下载用户相关附件的api
 * @Date: Create in 上午9:36 2018/5/10
 **/
public class AdminResumeController {
    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;

    @Autowired
    DwResumeService dwResumeService;

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
