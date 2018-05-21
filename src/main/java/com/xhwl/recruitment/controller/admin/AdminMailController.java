package com.xhwl.recruitment.controller.admin;

import com.xhwl.recruitment.service.MailService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequiresAuthentication
    @RequiresRoles("admin")
    @PostMapping("/admin/onMail/{resumeId}")//发送通过邮件
    public void sendOnMail(@PathVariable("resumeId")Long resumeId,@RequestParam("month") String month,
                           @RequestParam("day")String day,@RequestParam("hour")String hour,@RequestParam("minute")String minute)
    {
        mailService.sendOnMailByResumeId(resumeId,month,day,hour,minute);
    }

    @RequiresAuthentication
    @RequiresRoles("admin")
    @PostMapping("/admin/offMail/resumeId")//发送回绝邮件
    public void sendOffMail(@PathVariable("resumeId")Long resumeId)
    {
        mailService.sendOffMailByResumeId(resumeId);
    }
}
