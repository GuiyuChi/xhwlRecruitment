package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.*;
import com.xhwl.recruitment.domain.DwEducationExperienceEntity;
import com.xhwl.recruitment.domain.DwPersonalInformationEntity;
import com.xhwl.recruitment.domain.PositionEntity;
import com.xhwl.recruitment.domain.ResumeDeliverEntity;
import com.xhwl.recruitment.dto.DeliverDto;
import com.xhwl.recruitment.redis.DeliverRedis;
import com.xhwl.recruitment.util.StatusCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: guiyu
 * @Description: 管理员审核简历相关服务提供
 * @Date: Create in 下午9:20 2018/5/1
 **/
@Service
public class AuditDeliverService {
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


    @Autowired
    ResumeDeliverRepository resumeDeliverRepository;

    @Autowired
    DwResumeRepository dwResumeRepository;

    @Autowired
    DwPersonalInformationRepository dwPersonalInformationRepository;

    @Autowired
    DwEducationExperienceRepository dwEducationExperienceRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    DeliverRedis deliverRedis;

    //获取用户名
    private String getUsernameByDeliver(ResumeDeliverEntity resumeDeliverEntity) {
        Long resumeId = resumeDeliverEntity.getDwResumeId();
        DwPersonalInformationEntity dwPersonalInformationEntity = dwPersonalInformationRepository.findByResumeId(resumeId);
        if (dwEducationExperienceRepository == null) return "未填写";
        return dwPersonalInformationEntity.getName();
    }

    //获取性别
    private String getSexByDeliver(ResumeDeliverEntity resumeDeliverEntity) {
        Long resumeId = resumeDeliverEntity.getDwResumeId();
        DwPersonalInformationEntity dwPersonalInformationEntity = dwPersonalInformationRepository.findByResumeId(resumeId);
        if (dwPersonalInformationEntity == null) return "未填写";
        Byte sexByte = dwPersonalInformationEntity.getSex();
        if (sexByte == 1) {
            return "男";
        } else {
            return "女";
        }
    }

    //获取最高学历
    private String getMaxHighEducationByDeliver(ResumeDeliverEntity resumeDeliverEntity) {
        Long resumeId = resumeDeliverEntity.getDwResumeId();
        List<DwEducationExperienceEntity> educations = dwEducationExperienceRepository.findAllByResumeId(resumeId);
        if (educations == null) return "学历未知";
        Byte max = 0;
        for (DwEducationExperienceEntity education : educations) {
            if (education.getEducationHistory() > max) {
                max = education.getEducationHistory();
            }
        }
        if (max == 1) {
            return "高中";
        } else if (max == 2) {
            return "专科";
        } else if (max == 3) {
            return "本科";
        } else if (max == 4) {
            return "研究生";
        } else if (max == 5) {
            return "博士生";
        } else if (max == 6) {
            return "博士后";
        } else {
            return "学历未知";
        }
    }

    /**
     * 管理员让一条投递记录进入下一个流程,通过
     *
     * @param deliverId
     */
    public void deliverToNextStep(Long deliverId) {
        ResumeDeliverEntity deliver = resumeDeliverRepository.findOne(deliverId);
        String oldCode = deliver.getRecruitmentState();
        deliver.setRecruitmentState(StatusCodeUtil.codeChange(oldCode, 1));
        resumeDeliverRepository.save(deliver);

        // 重新设置阅读状态为未读
        deliverRedis.setDeliverUnread(deliverId);
    }

    /**
     * 管理员回绝一条投递
     *
     * @param deliverId
     */
    public void refuseDeliver(Long deliverId) {
        ResumeDeliverEntity deliver = resumeDeliverRepository.findOne(deliverId);
        String oldCode = deliver.getRecruitmentState();
        deliver.setRecruitmentState(StatusCodeUtil.codeChange(oldCode, 2));
        resumeDeliverRepository.save(deliver);

        // 重新设置阅读状态为未读
        deliverRedis.setDeliverUnread(deliverId);
    }

    /**
     * 管理员从拒绝池中捞取记录
     *
     * @param deliverId
     */
    public void cancelRefuse(Long deliverId) {
        ResumeDeliverEntity deliver = resumeDeliverRepository.findOne(deliverId);
        String oldCode = deliver.getRecruitmentState();
        deliver.setRecruitmentState(StatusCodeUtil.cancelRefuse(oldCode));
        resumeDeliverRepository.save(deliver);

        // 重新设置阅读状态为未读
        deliverRedis.setDeliverUnread(deliverId);
    }

    /**
     * 管理员获取正在 简历审核 中的简历
     *
     * @param positionId
     * @param department
     * @return
     */
    public Page<DeliverDto> findDeliverInResumeReview(Pageable pageable,Long positionId, Long department) {
        // 获取到对应的状态码
        String queryCode = StatusCodeUtil.getCode(ResumeReview);
        Page<ResumeDeliverEntity> deliverEntityPages = resumeDeliverRepository.findAllByPositionIdAndRecruitmentStateContaining(pageable, positionId, queryCode);
        List<ResumeDeliverEntity> delivers = deliverEntityPages.getContent();

        PositionEntity position = positionRepository.findOne(positionId);
        Integer auth = 0;
        // 管理员的岗位与 简历审核 岗位 相同
        if (department == position.getResumeAuditDepartment()) {
            auth = 1;
        }
        if (delivers == null) return null;
        List<DeliverDto> deliverDtos = new ArrayList<>();
        for (ResumeDeliverEntity resumeDeliverEntity : delivers) {
            DeliverDto deliverDto = new DeliverDto();
            deliverDto.setId(resumeDeliverEntity.getId());
            deliverDto.setUsername(getUsernameByDeliver(resumeDeliverEntity));
            deliverDto.setSex(getSexByDeliver(resumeDeliverEntity));
            deliverDto.setHighestEducation(getMaxHighEducationByDeliver(resumeDeliverEntity));
            deliverDto.setDeliverDate(resumeDeliverEntity.getDeliverDate());
            deliverDto.setAuth(auth);
            deliverDtos.add(deliverDto);
        }
        Page<DeliverDto> resPage = new PageImpl<DeliverDto>(deliverDtos, pageable, deliverEntityPages.getTotalElements());
        return resPage;
    }



    /**
     * 管理员获取正在 HR初审 中的简历
     *
     * @param positionId
     * @param department
     * @return
     */
    public Page<DeliverDto> findDeliverInHRFristReview(Pageable pageable, Long positionId, Long department) {
        // 获取到对应的状态码
        String queryCode = StatusCodeUtil.getCode(HRFristReview);
        Page<ResumeDeliverEntity> deliverEntityPages = resumeDeliverRepository.findAllByPositionIdAndRecruitmentStateContaining(pageable, positionId, queryCode);
        List<ResumeDeliverEntity> delivers = deliverEntityPages.getContent();

        Integer auth = 0;
        if (department == PersonnelDepartmentId) {
            auth = 1;
        }

        List<DeliverDto> deliverDtos = new ArrayList<>();
        for (ResumeDeliverEntity resumeDeliverEntity : delivers) {
            DeliverDto deliverDto = new DeliverDto();
            deliverDto.setId(resumeDeliverEntity.getId());
            deliverDto.setUsername(getUsernameByDeliver(resumeDeliverEntity));
            deliverDto.setSex(getSexByDeliver(resumeDeliverEntity));
            deliverDto.setHighestEducation(getMaxHighEducationByDeliver(resumeDeliverEntity));
            deliverDto.setDeliverDate(resumeDeliverEntity.getDeliverDate());
            deliverDto.setAuth(auth);
            deliverDtos.add(deliverDto);
        }
        Page<DeliverDto> resPage = new PageImpl<DeliverDto>(deliverDtos, pageable, deliverEntityPages.getTotalElements());
        return resPage;
    }


    /**
     * 管理员获取正在 部门笔试 中的简历
     *
     * @param positionId
     * @param department
     * @return
     */
    public Page<DeliverDto> findDeliverInDepartmentWrittenExamination(Pageable pageable, Long positionId, Long department) {
        // 获取到对应的状态码
        String queryCode = StatusCodeUtil.getCode(DepartmentWrittenExamination);

        Page<ResumeDeliverEntity> deliverEntityPages = resumeDeliverRepository.findAllByPositionIdAndRecruitmentStateContaining(pageable, positionId, queryCode);
        Integer auth = 0;
        if (department == positionRepository.findOne(positionId).getDepartment()) {
            auth = 1;
        }
        List<ResumeDeliverEntity> delivers = deliverEntityPages.getContent();

        List<DeliverDto> deliverDtos = new ArrayList<>();
        for (ResumeDeliverEntity resumeDeliverEntity : delivers) {
            DeliverDto deliverDto = new DeliverDto();
            deliverDto.setId(resumeDeliverEntity.getId());
            deliverDto.setUsername(getUsernameByDeliver(resumeDeliverEntity));
            deliverDto.setSex(getSexByDeliver(resumeDeliverEntity));
            deliverDto.setHighestEducation(getMaxHighEducationByDeliver(resumeDeliverEntity));
            deliverDto.setDeliverDate(resumeDeliverEntity.getDeliverDate());
            deliverDto.setAuth(auth);
            deliverDtos.add(deliverDto);
        }
        Page<DeliverDto> resPage = new PageImpl<DeliverDto>(deliverDtos, pageable, deliverEntityPages.getTotalElements());
        return resPage;
    }

    /**
     * 管理员获取正在 部门面试 中的简历
     *
     * @param positionId
     * @param department
     * @return
     */
    public Page<DeliverDto> findDeliverInDepartmentInterview(Pageable pageable,Long positionId, Long department) {
        // 获取到对应的状态码
        String queryCode = StatusCodeUtil.getCode(DepartmentInterview);
        Page<ResumeDeliverEntity> deliverEntityPages = resumeDeliverRepository.findAllByPositionIdAndRecruitmentStateContaining(pageable, positionId, queryCode);
        List<ResumeDeliverEntity> delivers = deliverEntityPages.getContent();

        Integer auth = 0;
        if (department == positionRepository.findOne(positionId).getDepartment()) {
            auth = 1;
        }
        if (delivers == null) return null;
        List<DeliverDto> deliverDtos = new ArrayList<>();
        for (ResumeDeliverEntity resumeDeliverEntity : delivers) {
            DeliverDto deliverDto = new DeliverDto();
            deliverDto.setId(resumeDeliverEntity.getId());
            deliverDto.setUsername(getUsernameByDeliver(resumeDeliverEntity));
            deliverDto.setSex(getSexByDeliver(resumeDeliverEntity));
            deliverDto.setHighestEducation(getMaxHighEducationByDeliver(resumeDeliverEntity));
            deliverDto.setDeliverDate(resumeDeliverEntity.getDeliverDate());
            deliverDto.setAuth(auth);
            deliverDtos.add(deliverDto);
        }
        Page<DeliverDto> resPage = new PageImpl<DeliverDto>(deliverDtos, pageable, deliverEntityPages.getTotalElements());
        return resPage;
    }


    /**
     * 管理员获取正在 HR面试 中的简历
     *
     * @param positionId
     * @param department
     * @return
     */
    public Page<DeliverDto> findDeliverInHRInterview(Pageable pageable,Long positionId, Long department) {
        // 获取到对应的状态码
        String queryCode = StatusCodeUtil.getCode(HRInterview);
        Page<ResumeDeliverEntity> deliverEntityPages = resumeDeliverRepository.findAllByPositionIdAndRecruitmentStateContaining(pageable, positionId, queryCode);
        List<ResumeDeliverEntity> delivers = deliverEntityPages.getContent();

        Integer auth = 0;
        if (department == PersonnelDepartmentId) {
            auth = 1;
        }
        List<DeliverDto> deliverDtos = new ArrayList<>();
        for (ResumeDeliverEntity resumeDeliverEntity : delivers) {
            DeliverDto deliverDto = new DeliverDto();
            deliverDto.setId(resumeDeliverEntity.getId());
            deliverDto.setUsername(getUsernameByDeliver(resumeDeliverEntity));
            deliverDto.setSex(getSexByDeliver(resumeDeliverEntity));
            deliverDto.setHighestEducation(getMaxHighEducationByDeliver(resumeDeliverEntity));
            deliverDto.setDeliverDate(resumeDeliverEntity.getDeliverDate());
            deliverDto.setAuth(auth);
            deliverDtos.add(deliverDto);
        }
        Page<DeliverDto> resPage = new PageImpl<DeliverDto>(deliverDtos, pageable, deliverEntityPages.getTotalElements());
        return resPage;
    }


    /**
     * 管理员获取正在 已通过 的简历
     *
     * @param positionId
     * @param department
     * @return
     */
    public Page<DeliverDto> findDeliverInPass(Pageable pageable, Long positionId, Long department) {
        // 获取到对应的状态码
        String queryCode = StatusCodeUtil.getCode(Pass);
        Page<ResumeDeliverEntity> deliverEntityPages = resumeDeliverRepository.findAllByPositionIdAndRecruitmentStateContaining(pageable, positionId, queryCode);
        List<ResumeDeliverEntity> delivers = deliverEntityPages.getContent();

        Integer auth = 0;
        if (department == PersonnelDepartmentId) {
            auth = 1;
        }
        List<DeliverDto> deliverDtos = new ArrayList<>();
        for (ResumeDeliverEntity resumeDeliverEntity : delivers) {
            DeliverDto deliverDto = new DeliverDto();
            deliverDto.setId(resumeDeliverEntity.getId());
            deliverDto.setUsername(getUsernameByDeliver(resumeDeliverEntity));
            deliverDto.setSex(getSexByDeliver(resumeDeliverEntity));
            deliverDto.setHighestEducation(getMaxHighEducationByDeliver(resumeDeliverEntity));
            deliverDto.setDeliverDate(resumeDeliverEntity.getDeliverDate());
            deliverDto.setAuth(auth);
            deliverDtos.add(deliverDto);
        }
        Page<DeliverDto> resPage = new PageImpl<DeliverDto>(deliverDtos, pageable, deliverEntityPages.getTotalElements());
        return resPage;
    }

    /**
     * 管理员获取正在 已回绝 的简历
     *
     * @param positionId
     * @param department
     * @return
     */
    public Page<HashMap> findDeliverInRefuse(Pageable pageable, Long positionId, Long department) {
        // 获取到对应的状态码
        String queryCode = StatusCodeUtil.getCode(Refuse);
        Page<ResumeDeliverEntity> deliverEntityPages = resumeDeliverRepository.findAllByPositionIdAndRecruitmentStateContaining(pageable, positionId, queryCode);
        List<ResumeDeliverEntity> delivers = deliverEntityPages.getContent();

        Integer auth = 0;
        if (department == PersonnelDepartmentId) {
            auth = 1;
        }
        if (delivers == null) return null;
        List<DeliverDto> deliverDtos = new ArrayList<>();
        List<HashMap> res = new ArrayList<>();

        for (ResumeDeliverEntity resumeDeliverEntity : delivers) {
            HashMap<String, String> hashMap = new LinkedHashMap<>();
            hashMap.put("id", String.valueOf(resumeDeliverEntity.getId()));
            hashMap.put("username", getUsernameByDeliver(resumeDeliverEntity));
            hashMap.put("sex", getSexByDeliver(resumeDeliverEntity));
            hashMap.put("highestEducation", getMaxHighEducationByDeliver(resumeDeliverEntity));
            hashMap.put("deliverDate", String.valueOf(resumeDeliverEntity.getDeliverDate()));
            hashMap.put("refuseStep", StatusCodeUtil.getRefuseStep(resumeDeliverEntity.getRecruitmentState()));
            hashMap.put("auth", String.valueOf(auth));

            res.add(hashMap);
        }
        Page<HashMap> resPage = new PageImpl<HashMap>(res, pageable, deliverEntityPages.getTotalElements());
        return resPage;

    }


}
