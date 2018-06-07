package com.xhwl.recruitment.domain;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午3:38 2018/6/7
 **/
@Entity
@Table(name = "position", schema = "xhwl", catalog = "")
public class PositionEntity {
    private long id;
    private String positionName;
    private Long department;
    private Long resumeAuditDepartment;
    private Long assessmentDepartment;
    private String positionType;
    private Integer recruitmentType;
    private String workSeniority;
    private String workPlace;
    private String salary;
    private String education;
    private Integer recruitingNumbers;
    private Date deadline;
    private String jobResponsibilities;
    private String jobRequirements;
    private Integer publishType;
    private Date publishDate;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "position_name", nullable = true, length = 255)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Basic
    @Column(name = "department", nullable = true)
    public Long getDepartment() {
        return department;
    }

    public void setDepartment(Long department) {
        this.department = department;
    }

    @Basic
    @Column(name = "resume_audit_department", nullable = true)
    public Long getResumeAuditDepartment() {
        return resumeAuditDepartment;
    }

    public void setResumeAuditDepartment(Long resumeAuditDepartment) {
        this.resumeAuditDepartment = resumeAuditDepartment;
    }

    @Basic
    @Column(name = "assessment_department", nullable = true)
    public Long getAssessmentDepartment() {
        return assessmentDepartment;
    }

    public void setAssessmentDepartment(Long assessmentDepartment) {
        this.assessmentDepartment = assessmentDepartment;
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
    @Column(name = "work_seniority", nullable = true, length = 255)
    public String getWorkSeniority() {
        return workSeniority;
    }

    public void setWorkSeniority(String workSeniority) {
        this.workSeniority = workSeniority;
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
    @Column(name = "salary", nullable = true, length = 255)
    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "education", nullable = true, length = 255)
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
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
    @Column(name = "job_requirements", nullable = true, length = -1)
    public String getJobRequirements() {
        return jobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements = jobRequirements;
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
                Objects.equals(department, that.department) &&
                Objects.equals(resumeAuditDepartment, that.resumeAuditDepartment) &&
                Objects.equals(assessmentDepartment, that.assessmentDepartment) &&
                Objects.equals(positionType, that.positionType) &&
                Objects.equals(recruitmentType, that.recruitmentType) &&
                Objects.equals(workSeniority, that.workSeniority) &&
                Objects.equals(workPlace, that.workPlace) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(education, that.education) &&
                Objects.equals(recruitingNumbers, that.recruitingNumbers) &&
                Objects.equals(deadline, that.deadline) &&
                Objects.equals(jobResponsibilities, that.jobResponsibilities) &&
                Objects.equals(jobRequirements, that.jobRequirements) &&
                Objects.equals(publishType, that.publishType) &&
                Objects.equals(publishDate, that.publishDate) &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(gmtModified, that.gmtModified);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, positionName, department, resumeAuditDepartment, assessmentDepartment, positionType, recruitmentType, workSeniority, workPlace, salary, education, recruitingNumbers, deadline, jobResponsibilities, jobRequirements, publishType, publishDate, gmtCreate, gmtModified);
    }
}
