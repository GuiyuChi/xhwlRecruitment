package com.xhwl.recruitment.vo;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午2:27 2018/4/12
 **/
public class ResumeVo {
    private long id;
    private long userId;
    private String selfAssessment;
    private String uploadResumePath;
    private String supportDetailPath;
    private String photoPath;
    private int resumesForm;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getSelfAssessment() {
        return selfAssessment;
    }

    public void setSelfAssessment(String selfAssessment) {
        this.selfAssessment = selfAssessment;
    }

    public String getUploadResumePath() {
        return uploadResumePath;
    }

    public void setUploadResumePath(String uploadResumePath) {
        this.uploadResumePath = uploadResumePath;
    }

    public String getSupportDetailPath() {
        return supportDetailPath;
    }

    public void setSupportDetailPath(String supportDetailPath) {
        this.supportDetailPath = supportDetailPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getResumesForm() {
        return resumesForm;
    }

    public void setResumesForm(int resumesForm) {
        this.resumesForm = resumesForm;
    }

    @Override
    public String toString() {
        return "ResumeVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", selfAssessment='" + selfAssessment + '\'' +
                ", uploadResumePath='" + uploadResumePath + '\'' +
                ", supportDetailPath='" + supportDetailPath + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", resumesForm=" + resumesForm +
                '}';
    }
}
