package com.xhwl.recruitment.vo;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 上午10:52 2018/4/23
 **/
public class PositionVo {
    private long id;
    private String positionName;
    private String resumeAuditPosition;
    private String assessmentPosition;
    private String positionType;
    private Integer recruitmentType;
    private String workPlace;
    private Integer education;
    private Integer recruitingNumbers;
    private String deadline;
    private String jobResponsibilities;
    private String jobDescription;
    private Integer publishType;
    private String publishDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getResumeAuditPosition() {
        return resumeAuditPosition;
    }

    public void setResumeAuditPosition(String resumeAuditPosition) {
        this.resumeAuditPosition = resumeAuditPosition;
    }

    public String getAssessmentPosition() {
        return assessmentPosition;
    }

    public void setAssessmentPosition(String assessmentPosition) {
        this.assessmentPosition = assessmentPosition;
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

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
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
                ", resumeAuditPosition='" + resumeAuditPosition + '\'' +
                ", assessmentPosition='" + assessmentPosition + '\'' +
                ", positionType='" + positionType + '\'' +
                ", recruitmentType=" + recruitmentType +
                ", workPlace='" + workPlace + '\'' +
                ", education=" + education +
                ", recruitingNumbers=" + recruitingNumbers +
                ", deadline='" + deadline + '\'' +
                ", jobResponsibilities='" + jobResponsibilities + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", publishType=" + publishType +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}
