package com.xhwl.recruitment.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午4:24 2018/4/22
 **/
@Entity
@Table(name = "dw_education_experience", schema = "xhwl", catalog = "")
public class DwEducationExperienceEntity {
    private long id;
    private long resumeId;
    private Date startTime;
    private Date endTime;
    private String school;
    private String speciality;
    private Byte educationHistory;
    private Integer rank;

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
    @Column(name = "resume_id", nullable = false)
    public long getResumeId() {
        return resumeId;
    }

    public void setResumeId(long resumeId) {
        this.resumeId = resumeId;
    }

    @Basic
    @Column(name = "start_time", nullable = true)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "end_time", nullable = true)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "school", nullable = true, length = 50)
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "speciality", nullable = true, length = 50)
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Basic
    @Column(name = "education_history", nullable = true)
    public Byte getEducationHistory() {
        return educationHistory;
    }

    public void setEducationHistory(Byte educationHistory) {
        this.educationHistory = educationHistory;
    }

    @Basic
    @Column(name = "rank", nullable = true)
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DwEducationExperienceEntity that = (DwEducationExperienceEntity) o;
        return id == that.id &&
                resumeId == that.resumeId &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(school, that.school) &&
                Objects.equals(speciality, that.speciality) &&
                Objects.equals(educationHistory, that.educationHistory) &&
                Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, resumeId, startTime, endTime, school, speciality, educationHistory, rank);
    }

    @Override
    public String toString() {
        return "DwEducationExperienceEntity{" +
                "id=" + id +
                ", resumeId=" + resumeId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", school='" + school + '\'' +
                ", speciality='" + speciality + '\'' +
                ", educationHistory=" + educationHistory +
                ", rank=" + rank +
                '}';
    }
}
