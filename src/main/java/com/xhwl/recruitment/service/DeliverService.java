package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.*;
import com.xhwl.recruitment.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: guiyu
 * @Description: 用户投递简历时的逻辑，复制文件和数据库
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

    /**
     * 查找用户原先的resumeId
     *
     * @param userId
     * @return
     */
    private Long findResumeIdByUserId(Long userId) {
        return resumeRepository.findByUserId(userId).getId();
    }

    //todo 文件转移
    public HashMap<String, String> copyDocument() {
        HashMap<String, String> address = new HashMap<>();
        //测试的输入
        address.put("uploadResumePath", "dw/resume/321.docx");
        address.put("supportDetailPath", "dw/support/4fsaer.zip");
        address.put("photoPath", "dw/photo/45283.png");
        return address;
    }

    /**
     * 数据库的信息复制到仓库
     *
     * @param userId
     * @param address
     */
    @Transactional
    public void copyDatabase(Long userId, HashMap<String, String> address) {
        DwResumeEntity dwResumeEntity = copyResumeTable(userId, address);
        Long newResumeId = dwResumeEntity.getId();
        Long oldResumeId = findResumeIdByUserId(userId);
        copyPersonalInformation(userId, newResumeId);
        copyEducationExperience(oldResumeId, newResumeId);
        copyTrainingExperience(oldResumeId, newResumeId);
        copyProjectExperience(oldResumeId, newResumeId);

    }

    /**
     * 投递时将resume表复制一份到仓库
     *
     * @param userId
     */
    public DwResumeEntity copyResumeTable(Long userId, HashMap<String, String> address) {
        ResumeEntity resumeEntity = resumeRepository.findByUserId(userId);

        DwResumeEntity dwResumeEntity = new DwResumeEntity();
        dwResumeEntity.setUserId(resumeEntity.getUserId());
        dwResumeEntity.setSelfAssessment(resumeEntity.getSelfAssessment());
        dwResumeEntity.setPhotoPath(address.get("photoPath"));
        dwResumeEntity.setSupportDetailPath(address.get("supportDetailPath"));
        dwResumeEntity.setUploadResumePath(address.get("uploadResumePath"));
        dwResumeEntity.setResumesForm(resumeEntity.getResumesForm());
        return dwResumeRepository.save(dwResumeEntity);
    }

    /**
     * 复制个人信息表，根据用户id查到个人信息实体，复制一份并加入新的resumeId，然后存到仓库中
     *
     * @param userId
     * @param newResumeId
     */
    public void copyPersonalInformation(Long userId, Long newResumeId) {
        Long oldResumeId = findResumeIdByUserId(userId);

        PersonalInformationEntity personalInformationEntity = personalInformationRepository.findByResumeId(oldResumeId);

        DwPersonalInformationEntity dwPersonalInformationEntity = new DwPersonalInformationEntity();

        BeanUtils.copyProperties(personalInformationEntity, dwPersonalInformationEntity);

        dwPersonalInformationEntity.setResumeId(newResumeId);

        DwPersonalInformationEntity dwPersonalInformationEntity1 = dwPersonalInformationRepository.save(dwPersonalInformationEntity);

        log.info("deliverService copyPersonalInformation {}", dwPersonalInformationEntity1);
    }


    /**
     * 复制教育经历表
     *
     * @param oldResumeId
     * @param newResumeId
     */
    public void copyEducationExperience(Long oldResumeId, Long newResumeId) {

        List<EducationExperienceEntity> educations = educationRepository.findAllByResumeId(oldResumeId);

        for (int i = 0; i < educations.size(); i++) {
            DwEducationExperienceEntity dwEducation = new DwEducationExperienceEntity();
            BeanUtils.copyProperties(educations.get(i), dwEducation);
            dwEducation.setResumeId(newResumeId);
            DwEducationExperienceEntity dwr = dwEducationExperienceRepository.save(dwEducation);
            log.info("the {} result of copy educationExperience: {}", i, dwr);
        }
    }

    /**
     * 复制培训经历表
     *
     * @param oldResumeId
     * @param newResumeId
     */
    public void copyTrainingExperience(Long oldResumeId, Long newResumeId) {

        List<TrainingExperienceEntity> trains = trainingRepository.findAllByResumeId(oldResumeId);

        for (int i = 0; i < trains.size(); i++) {
            DwTrainingExperienceEntity dwTrain = new DwTrainingExperienceEntity();
            BeanUtils.copyProperties(trains.get(i), dwTrain);
            dwTrain.setResumeId(newResumeId);
            DwTrainingExperienceEntity dwt = dwTrainingExperienceRepository.save(dwTrain);
            log.info("the {} result of copy copyTrainingExperience: {}", i, dwt);
        }
    }

    /**
     * 复制项目经历
     *
     * @param oldResumeId
     * @param newResumeId
     */
    public void copyProjectExperience(Long oldResumeId, Long newResumeId) {
        List<ProjectExperienceEntity> projects = projectExperienceRepository.findAllByResumeId(oldResumeId);

        for (int i = 0; i < projects.size(); i++) {
            DwProjectExperienceEntity dwProject = new DwProjectExperienceEntity();
            BeanUtils.copyProperties(projects.get(i), dwProject);
            dwProject.setResumeId(newResumeId);
            DwProjectExperienceEntity dwp = dwProjectExperienceRepository.save(dwProject);
            log.info("the {} result of copy copyProjectExperience: {}", i, dwp);
        }

    }

    /**
     * @param oldResumeId
     * @param newResumeId
     */
    public void copyWorkExperience(Long oldResumeId, Long newResumeId) {
        List<WorkExperienceEntity> works = workExperienceRepository.findAllByResumeId(oldResumeId);

        for (int i=0;i<works.size();i++){
            DwWorkExperienceEntity dwWork = new DwWorkExperienceEntity();
            BeanUtils.copyProperties(works.get(i),dwWork);
        }
    }
}
