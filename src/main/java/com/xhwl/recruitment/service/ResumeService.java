package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.*;
import com.xhwl.recruitment.domain.*;
import com.xhwl.recruitment.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午6:22 2018/4/7
 **/
@Service
public class ResumeService {

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

    @Autowired
    private JobIntentionRepository jobIntentionRepository;

    //根据用户id查找简历表,未找到返回null
    public ResumeEntity findResumeByUserId(Long userId) {
        ResumeEntity resumeEntity = resumeRepository.findByUserId(userId);
        return resumeEntity;
    }

    /**
     * 新建一张简历，用户第一次创建简历时调用
     *
     * @param userId
     * @param resumes_form 简历表的类型
     * @return
     */
    public ResumeEntity createNewResume(Long userId, int resumes_form) {
        ResumeEntity resumeEntity = new ResumeEntity();
        resumeEntity.setUserId(userId);
        resumeEntity.setResumesForm((byte) resumes_form);

        return resumeRepository.save(resumeEntity);
    }

    /**
     * 根据用户名获取简历表
     *
     * @param userId
     * @return
     */
    public ResumeEntity getResume(Long userId) {
        return resumeRepository.findByUserId(userId);
    }

    /**
     * 修改resume表
     *
     * @param resumeVo
     * @return
     */
    public ResumeEntity modifyResume(ResumeVo resumeVo) {
        Long userId = resumeVo.getUserId();
        Long id = resumeRepository.findByUserId(userId).getId();

        ResumeEntity resumeEntity = new ResumeEntity();
        resumeEntity.setId(id);
        resumeEntity.setUserId(userId);
        resumeEntity.setSelfAssessment(resumeVo.getSelfAssessment());
        resumeEntity.setUploadResumePath(resumeVo.getUploadResumePath());
        resumeEntity.setSupportDetailPath(resumeVo.getSupportDetailPath());
        resumeEntity.setPhotoPath(resumeVo.getPhotoPath());
        resumeEntity.setResumesForm(new Integer(resumeVo.getResumesForm()).byteValue());

        return resumeRepository.saveAndFlush(resumeEntity);
    }


    /**
     * 新建个人信息表
     *
     * @param userId
     * @param personalInformationVo
     * @return
     */
    public PersonalInformationEntity addPersonalInformation(Long userId, PersonalInformationVo personalInformationVo) {

        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);

        Long resumeId = null;
        //todo 简历表不存在的异常处理
        if (resumeEntity != null) {
            resumeId = resumeEntity.getId();
        }

        PersonalInformationEntity personalInformationEntity = new PersonalInformationEntity();
        personalInformationEntity.setResumeId(resumeId);

        personalInformationEntity.setName(personalInformationVo.getName());
        personalInformationEntity.setSex(personalInformationVo.getSex().byteValue());
        personalInformationEntity.setIdType(personalInformationVo.getIdType().byteValue());
        personalInformationEntity.setIdNumber(personalInformationVo.getIdNumber());

        //时间处理
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            if (personalInformationVo.getBirthday() != null)
                birthday = new Date(dateFormat.parse(personalInformationVo.getBirthday()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        personalInformationEntity.setBirthday(birthday);

        personalInformationEntity.setEmail(personalInformationVo.getEmail());
        personalInformationEntity.setTelephone(personalInformationVo.getTelephone());
        personalInformationEntity.setMaritalStatus(personalInformationVo.getMaritalStatus().byteValue());
        personalInformationEntity.setWorkSeniority(personalInformationVo.getWorkSeniority());
        personalInformationEntity.setPoliticalStatus(personalInformationVo.getPoliticalStatus());
        personalInformationEntity.setPresentAddress(personalInformationVo.getPresentAddress());

        PersonalInformationEntity personalInformationEntity1 = personalInformationRepository.save(personalInformationEntity);

        return personalInformationEntity1;
    }

    /**
     * 修改个人信息表
     *
     * @param personalInformationVo
     * @return
     */
    public PersonalInformationEntity modifyPersonalInformation(PersonalInformationVo personalInformationVo) {
        Long id = personalInformationVo.getId();
        Long resumeId = personalInformationRepository.findOne(id).getResumeId();

        PersonalInformationEntity personalInformationEntity = new PersonalInformationEntity();
        personalInformationEntity.setId(id);
        personalInformationEntity.setResumeId(resumeId);

        personalInformationEntity.setName(personalInformationVo.getName());
        personalInformationEntity.setSex(personalInformationVo.getSex().byteValue());
        personalInformationEntity.setIdType(personalInformationVo.getIdType().byteValue());
        personalInformationEntity.setIdNumber(personalInformationVo.getIdNumber());

        //时间处理
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try {
            if (personalInformationVo.getBirthday() != null)
                birthday = new Date(dateFormat.parse(personalInformationVo.getBirthday()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        personalInformationEntity.setBirthday(birthday);

        personalInformationEntity.setEmail(personalInformationVo.getEmail());
        personalInformationEntity.setTelephone(personalInformationVo.getTelephone());
        personalInformationEntity.setMaritalStatus(personalInformationVo.getMaritalStatus().byteValue());
        personalInformationEntity.setWorkSeniority(personalInformationVo.getWorkSeniority());
        personalInformationEntity.setPoliticalStatus(personalInformationVo.getPoliticalStatus());
        personalInformationEntity.setPresentAddress(personalInformationVo.getPresentAddress());


        PersonalInformationEntity personalInformationEntity1 = personalInformationRepository.saveAndFlush(personalInformationEntity);
        return personalInformationEntity1;
    }

    /**
     * 获取个人信息表（一个人只有一张）
     *
     * @param userId
     * @return
     */
    public PersonalInformationEntity getPersonalInformation(Long userId) {
        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);

        Long resumeId = null;
        if (resumeEntity != null) {
            resumeId = resumeEntity.getId();
        }

        PersonalInformationEntity personalInformationEntity = personalInformationRepository.findByResumeId(resumeId);
        return personalInformationEntity;
    }


    /**
     * 新建一项教育经历
     *
     * @param userId
     * @param educationExperenceVo
     * @return
     */
    public EducationExperienceEntity addEducationExperence(Long userId, EducationExperenceVo educationExperenceVo) {

        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);

        Long resumeId = null;
        //todo 简历表不存在的异常处理  重复创建完全相同的教育经历表的异常处理 时间格式异常
        if (resumeEntity != null) {
            resumeId = resumeEntity.getId();
        }

        EducationExperienceEntity educationExperienceEntity = new EducationExperienceEntity();
        educationExperienceEntity.setResumeId(resumeId);

        //时间处理
        String start = educationExperenceVo.getStartTime();
        String end = educationExperenceVo.getEndTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;

        //可能传入空值
        try {
            if (start != null)
                startTime = new Date(dateFormat.parse(start).getTime());
            else
                startTime = null;
            if (end != null)
                endTime = new Date(dateFormat.parse(end).getTime());
            else
                endTime = null;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        educationExperienceEntity.setStartTime(startTime);
        educationExperienceEntity.setEndTime(endTime);

        educationExperienceEntity.setSchool(educationExperenceVo.getSchool());
        educationExperienceEntity.setSpeciality(educationExperenceVo.getSpeciality());
        educationExperienceEntity.setEducationHistory(educationExperenceVo.getEducationHistory());
        educationExperienceEntity.setRank(educationExperenceVo.getRank());

        EducationExperienceEntity educationExperienceEntity1 = educationRepository.save(educationExperienceEntity);
        return educationExperienceEntity1;
    }

    /**
     * 修改教育经历表
     *
     * @param educationExperenceVo
     * @return
     */
    public EducationExperienceEntity modifyEducationExperience(EducationExperenceVo educationExperenceVo) {
        //获取原先的简历号 resumeId
        Long id = educationExperenceVo.getId();
        Long resumeId = educationRepository.findOne(id).getResumeId();

        EducationExperienceEntity educationExperienceEntity = new EducationExperienceEntity();
        educationExperienceEntity.setId(id);
        educationExperienceEntity.setResumeId(resumeId);

        //时间处理
        String start = educationExperenceVo.getStartTime();
        String end = educationExperenceVo.getEndTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        try {
            if (start != null) {
                startTime = new Date(dateFormat.parse(start).getTime());
            } else {
                startTime = null;
            }
            if (end != null) {
                endTime = new Date(dateFormat.parse(end).getTime());
            } else {
                endTime = null;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        educationExperienceEntity.setStartTime(startTime);
        educationExperienceEntity.setEndTime(endTime);

        educationExperienceEntity.setSchool(educationExperenceVo.getSchool());
        educationExperienceEntity.setSpeciality(educationExperenceVo.getSpeciality());
        educationExperienceEntity.setEducationHistory(educationExperenceVo.getEducationHistory());
        educationExperienceEntity.setRank(educationExperenceVo.getRank());

        EducationExperienceEntity educationExperienceEntity1 = educationRepository.saveAndFlush(educationExperienceEntity);
        return educationExperienceEntity1;
    }

    /**
     * internship_experience
     * 根据id删除对应的教育经历表
     *
     * @param educationExperienceId
     */
    public void deleteEducationExperience(Long educationExperienceId) {
        educationRepository.delete(educationExperienceId);
    }

    /**
     * 获取教育经历
     *
     * @param userId
     * @return
     */
    public List<EducationExperienceEntity> getEducationExperiences(Long userId) {
        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);

        Long resumeId = null;
        //todo 简历表不存在的异常处理
        if (resumeEntity != null) {
            resumeId = resumeEntity.getId();
        }

        List<EducationExperienceEntity> educationExperienceEntities = new ArrayList<>();
        educationExperienceEntities = educationRepository.findAllByResumeId(resumeId);
        return educationExperienceEntities;
    }


    /**
     * 新建一项培训经历
     *
     * @param userId
     * @param trainingExperienceVo
     * @return
     */
    public TrainingExperienceEntity addTrainingExperience(Long userId, TrainingExperienceVo trainingExperienceVo) {
        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);

        Long resumeId = null;
        //todo 简历表不存在的异常处理
        if (resumeEntity != null) {
            resumeId = resumeEntity.getId();
        }

        TrainingExperienceEntity trainingExperienceEntity = new TrainingExperienceEntity();


        //时间处理
        String start = trainingExperienceVo.getStartTime();
        String end = trainingExperienceVo.getEndTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = (start != null ? new Date(dateFormat.parse(start).getTime()) : null);
            endTime = (end != null ? new Date(dateFormat.parse(end).getTime()) : null);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        trainingExperienceEntity.setResumeId(resumeId);
        trainingExperienceEntity.setStartTime(startTime);
        trainingExperienceEntity.setEndTime(endTime);
        trainingExperienceEntity.setTrainingInstitutions(trainingExperienceVo.getTrainingInstitutions());
        trainingExperienceEntity.setTrainingContent(trainingExperienceVo.getTrainingContent());
        trainingExperienceEntity.setDescription(trainingExperienceVo.getDescription());

        TrainingExperienceEntity trainingExperienceEntity1 = trainingRepository.save(trainingExperienceEntity);
        return trainingExperienceEntity1;
    }

    /**
     * 修改培训经历表
     *
     * @param trainingExperienceVo
     * @return
     */
    public TrainingExperienceEntity modifyTrainingExperience(TrainingExperienceVo trainingExperienceVo) {
        //获取原先的简历号 resumeId
        Long id = trainingExperienceVo.getId();
        Long resumeId = trainingRepository.findOne(id).getResumeId();

        //时间处理
        String start = trainingExperienceVo.getStartTime();
        String end = trainingExperienceVo.getEndTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = (start != null ? new Date(dateFormat.parse(start).getTime()) : null);
            endTime = (end != null ? new Date(dateFormat.parse(end).getTime()) : null);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TrainingExperienceEntity trainingExperienceEntity = new TrainingExperienceEntity();

        trainingExperienceEntity.setId(id);
        trainingExperienceEntity.setResumeId(resumeId);
        trainingExperienceEntity.setStartTime(startTime);
        trainingExperienceEntity.setEndTime(endTime);
        trainingExperienceEntity.setTrainingInstitutions(trainingExperienceVo.getTrainingInstitutions());
        trainingExperienceEntity.setTrainingContent(trainingExperienceVo.getTrainingContent());
        trainingExperienceEntity.setDescription(trainingExperienceVo.getDescription());

        TrainingExperienceEntity trainingExperienceEntity1 = trainingRepository.saveAndFlush(trainingExperienceEntity);
        return trainingExperienceEntity1;
    }

    /**
     * 根据id删除对应的培训经历表
     *
     * @param id
     */
    public void deleteTrainingExperience(Long id) {
        trainingRepository.delete(id);
    }

    /**
     * 获取培训经历表
     *
     * @param userId
     * @return
     */
    public List<TrainingExperienceEntity> getTrainExperiences(Long userId) {
        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);

        Long resumeId = null;
        //todo 简历表不存在的异常处理
        if (resumeEntity != null) {
            resumeId = resumeEntity.getId();
        }

        List<TrainingExperienceEntity> trainingExperienceEntities = trainingRepository.findAllByResumeId(resumeId);
        return trainingExperienceEntities;
    }


    /**
     * 新建一项项目经历
     *
     * @param userId
     * @param projectExperienceVo
     * @return
     */
    public ProjectExperienceEntity addProjectExperience(Long userId, ProjectExperienceVo projectExperienceVo) {
        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);

        Long resumeId = null;
        //todo 简历表不存在的异常处理
        if (resumeEntity != null) {
            resumeId = resumeEntity.getId();
        }

        ProjectExperienceEntity projectExperienceEntity = new ProjectExperienceEntity();

        projectExperienceEntity.setResumeId(resumeId);
        projectExperienceEntity.setProjectName(projectExperienceVo.getProjectName());
        projectExperienceEntity.setProjectRole(projectExperienceVo.getProjectRole());
        projectExperienceEntity.setProjectDescription(projectExperienceVo.getProjectDescription());

        return projectExperienceRepository.save(projectExperienceEntity);
    }

    /**
     * 修改项目经历，需传入项目经历id
     *
     * @param projectExperienceVo
     * @return
     */
    public ProjectExperienceEntity modifyProjectExperience(ProjectExperienceVo projectExperienceVo) {
        //获取原先的简历号 resumeId
        Long id = projectExperienceVo.getId();
        Long resumeId = projectExperienceRepository.findOne(id).getResumeId();

        ProjectExperienceEntity projectExperienceEntity = new ProjectExperienceEntity();

        projectExperienceEntity.setId(id);
        projectExperienceEntity.setResumeId(resumeId);
        projectExperienceEntity.setProjectName(projectExperienceVo.getProjectName());
        projectExperienceEntity.setProjectRole(projectExperienceVo.getProjectRole());
        projectExperienceEntity.setProjectDescription(projectExperienceVo.getProjectDescription());

        return projectExperienceRepository.saveAndFlush(projectExperienceEntity);
    }

    /**
     * 删除项目经历
     *
     * @param id
     */
    public void deleteProjectExperience(Long id) {
        projectExperienceRepository.delete(id);
    }

    /**
     * 获取项目经历表
     *
     * @param userId
     * @return
     */
    public List<ProjectExperienceEntity> getProjectExperiences(Long userId) {
        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);

        Long resumeId = null;
        //todo 简历表不存在的异常处理
        if (resumeEntity != null) {
            resumeId = resumeEntity.getId();
        }

        return projectExperienceRepository.findAllByResumeId(resumeId);
    }


    /**
     * 新建一项工作经历
     *
     * @param userId
     * @param workExperienceVo
     * @return
     */
    public WorkExperienceEntity addWorkExperience(Long userId, WorkExperienceVo workExperienceVo) {
        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);

        Long resumeId = null;
        if (resumeEntity != null) {
            resumeId = resumeEntity.getId();
        }


        //时间处理
        String start = workExperienceVo.getStartTime();
        String end = workExperienceVo.getEndTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = (start != null ? new Date(dateFormat.parse(start).getTime()) : null);
            endTime = (end != null ? new Date(dateFormat.parse(end).getTime()) : null);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        WorkExperienceEntity workExperienceEntity = new WorkExperienceEntity();

        workExperienceEntity.setResumeId(resumeId);
        workExperienceEntity.setStartTime(startTime);
        workExperienceEntity.setEndTime(endTime);
        workExperienceEntity.setCompany(workExperienceVo.getCompany());
        workExperienceEntity.setPosition(workExperienceVo.getPosition());
        workExperienceEntity.setDescription(workExperienceVo.getDescription());
        return workExperienceRepository.save(workExperienceEntity);

    }

    /**
     * 修改工作经历，按id修改
     *
     * @param workExperienceVo
     * @return
     */
    public WorkExperienceEntity modifyWorkExperience(WorkExperienceVo workExperienceVo) {
        // 获取原先的简历号
        Long id = workExperienceVo.getId();
        Long resumeId = workExperienceRepository.findOne(id).getResumeId();

        WorkExperienceEntity workExperienceEntity = new WorkExperienceEntity();

        //时间处理
        String start = workExperienceVo.getStartTime();
        String end = workExperienceVo.getEndTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = (start != null ? new Date(dateFormat.parse(start).getTime()) : null);
            endTime = (end != null ? new Date(dateFormat.parse(end).getTime()) : null);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        workExperienceEntity.setId(id);
        workExperienceEntity.setResumeId(resumeId);
        workExperienceEntity.setStartTime(startTime);
        workExperienceEntity.setEndTime(endTime);
        workExperienceEntity.setCompany(workExperienceVo.getCompany());
        workExperienceEntity.setPosition(workExperienceVo.getPosition());
        workExperienceEntity.setDescription(workExperienceVo.getDescription());
        return workExperienceRepository.save(workExperienceEntity);
    }

    /**
     * 删除一项工作经历
     *
     * @param
     */
    public void deleteWorkExperience(Long id) {
        workExperienceRepository.delete(id);
    }

    /**
     * 获取工作经历
     *
     * @param userId
     * @return
     */
    public List<WorkExperienceEntity> getWorkExperiences(Long userId) {
        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);

        Long resumeId = null;
        if (resumeEntity != null) {
            resumeId = resumeEntity.getId();
        }

        return workExperienceRepository.findAllByResumeId(resumeId);
    }


    /**
     * 新建一项实习经历
     *
     * @param userId
     * @param internshipExperienceVo
     * @return
     */
    public InternshipExperienceEntity addInternshipExperience(Long userId, InternshipExperienceVo internshipExperienceVo) {
        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);
        Long resumeId = resumeEntity.getId();

        //时间处理
        String start = internshipExperienceVo.getStartTime();
        String end = internshipExperienceVo.getEndTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = (start != null ? new Date(dateFormat.parse(start).getTime()) : null);
            endTime = (end != null ? new Date(dateFormat.parse(end).getTime()) : null);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        InternshipExperienceEntity internshipExperienceEntity = new InternshipExperienceEntity();

        internshipExperienceEntity.setResumeId(resumeId);
        internshipExperienceEntity.setStartTime(startTime);
        internshipExperienceEntity.setEndTime(endTime);
        internshipExperienceEntity.setCompany(internshipExperienceVo.getCompany());
        internshipExperienceEntity.setPosition(internshipExperienceVo.getPosition());
        internshipExperienceEntity.setDescription(internshipExperienceVo.getDescription());

        return internshipExperienceRepository.save(internshipExperienceEntity);
    }

    /**
     * 修改实习经历，按id修改
     *
     * @param internshipExperienceVo
     * @return
     */
    public InternshipExperienceEntity modifyInternshipExperience(InternshipExperienceVo internshipExperienceVo) {
        // 获取原先的简历号
        Long id = internshipExperienceVo.getId();
        Long resumeId = internshipExperienceRepository.getOne(id).getResumeId();

        //时间处理
        String start = internshipExperienceVo.getStartTime();
        String end = internshipExperienceVo.getEndTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = null;
        Date endTime = null;
        try {
            startTime = (start != null ? new Date(dateFormat.parse(start).getTime()) : null);
            endTime = (end != null ? new Date(dateFormat.parse(end).getTime()) : null);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        InternshipExperienceEntity internshipExperienceEntity = new InternshipExperienceEntity();

        internshipExperienceEntity.setId(id);
        internshipExperienceEntity.setResumeId(resumeId);
        internshipExperienceEntity.setStartTime(startTime);
        internshipExperienceEntity.setEndTime(endTime);
        internshipExperienceEntity.setCompany(internshipExperienceVo.getCompany());
        internshipExperienceEntity.setPosition(internshipExperienceVo.getPosition());
        internshipExperienceEntity.setDescription(internshipExperienceVo.getDescription());

        return internshipExperienceRepository.saveAndFlush(internshipExperienceEntity);
    }

    /**
     * 删除一项实习经历
     *
     * @param id
     */
    public void deleteInternshipExperience(Long id) {
        internshipExperienceRepository.delete(id);
    }

    /**
     * 获取实习经历
     *
     * @param userId
     * @return
     */
    public List<InternshipExperienceEntity> getInternshipExperiences(Long userId) {
        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);
        Long resumeId = resumeEntity.getId();

        return internshipExperienceRepository.findAllByResumeId(resumeId);
    }

    /**
     * 新建一项奖项
     *
     * @param userId
     * @param awardVo
     * @return
     */
    public AwardEntity addAward(Long userId, AwardVo awardVo) {
        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);
        Long resumeId = resumeEntity.getId();

        //时间处理
        String aw = awardVo.getDateOfAward();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = (aw != null ? new Date(dateFormat.parse(aw).getTime()) : null);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        AwardEntity awardEntity = new AwardEntity();

        awardEntity.setResumeId(resumeId);
        awardEntity.setAwardName(awardVo.getAwardName());
        awardEntity.setDateOfAward(date);

        return awardRepository.save(awardEntity);
    }

    /**
     * 修改奖励，按id修改
     *
     * @param awardVo
     * @return
     */
    public AwardEntity modifyAward(AwardVo awardVo) {
        // 获取原先的简历号
        Long id = awardVo.getId();
        Long resumeId = awardRepository.getOne(id).getResumeId();

        //时间处理
        String aw = awardVo.getDateOfAward();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = (aw != null ? new Date(dateFormat.parse(aw).getTime()) : null);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        AwardEntity awardEntity = new AwardEntity();

        awardEntity.setId(id);
        awardEntity.setResumeId(resumeId);
        awardEntity.setAwardName(awardVo.getAwardName());
        awardEntity.setDateOfAward(date);

        return awardRepository.saveAndFlush(awardEntity);
    }

    /**
     * 删除一项奖励
     *
     * @param awardId
     */
    public void deleteAward(Long awardId) {
        awardRepository.delete(awardId);
    }

    /**
     * 获取用户的奖励
     *
     * @param userId
     * @return
     */
    public List<AwardEntity> getAward(Long userId) {
        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);
        Long resumeId = resumeEntity.getId();

        return awardRepository.findAllByResumeId(resumeId);
    }

    /**
     * 新建工作意向
     *
     * @param userId
     * @param jobIntensionVo
     * @return
     */
    public JobIntentionEntity addJobIntension(Long userId, JobIntentionVo jobIntensionVo) {
        //获得resumeId

        ResumeEntity resumeEntity = findResumeByUserId(userId);
        Long resumeId = resumeEntity.getId();

        //时间处理
        String et = jobIntensionVo.getExpectedTimeForDuty();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expectedTimeForDuty = null;
        try {
            expectedTimeForDuty = (et != null) ? new Date(dateFormat.parse(et).getTime()) : null;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JobIntentionEntity JobIntentionEntity = new JobIntentionEntity();
        JobIntentionEntity.setResumeId(resumeId);
        JobIntentionEntity.setSalary(jobIntensionVo.getSalary());
        JobIntentionEntity.setWorkPlace(jobIntensionVo.getWorkPlace());
        JobIntentionEntity.setExpectedTimeForDuty(expectedTimeForDuty);

        return jobIntentionRepository.save(JobIntentionEntity);
    }

    /**
     * 修改工作意向
     *
     * @param jobIntensionVo
     * @return
     */
    public JobIntentionEntity modifyJobIntension(JobIntentionVo jobIntensionVo) {
        Long id = jobIntensionVo.getId();
        Long resumeId = jobIntentionRepository.findOne(id).getResumeId();

        //时间处理
        String et = jobIntensionVo.getExpectedTimeForDuty();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expectedTimeForDuty = null;
        try {
            expectedTimeForDuty = (et != null) ? new Date(dateFormat.parse(et).getTime()) : null;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        JobIntentionEntity JobIntentionEntity = new JobIntentionEntity();
        JobIntentionEntity.setId(id);
        JobIntentionEntity.setResumeId(resumeId);
        JobIntentionEntity.setSalary(jobIntensionVo.getSalary());
        JobIntentionEntity.setWorkPlace(jobIntensionVo.getWorkPlace());
        JobIntentionEntity.setExpectedTimeForDuty(expectedTimeForDuty);

        return jobIntentionRepository.saveAndFlush(JobIntentionEntity);
    }

    /**
     * 获取工作意向
     *
     * @param userId
     * @return
     */
    public JobIntentionEntity getJobIntension(Long userId) {
        //根据用户ID查到简历表id
        ResumeEntity resumeEntity = findResumeByUserId(userId);

        Long resumeId = resumeEntity.getId();

        return jobIntentionRepository.findByResumeId(resumeId);
    }

    /**
     * 根据用户名查找自我介绍
     *
     * @param userId
     * @return
     */
    public String getSelfAssessment(Long userId) {
        ResumeEntity resumeEntity = resumeRepository.findByUserId(userId);
        return resumeEntity.getSelfAssessment();
    }

    /**
     * 修改用户自我介绍
     *
     * @param userId
     * @param selfAssessment
     * @return
     */
    public String modifySelfAssessment(Long userId, String selfAssessment) {
        ResumeEntity resumeEntity = resumeRepository.findByUserId(userId);
        resumeEntity.setSelfAssessment(selfAssessment);
        resumeRepository.saveAndFlush(resumeEntity);
        return selfAssessment;
    }

}
