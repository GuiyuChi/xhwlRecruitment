package com.xhwl.recruitment.controller.admin;

import com.xhwl.recruitment.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ke piao
 * @Description: 管理员根据简历id发送邮件的API
 * @Date: Create in 上午11:33 2018/5/1
 **/
@RestController
public class AdminMailController {
    @Autowired
    MailService mailService;
    @PostMapping("/admin/resume/{resumeId}")
    public void sendMail(@PathVariable("resumeId")Long resumeId)
    {
        mailService.sendMailByResumeId(resumeId);
    }
}
