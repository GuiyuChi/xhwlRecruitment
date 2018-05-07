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
    public void sendOnMailByResumeId(Long ResumeId)//发送通过邮件
    {
        PersonalInformationEntity personalInformationEntity=personalInformationRepository.findByResumeId(ResumeId);
        String mail=personalInformationEntity.getEmail();
        String name=personalInformationEntity.getName();
        try
        {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("wudan1@copm.com.cn");
            message.setTo(mail);
            message.setSubject("兴海物联");
            message.setText(name+":\n"+"  您好！\n"+"  恭喜您成功通过本公司的笔试面试环节！请您于XX月XX日XX时XX分准时到岗，期待您的加入！\n"+"  进一步了解公司请点击下方链接：");
            this.mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
    public void sendOffMailByResumeId(Long ResumeId)//发送回绝邮件
    {
        PersonalInformationEntity personalInformationEntity=personalInformationRepository.findByResumeId(ResumeId);
        String mail=personalInformationEntity.getEmail();
        String name=personalInformationEntity.getName();
        try
        {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("wudan1@copm.com.cn");
            message.setTo(mail);
            message.setSubject("兴海物联");
            message.setText(name+":\n"+"  您好！\n"+"  很遗憾您没有通过该岗位的招聘！希望未来还有合作机会，祝您今后求职顺利\n"+"  进一步了解公司请点击下方链接：");
            this.mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
