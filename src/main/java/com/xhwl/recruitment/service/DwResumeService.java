package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.*;
import com.xhwl.recruitment.domain.AdminAuthEntity;
import com.xhwl.recruitment.domain.DwResumeEntity;
import com.xhwl.recruitment.domain.PositionEntity;
import com.xhwl.recruitment.domain.ResumeDeliverEntity;
import com.xhwl.recruitment.util.StatusCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午5:58 2018/4/25
 **/
@Service
public class DwResumeService {
    @Autowired
    private DwResumeRepository dwResumeRepository;

    @Autowired
    private DwPersonalInformationRepository dwPersonalInformationRepository;

    @Autowired
    private DwEducationExperienceRepository dwEducationExperienceRepository;

    @Autowired
    private DwTrainingExperienceRepository dwTrainingExperienceRepository;

    @Autowired
    private DwProjectExperienceRepository dwProjectExperienceRepository;

    @Autowired
    private DwWorkExperienceRepository dwWorkExperienceRepository;

    @Autowired
    private DwInternshipExperienceRepository dwInternshipExperienceRepository;

    @Autowired
    private DwAwardRepository dwAwardRepository;

    @Autowired
    private DwJobIntentionRepository dwJobIntentionRepository;

    @Autowired
    private ResumeDeliverRepository resumeDeliverRepository;

    @Autowired
    private PositionRepository positionRepository;


    @Autowired
    private AdminAuthRepository adminAuthRepository;

    /**
     * 人事部门的id
     */
    private static final Long PersonnelDepartmentId = 1L;

    private final static Integer ResumeReview = 0; //简历审核

    private final static Integer HRFristReview = 1; //hr初审

    private final static Integer DepartmentWrittenExamination = 2; //部门笔试

    private final static Integer DepartmentInterview = 3; //部门面试

    private final static Integer HRInterview = 4; //HR面试

    private final static Integer Pass = 5; //已通过

    private final static Integer Refuse = 6; //已回绝


    private Long getDwResumeId(Long deliverId) {
        return resumeDeliverRepository.findOne(deliverId).getDwResumeId();
    }

    /**
     * 判断某一用户对查看的简历是否有处理的权限，进而进行阅读状态的修改
     *
     * @param deliverId
     * @param userId
     * @return
     */
    private Boolean authJudge(Long deliverId, Long userId) {
        try {
            AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
            ResumeDeliverEntity deliver = resumeDeliverRepository.findById(deliverId);
            Long positionId = deliver.getPositionId();
            PositionEntity position = positionRepository.findOne(positionId);
            Long adminDepartment = adminAuthEntity.getDepartmentId();
            Long positionDepartment = position.getDepartment();
            Long resumeAuditDepartment = position.getResumeAuditDepartment();

            String state = deliver.getRecruitmentState();
            switch (StatusCodeUtil.codeAnalysis(state)) {
                case 0:
                    return adminDepartment == resumeAuditDepartment ? true : false;
                case 1:
                    return adminDepartment == PersonnelDepartmentId ? true : false;
                case 2:
                    return adminDepartment == positionDepartment ? true : false;
                case 3:
                    return adminDepartment == positionDepartment ? true : false;
                case 4:
                    return adminDepartment == PersonnelDepartmentId ? true : false;
                case 5:
                    return adminDepartment == PersonnelDepartmentId ? true : false;
                case 6:
                    return adminDepartment == PersonnelDepartmentId ? true : false;
                default:
                    return false;
            }

        } catch (Exception e) {
            return false;
        }

    }

    //    查询简历信息
    private List<Object> getDwResumeAllInfo(Long resumeId) {
        List<Object> res = new ArrayList<>();
        res.add(dwPersonalInformationRepository.findByResumeId(resumeId));
        res.add(dwEducationExperienceRepository.findAllByResumeId(resumeId));
        res.add(dwTrainingExperienceRepository.findAllByResumeId(resumeId));
        res.add(dwProjectExperienceRepository.findAllByResumeId(resumeId));
        res.add(dwWorkExperienceRepository.findAllByResumeId(resumeId));
        res.add(dwInternshipExperienceRepository.findAllByResumeId(resumeId));
        res.add(dwAwardRepository.findAllByResumeId(resumeId));
        res.add(dwJobIntentionRepository.findByResumeId(resumeId));
        res.add(dwResumeRepository.findOne(resumeId));
        return res;
    }

    /**
     * 管理员根据投递记录查看简历的详情
     *
     * @param deliverId
     * @return
     */
    public List<Object> adminGetResume(Long userId, Long deliverId) {
        Long resumeId = getDwResumeId(deliverId);

        ResumeDeliverEntity resumeDeliverEntity = resumeDeliverRepository.findById(deliverId);

        //用户有修改权限
        if (authJudge(deliverId, userId)) {
            // 设置阅读状态为已读
            resumeDeliverEntity.setReadFlag(1);
            resumeDeliverRepository.saveAndFlush(resumeDeliverEntity);
        }

        return getDwResumeAllInfo(resumeId);
    }
}
