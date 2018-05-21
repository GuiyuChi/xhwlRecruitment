package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.*;
import com.xhwl.recruitment.domain.*;
import com.xhwl.recruitment.util.StatusCodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: guiyu
 * @Description: 用户投递简历时的逻辑，复制文件和数据库 以及其他相关内容
 * @Date: Create in 下午4:32 2018/4/22
 **/
@Service
@Slf4j
public class DeliverService {
    @Autowired
    ResumeRepository resumeRepository;
    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private PersonalInformationRepository personalInformationRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private ProjectExperienceRepository projectExperienceRepository;

    @Autowired
    private WorkExperienceRepository workExperienceRepository;

    @Autowired
    private InternshipExperienceRepository internshipExperienceRepository;

    @Autowired
    private AwardRepository awardRepository;

    @Autowired
    private JobIntentionRepository jobIntentionRepository;

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
    private FileService fileService;


    /**
     * 查找用户原先的resumeId
     *
     * @param userId
     * @return
     */
    private Long findResumeIdByUserId(Long userId) {
        return resumeRepository.findByUserId(userId).getId();
    }


    /**
     * 数据库的信息复制到仓库
     *
     * @param userId
     * @param address
     */
    @Transactional
    public Long copyDatabase(Long userId, HashMap<String, String> address) {
        DwResumeEntity dwResumeEntity = copyResumeTable(userId, address);
        Long newResumeId = dwResumeEntity.getId();
        Long oldResumeId = findResumeIdByUserId(userId);
        copyPersonalInformation(userId, newResumeId);
        copyEducationExperience(oldResumeId, newResumeId);
        copyTrainingExperience(oldResumeId, newResumeId);
        copyProjectExperience(oldResumeId, newResumeId);
        copyWorkExperience(oldResumeId, newResumeId);
        copyInternshipExperience(oldResumeId, newResumeId);
        copyAward(oldResumeId, newResumeId);
        copyJobIntention(oldResumeId, newResumeId);
        return newResumeId;
    }

    /**
     * 投递时将resume表复制一份到仓库
     *
     * @param userId
     */
    private DwResumeEntity copyResumeTable(Long userId, HashMap<String, String> address) {
        ResumeEntity resumeEntity = resumeRepository.findByUserId(userId);

        DwResumeEntity dwResumeEntity = new DwResumeEntity();
        dwResumeEntity.setUserId(resumeEntity.getUserId());
        dwResumeEntity.setSelfAssessment(resumeEntity.getSelfAssessment());
        dwResumeEntity.setPhotoPath(address.get("photoPath"));
        dwResumeEntity.setSupportDetailPath(address.get("supportDetailPath"));
        dwResumeEntity.setUploadResumePath(address.get("uploadResumePath"));
        dwResumeEntity.setResumesForm(resumeEntity.getResumesForm());
        DwResumeEntity dwr = dwResumeRepository.save(dwResumeEntity);
//        log.info("the result of copy copyJobIntention: {}", dwr);
        return dwr;
    }

    /**
     * 复制个人信息表，根据用户id查到个人信息实体，复制一份并加入新的resumeId，然后存到仓库中
     *
     * @param userId
     * @param newResumeId
     */
    private void copyPersonalInformation(Long userId, Long newResumeId) {
        Long oldResumeId = findResumeIdByUserId(userId);

        PersonalInformationEntity personalInformationEntity = personalInformationRepository.findByResumeId(oldResumeId);

        DwPersonalInformationEntity dwPersonalInformationEntity = new DwPersonalInformationEntity();

        BeanUtils.copyProperties(personalInformationEntity, dwPersonalInformationEntity);

        dwPersonalInformationEntity.setResumeId(newResumeId);

        DwPersonalInformationEntity dwPersonalInformationEntity1 = dwPersonalInformationRepository.save(dwPersonalInformationEntity);

//        log.info("deliverService copyPersonalInformation {}", dwPersonalInformationEntity1);
    }


    /**
     * 复制教育经历表
     *
     * @param oldResumeId
     * @param newResumeId
     */
    private void copyEducationExperience(Long oldResumeId, Long newResumeId) {

        List<EducationExperienceEntity> educations = educationRepository.findAllByResumeId(oldResumeId);

        for (int i = 0; i < educations.size(); i++) {
            DwEducationExperienceEntity dwEducation = new DwEducationExperienceEntity();
            BeanUtils.copyProperties(educations.get(i), dwEducation);
            dwEducation.setResumeId(newResumeId);
            DwEducationExperienceEntity dwr = dwEducationExperienceRepository.save(dwEducation);
//            log.info("the {} result of copy educationExperience: {}", i, dwr);
        }
    }

    /**
     * 复制培训经历表
     *
     * @param oldResumeId
     * @param newResumeId
     */
    private void copyTrainingExperience(Long oldResumeId, Long newResumeId) {

        List<TrainingExperienceEntity> trains = trainingRepository.findAllByResumeId(oldResumeId);

        for (int i = 0; i < trains.size(); i++) {
            DwTrainingExperienceEntity dwTrain = new DwTrainingExperienceEntity();
            BeanUtils.copyProperties(trains.get(i), dwTrain);
            dwTrain.setResumeId(newResumeId);
            DwTrainingExperienceEntity dwt = dwTrainingExperienceRepository.save(dwTrain);
//            log.info("the {} result of copy copyTrainingExperience: {}", i, dwt);
        }
    }

    /**
     * 复制项目经历
     *
     * @param oldResumeId
     * @param newResumeId
     */
    private void copyProjectExperience(Long oldResumeId, Long newResumeId) {
        List<ProjectExperienceEntity> projects = projectExperienceRepository.findAllByResumeId(oldResumeId);

        for (int i = 0; i < projects.size(); i++) {
            DwProjectExperienceEntity dwProject = new DwProjectExperienceEntity();
            BeanUtils.copyProperties(projects.get(i), dwProject);
            dwProject.setResumeId(newResumeId);
            DwProjectExperienceEntity dwp = dwProjectExperienceRepository.save(dwProject);
//            log.info("the {} result of copy copyProjectExperience: {}", i, dwp);
        }

    }

    /**
     * 复制工作经历
     *
     * @param oldResumeId
     * @param newResumeId
     */
    private void copyWorkExperience(Long oldResumeId, Long newResumeId) {
        List<WorkExperienceEntity> works = workExperienceRepository.findAllByResumeId(oldResumeId);

        for (int i = 0; i < works.size(); i++) {
            DwWorkExperienceEntity dwWork = new DwWorkExperienceEntity();
            BeanUtils.copyProperties(works.get(i), dwWork);
            dwWork.setResumeId(newResumeId);
            DwWorkExperienceEntity dww = dwWorkExperienceRepository.save(dwWork);
//            log.info("the {} result of copy copyWorkExperience: {}", i, dww);
        }
    }

    /**
     * 复制实习经历
     *
     * @param oldResumeId
     * @param newResumeId
     */
    private void copyInternshipExperience(Long oldResumeId, Long newResumeId) {
        List<InternshipExperienceEntity> internships = internshipExperienceRepository.findAllByResumeId(oldResumeId);

        for (int i = 0; i < internships.size(); i++) {
            DwInternshipExperienceEntity dwInternship = new DwInternshipExperienceEntity();
            BeanUtils.copyProperties(internships.get(i), dwInternship);
            dwInternship.setResumeId(newResumeId);
            DwInternshipExperienceEntity dwi = dwInternshipExperienceRepository.save(dwInternship);
//            log.info("the {} result of copy copyInternshipExperience: {}", i, dwi);
        }
    }


    /**
     * 复制奖励表
     *
     * @param oldResumeId
     * @param newResumeId
     */
    private void copyAward(Long oldResumeId, Long newResumeId) {
        List<AwardEntity> awards = awardRepository.findAllByResumeId(oldResumeId);

        for (int i = 0; i < awards.size(); i++) {
            DwAwardEntity dwAward = new DwAwardEntity();
            BeanUtils.copyProperties(awards.get(i), dwAward);
            dwAward.setResumeId(newResumeId);
            DwAwardEntity dwa = dwAwardRepository.save(dwAward);
//            log.info("the {} result of copy copyAward: {}", i, dwa);
        }
    }

    /**
     * 复制求职意向
     *
     * @param oldResumeId
     * @param newResumeId
     */
    private void copyJobIntention(Long oldResumeId, Long newResumeId) {
        JobIntentionEntity jobIntention = jobIntentionRepository.findByResumeId(oldResumeId);

        DwJobIntentionEntity dwJobIntention = new DwJobIntentionEntity();
        BeanUtils.copyProperties(jobIntention, dwJobIntention);
        dwJobIntention.setResumeId(newResumeId);
        DwJobIntentionEntity dwj = dwJobIntentionRepository.save(dwJobIntention);
//        log.info("the result of copy copyJobIntention: {}", dwj);
    }

    /**
     * 添加投递表
     *
     * @param positionId
     * @param userId
     * @param newResumeId
     */
    private Long addResumeDeliver(Long positionId, Long userId, Long newResumeId) {
        ResumeDeliverEntity resumeDeliverEntity = new ResumeDeliverEntity();

        resumeDeliverEntity.setPositionId(positionId);
        resumeDeliverEntity.setUserId(userId);
        resumeDeliverEntity.setDwResumeId(newResumeId);

        //初始化投递进度
        resumeDeliverEntity.setRecruitmentState(StatusCodeUtil.initCode());

        PositionEntity positionEntity = positionRepository.findOne(positionId);

        Date currentDate = new java.sql.Date(System.currentTimeMillis());
        resumeDeliverEntity.setDeliverDate(currentDate);
        resumeDeliverEntity.setEmailState(0);

        ResumeDeliverEntity rde = resumeDeliverRepository.save(resumeDeliverEntity);
//        log.info("the result of copy addResumeDeliver: {}", rde);
        return rde.getId();
    }

    /**
     * 用户投递简历,调用fileService
     *
     * @param positionId
     * @param userId
     * @return 投递表的ID
     */
    public Long deliver(Long positionId, Long userId) {
        HashMap<String, String> filespath = fileService.copyDocument(userId);
        Long newResumeId = copyDatabase(userId, filespath);
        Long resumeDeliverId = addResumeDeliver(positionId, userId, newResumeId);
        return resumeDeliverId;
    }

    /**
     * 判断是否是第一次投递
     *
     * @param userId
     * @param positionId
     * @return
     */
    public boolean isFirst(Long userId, Long positionId) {
        boolean flag = true;
        List<ResumeDeliverEntity> resumeDeliverEntities = resumeDeliverRepository.findAllByUserId(userId);

        if (resumeDeliverEntities == null) return true;
        for (ResumeDeliverEntity resumeDeliverEntity : resumeDeliverEntities) {
            if (resumeDeliverEntity.getPositionId() == positionId) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 判断用户的简历类型是否和岗位需要一致
     *
     * @param userId
     * @param positionId
     * @return
     */
    public boolean checkResumeType(Long userId, Long positionId) {
        Integer resumeType = Integer.valueOf(resumeRepository.findByUserId(userId).getResumesForm());
        Integer positionType = positionRepository.findOne(positionId).getRecruitmentType();

        if (resumeType == positionType) return true;
        else return false;
    }


    /**
     * 获取用户的投递情况
     *
     * @param userId
     * @return
     */
    public List<HashMap> findResumeDelivers(Long userId) {
        List<ResumeDeliverEntity> resumeDelivers = resumeDeliverRepository.findAllByUserId(userId);

        if (resumeDelivers == null) return null;
        List<HashMap> res = new ArrayList<>();
        for (ResumeDeliverEntity resumeDeliver : resumeDelivers) {
            HashMap<String, String> hashMap = new LinkedHashMap<>();
            hashMap.put("id", String.valueOf(resumeDeliver.getId()));
            Long positionId = resumeDeliver.getPositionId();
            PositionEntity positionEntity = positionRepository.findOne(positionId);
            // 确保岗位存在
            if (positionEntity != null) {
                hashMap.put("positionName", positionEntity.getPositionName());
                hashMap.put("recruitmentType", String.valueOf(positionEntity.getRecruitmentType()));
                hashMap.put("recruitmentState", StatusCodeUtil.code2View(resumeDeliver.getRecruitmentState()));
                res.add(hashMap);
            }
        }
        return res;
    }

    /**
     * 获取某岗位有多少条投递
     *
     * @param positionId
     * @return
     */
    public HashMap<String, Integer> getNumOfDeliver(Long positionId) {
        List<ResumeDeliverEntity> resumeDeliverEntities = resumeDeliverRepository.findAllByPositionId(positionId);
        HashMap<String, Integer> hashMap = new LinkedHashMap<>();
        hashMap.put("number", resumeDeliverEntities.size());
        return hashMap;
    }

    /**
     * 用户删除投递
     *
     * @param deliverId
     */
    public void deleteDeliver(Long deliverId) {
        resumeDeliverRepository.delete(deliverId);
    }
}
