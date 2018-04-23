package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午1:46 2018/4/13
 **/
@Service
public class PermissionService {

    @Autowired
    private ResumeRepository resumeRepository;

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

    /**
     * 判断某用户是否有修改某教育经历表的权限
     * @param userId
     * @param educationExperiencId
     * @return
     */
    public boolean EducationExperiencePermission(Long userId,Long educationExperiencId){
        Long resumeId = educationRepository.findOne(educationExperiencId).getResumeId();
        if(resumeId == resumeRepository.findByUserId(userId).getId()){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 判断某用户是否有修改某培训经历表的权限
     * @param userId
     * @param trainingExperienceId
     * @return
     */
    public boolean trainingExperiencePermission(Long userId,Long trainingExperienceId){
        Long resumeId = trainingRepository.findOne(trainingExperienceId).getResumeId();
        if(resumeId == resumeRepository.findByUserId(userId).getId()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 判断某用户是否有修改某项目经历表的权限
     * @param userId
     * @param projectExperienceId
     * @return
     */
    public boolean projectExperiencePermission(Long userId,Long projectExperienceId){
        Long resumeId = projectExperienceRepository.findOne(projectExperienceId).getResumeId();
        if(resumeId == resumeRepository.findByUserId(userId).getId()){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 判断某用户是否有修改某工作经历表的权限
     * @param userId
     * @param workExperienceId
     * @return
     */
    public boolean workExperiencePermission(Long userId,Long workExperienceId) {
        Long resumeId = workExperienceRepository.findOne(workExperienceId).getResumeId();
        if (resumeId == resumeRepository.findByUserId(userId).getId()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断某用户是否有修改某实习经历表的权限
     * @param userId
     * @param internshipId
     * @return
     */
    public boolean internshipExperiencePermission(Long userId, Long internshipId){
        Long resumeId = internshipExperienceRepository.findOne(internshipId).getResumeId();
        if (resumeId == resumeRepository.findByUserId(userId).getId()) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 判断某用户是否有修改某获奖经历表的权限
     * @param userId
     * @param awardId
     * @return
     */
    public boolean awardPermission (Long userId,Long awardId){
        Long resumeId = awardRepository.findOne(awardId).getResumeId();
        if (resumeId == resumeRepository.findByUserId(userId).getId()) {
            return true;
        } else {
            return false;
        }
    }
}
