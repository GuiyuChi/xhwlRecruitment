package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.*;
import com.xhwl.recruitment.domain.DwResumeEntity;
import com.xhwl.recruitment.redis.DeliverRedis;
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
    private DeliverRedis deliverRedis;

    private Long getDwResumeId(Long deliverId){
        return resumeDeliverRepository.findOne(deliverId).getDwResumeId();
    }

//    查询简历信息
    private List<Object> getDwResumeAllInfo(Long resumeId){
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
     * @param deliverId
     * @return
     */
    public List<Object> adminGetResume(Long deliverId){
        Long resumeId = getDwResumeId(deliverId);

        //设置投递记录对应的阅读状态为已读
        deliverRedis.setDeliverRead(deliverId);

        return getDwResumeAllInfo(resumeId);
    }
}
