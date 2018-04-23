package com.xhwl.recruitment.vo;

import java.sql.Date;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午6:46 2018/4/7
 **/
public class EducationExperenceVo {
    private Long id;
    private String startTime;
    private String endTime;
    private String school;
    private String speciality;
    private Byte educationHistory;
    private Integer rank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Byte getEducationHistory() {
        return educationHistory;
    }

    public void setEducationHistory(Byte educationHistory) {
        this.educationHistory = educationHistory;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "EducationExperenceVo{" +
                "id=" + id +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", school='" + school + '\'' +
                ", speciality='" + speciality + '\'' +
                ", educationHistory=" + educationHistory +
                ", rank=" + rank +
                '}';
    }
}
