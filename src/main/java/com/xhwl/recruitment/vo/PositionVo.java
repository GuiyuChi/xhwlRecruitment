package com.xhwl.recruitment.vo;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 上午10:52 2018/4/23
 **/
public class PositionVo {
    private Long id;
    private String positionName;//职位名称
    private Long department;//所属部门
    private Long resumeAuditDepartment;//简历审核部门
    private Long assessmentDepartment;//考核部门
    private String positionType;//职位类型
    private Integer recruitmentType;//招聘类型
    private String workPlace;//工作地点
    private String education;//学历要求
    private Integer recruitingNumbers;//招聘人数
    private String deadline;//截止日期
    private String jobResponsibilities;//工作职责
    private String jobRequirements;//职位要求
    private Integer publishType;//发布类型
    private String publishDate;//发布日期

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }

    public Long getResumeAuditDepartment() {
        return resumeAuditDepartment;
    }

    public void setResumeAuditDepartment(Long resumeAuditDepartment) {
        this.resumeAuditDepartment = resumeAuditDepartment;
    }

    public Long getAssessmentDepartment() {
        return assessmentDepartment;
    }

    public void setAssessmentDepartment(Long assessmentDepartment) {
        this.assessmentDepartment = assessmentDepartment;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public Integer getRecruitmentType() {
        return recruitmentType;
    }

    public void setRecruitmentType(Integer recruitmentType) {
        this.recruitmentType = recruitmentType;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getRecruitingNumbers() {
        return recruitingNumbers;
    }

    public void setRecruitingNumbers(Integer recruitingNumbers) {
        this.recruitingNumbers = recruitingNumbers;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getJobResponsibilities() {
        return jobResponsibilities;
    }

    public void setJobResponsibilities(String jobResponsibilities) {
        this.jobResponsibilities = jobResponsibilities;
    }

    public String getJobRequirements() {
        return jobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements = jobRequirements;
    }

    public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "PositionVo{" +
                "id=" + id +
                ", positionName='" + positionName + '\'' +
                ", department=" + department +
                ", resumeAuditDepartment=" + resumeAuditDepartment +
                ", assessmentDepartment=" + assessmentDepartment +
                ", positionType='" + positionType + '\'' +
                ", recruitmentType=" + recruitmentType +
                ", workPlace='" + workPlace + '\'' +
                ", education='" + education + '\'' +
                ", recruitingNumbers=" + recruitingNumbers +
                ", deadline='" + deadline + '\'' +
                ", jobResponsibilities='" + jobResponsibilities + '\'' +
                ", jobRequirements='" + jobRequirements + '\'' +
                ", publishType=" + publishType +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}
