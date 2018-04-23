package com.xhwl.recruitment.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午4:24 2018/4/22
 **/
@Entity
@Table(name = "position", schema = "xhwl", catalog = "")
public class PositionEntity {
    private long id;
    private String positionName;
    private String resumeAuditPosition;
    private String assessmentPosition;
    private String positionType;
    private Integer recruitmentType;
    private String workPlace;
    private Integer education;
    private Integer recruitingNumbers;
    private Date deadline;
    private String jobResponsibilities;
    private String jobDescription;
    private Integer publishType;
    private Date publishDate;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "position_name", nullable = true, length = 255)
    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Basic
    @Column(name = "resume_audit_position", nullable = true, length = 255)
    public String getResumeAuditPosition() {
        return resumeAuditPosition;
    }

    public void setResumeAuditPosition(String resumeAuditPosition) {
        this.resumeAuditPosition = resumeAuditPosition;
    }

    @Basic
    @Column(name = "assessment_position", nullable = true, length = 255)
    public String getAssessmentPosition() {
        return assessmentPosition;
    }

    public void setAssessmentPosition(String assessmentPosition) {
        this.assessmentPosition = assessmentPosition;
    }

    @Basic
    @Column(name = "position_type", nullable = true, length = 255)
    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    @Basic
    @Column(name = "recruitment_type", nullable = true)
    public Integer getRecruitmentType() {
        return recruitmentType;
    }

    public void setRecruitmentType(Integer recruitmentType) {
        this.recruitmentType = recruitmentType;
    }

    @Basic
    @Column(name = "work_place", nullable = true, length = 255)
    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    @Basic
    @Column(name = "education", nullable = true)
    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    @Basic
    @Column(name = "recruiting_numbers", nullable = true)
    public Integer getRecruitingNumbers() {
        return recruitingNumbers;
    }

    public void setRecruitingNumbers(Integer recruitingNumbers) {
        this.recruitingNumbers = recruitingNumbers;
    }

    @Basic
    @Column(name = "deadline", nullable = true)
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Basic
    @Column(name = "job_responsibilities", nullable = true, length = -1)
    public String getJobResponsibilities() {
        return jobResponsibilities;
    }

    public void setJobResponsibilities(String jobResponsibilities) {
        this.jobResponsibilities = jobResponsibilities;
    }

    @Basic
    @Column(name = "job_description", nullable = true, length = -1)
    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Basic
    @Column(name = "publish_type", nullable = true)
    public Integer getPublishType() {
        return publishType;
    }

    public void setPublishType(Integer publishType) {
        this.publishType = publishType;
    }

    @Basic
    @Column(name = "publish_date", nullable = true)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Basic
    @Column(name = "gmt_create", nullable = true)
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @Basic
    @Column(name = "gmt_modified", nullable = true)
    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionEntity that = (PositionEntity) o;
        return id == that.id &&
                Objects.equals(positionName, that.positionName) &&
                Objects.equals(resumeAuditPosition, that.resumeAuditPosition) &&
                Objects.equals(assessmentPosition, that.assessmentPosition) &&
                Objects.equals(positionType, that.positionType) &&
                Objects.equals(recruitmentType, that.recruitmentType) &&
                Objects.equals(workPlace, that.workPlace) &&
                Objects.equals(education, that.education) &&
                Objects.equals(recruitingNumbers, that.recruitingNumbers) &&
                Objects.equals(deadline, that.deadline) &&
                Objects.equals(jobResponsibilities, that.jobResponsibilities) &&
                Objects.equals(jobDescription, that.jobDescription) &&
                Objects.equals(publishType, that.publishType) &&
                Objects.equals(publishDate, that.publishDate) &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(gmtModified, that.gmtModified);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, positionName, resumeAuditPosition, assessmentPosition, positionType, recruitmentType, workPlace, education, recruitingNumbers, deadline, jobResponsibilities, jobDescription, publishType, publishDate, gmtCreate, gmtModified);
    }
}
