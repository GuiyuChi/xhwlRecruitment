package com.xhwl.recruitment.vo;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 上午10:52 2018/4/23
 **/
public class PositionVo {
    private Long id;
    private String positionName;
    private String department;
    private String resumeAuditDepartment;
    private String assessmentDepartment;
    private String positionType;
    private Integer recruitmentType;
    private String workPlace;
    private String education;
    private Integer recruitingNumbers;
    private String deadline;
    private String jobResponsibilities;
    private String jobDescription;
    private Integer publishType;
    private String publishDate;

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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getResumeAuditDepartment() {
        return resumeAuditDepartment;
    }

    public void setResumeAuditDepartment(String resumeAuditDepartment) {
        this.resumeAuditDepartment = resumeAuditDepartment;
    }

    public String getAssessmentDepartment() {
        return assessmentDepartment;
    }

    public void setAssessmentDepartment(String assessmentDepartment) {
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

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
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
                ", department='" + department + '\'' +
                ", resumeAuditDepartment='" + resumeAuditDepartment + '\'' +
                ", assessmentDepartment='" + assessmentDepartment + '\'' +
                ", positionType='" + positionType + '\'' +
                ", recruitmentType=" + recruitmentType +
                ", workPlace='" + workPlace + '\'' +
                ", education='" + education + '\'' +
                ", recruitingNumbers=" + recruitingNumbers +
                ", deadline='" + deadline + '\'' +
                ", jobResponsibilities='" + jobResponsibilities + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", publishType=" + publishType +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}
