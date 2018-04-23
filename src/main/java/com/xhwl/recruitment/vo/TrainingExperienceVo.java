package com.xhwl.recruitment.vo;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午11:19 2018/4/7
 **/
public class TrainingExperienceVo {
    private Long id;
    private Long resumeId;
    private String startTime;
    private String endTime;
    private String trainingInstitutions;
    private String trainingContent;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
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

    public String getTrainingInstitutions() {
        return trainingInstitutions;
    }

    public void setTrainingInstitutions(String trainingInstitutions) {
        this.trainingInstitutions = trainingInstitutions;
    }

    public String getTrainingContent() {
        return trainingContent;
    }

    public void setTrainingContent(String trainingContent) {
        this.trainingContent = trainingContent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TrainingExperienceVo{" +
                "id=" + id +
                ", resumeId=" + resumeId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", trainingInstitutions='" + trainingInstitutions + '\'' +
                ", trainingContent='" + trainingContent + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
