package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.DwPersonalInformationRepository;
import com.xhwl.recruitment.dao.DwResumeRepository;
import com.xhwl.recruitment.dao.ResumeDeliverRepository;
import com.xhwl.recruitment.domain.DwPersonalInformationEntity;
import com.xhwl.recruitment.domain.DwResumeEntity;
import com.xhwl.recruitment.domain.ResumeDeliverEntity;
import com.xhwl.recruitment.util.StatusCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: guiyu
 * @Description: 管理员审核简历相关服务提供
 * @Date: Create in 下午9:20 2018/5/1
 **/
@Service
public class AuditDeliverService {
    private final static Integer ResumeReview = 0; //简历审核

    private final static Integer HRFristReview = 1; //HR初审

    private final static Integer DepartmentWrittenExamination = 2; //部门笔试

    private final static Integer DepartmentInterview = 3; //部门面试

    private final static Integer HRInterview = 4; //HR面试

    private final static Integer Pass = 5; //已通过

    private final static Integer Refuse = 6; //已回绝

    @Autowired
    ResumeDeliverRepository resumeDeliverRepository;

    @Autowired
    DwResumeRepository dwResumeRepository;

    @Autowired
    DwPersonalInformationRepository dwPersonalInformationRepository;

    //获取用户名
    private String getUsernameByDeliver(ResumeDeliverEntity resumeDeliverEntity) {
        Long resumeId = resumeDeliverEntity.getDwResumeId();
        DwPersonalInformationEntity dwPersonalInformationEntity = dwPersonalInformationRepository.findByResumeId(resumeId);
        return dwPersonalInformationEntity.getName();
    }

    //获取性别

    //获取最高学历



    private List<ResumeDeliverEntity> findAllResumeReview(Long positionId) {
        List<ResumeDeliverEntity> resumeDeliverEntities = resumeDeliverRepository.findAllByPositionId(positionId);
        List<ResumeDeliverEntity> resumeReviewList = new ArrayList<>();

        for (ResumeDeliverEntity resumeDeliverEntity : resumeDeliverEntities) {
            if (StatusCodeUtil.codeAnalysis(resumeDeliverEntity.getRecruitmentState()) == ResumeReview) {
                resumeReviewList.add(resumeDeliverEntity);
            }
        }
        return resumeReviewList;
    }
}
