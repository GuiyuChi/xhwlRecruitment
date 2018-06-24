package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.DwPersonalInformationRepository;
import com.xhwl.recruitment.dao.PersonalInformationRepository;
import com.xhwl.recruitment.dao.ResumeDeliverRepository;
import com.xhwl.recruitment.domain.DwPersonalInformationEntity;
import com.xhwl.recruitment.domain.PersonalInformationEntity;
import com.xhwl.recruitment.domain.ResumeDeliverEntity;
import com.xhwl.recruitment.util.EmailStateUtil;
import com.xhwl.recruitment.util.StatusCodeUtil;
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
    @Autowired
    ResumeDeliverRepository resumeDeliverRepository;
    @Autowired
    DwPersonalInformationRepository dwPersonalInformationRepository;

    private final static Integer ResumeReview = 0; //简历审核

    private final static Integer HRFristReview = 1; //HR初审

    private final static Integer DepartmentWrittenExamination = 2; //部门笔试

    private final static Integer DepartmentInterview = 3; //部门面试

    private final static Integer HRInterview = 4; //HR面试

    private final static Integer Pass = 5; //已通过

    private final static Integer Refuse = 6; //已回绝

    /**
     * 发送通过邮件
     *
     * @param ResumeId
     * @param month
     * @param day
     * @param hour
     * @param minute
     */
    public void sendOnMailByResumeId(Long ResumeId, String month, String day, String hour, String minute) {
        ResumeDeliverEntity resumeDelieverEntity = resumeDeliverRepository.findById(ResumeId);
        Long dw_resume_id = resumeDelieverEntity.getDwResumeId();
        DwPersonalInformationEntity dwPersonalInformationEntity = dwPersonalInformationRepository.findByResumeId(dw_resume_id);
        String mail = dwPersonalInformationEntity.getEmail();
        String name = dwPersonalInformationEntity.getName();
        //获取原先的邮件状态
        String oldState = resumeDelieverEntity.getEmailState();
        //设置新的邮件状态
        resumeDelieverEntity.setEmailState(EmailStateUtil.sendChange(oldState,Pass));
        resumeDeliverRepository.saveAndFlush(resumeDelieverEntity);
        try {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("714479658@qq.com");
            message.setTo(mail);
            message.setSubject("兴海物联");
            message.setText(name + ":\n" + "  您好！\n" + "  恭喜您成功通过本公司的笔试面试环节！请您于" + month + "月" + day + "日" + hour + "时" + minute + "分准时到岗，期待您的加入！\n" + "  进一步了解公司请点击下方链接：");
            this.mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送回绝邮件
     *
     * @param ResumeId
     */
    public void sendOffMailByResumeId(Long ResumeId) {
        ResumeDeliverEntity resumeDelieverEntity = resumeDeliverRepository.findById(ResumeId);
        Long dw_resume_id = resumeDelieverEntity.getDwResumeId();
        DwPersonalInformationEntity dwPersonalInformationEntity = dwPersonalInformationRepository.findByResumeId(dw_resume_id);
        String mail = dwPersonalInformationEntity.getEmail();
        String name = dwPersonalInformationEntity.getName();

        //获取原先的邮件状态
        String oldState = resumeDelieverEntity.getEmailState();
        //设置新的邮件状态
        resumeDelieverEntity.setEmailState(EmailStateUtil.sendChange(oldState,Refuse));
        resumeDeliverRepository.saveAndFlush(resumeDelieverEntity);
        try {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("714479658@qq.com");
            message.setTo(mail);
            message.setSubject("兴海物联");
            message.setText(name + ":\n" + "  您好！\n" + "  很遗憾您没有通过该岗位的招聘！希望未来还有合作机会，祝您今后求职顺利\n" + "  进一步了解公司请点击下方链接：");
            this.mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送其他邮件
     *
     * @param resumeId
     * @param title
     * @param component
     */
    public void sendCustomMail(Long resumeId, String title, String component) {
        ResumeDeliverEntity resumeDelieverEntity = resumeDeliverRepository.findById(resumeId);
        Long dw_resume_id = resumeDelieverEntity.getDwResumeId();
        DwPersonalInformationEntity dwPersonalInformationEntity = dwPersonalInformationRepository.findByResumeId(dw_resume_id);
        String mail = dwPersonalInformationEntity.getEmail();

        //获取此时的投递步数
        Integer step = StatusCodeUtil.codeAnalysis(resumeDelieverEntity.getRecruitmentState());
        //获取原先的邮件状态
        String oldState = resumeDelieverEntity.getEmailState();
        //设置新的邮件状态
        resumeDelieverEntity.setEmailState(EmailStateUtil.sendChange(oldState,step));

        resumeDeliverRepository.saveAndFlush(resumeDelieverEntity);

        try {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("714479658@qq.com");
            message.setTo(mail);
            message.setSubject(title);
            message.setText(component);
            this.mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
