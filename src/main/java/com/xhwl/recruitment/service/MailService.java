package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.PersonalInformationRepository;
import com.xhwl.recruitment.domain.PersonalInformationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author: kepiao
 * @Description: 发送邮件
 * @Date: Create in 下午1:46 2018/4/13
 **/
@Service
public class MailService {
    @Autowired
    PersonalInformationRepository personalInformationRepository;
    @Autowired
    JavaMailSender mailSender;
    public void sendMailByResumeId(Long ResumeId)
    {
        PersonalInformationEntity personalInformationEntity=personalInformationRepository.findByResumeId(ResumeId);
        String mail=personalInformationEntity.getEmail();
        try
        {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("714479658@qq.com");
            message.setTo(mail);
            message.setSubject("测试邮件主题");
            message.setText("测试邮件内容");
            this.mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
