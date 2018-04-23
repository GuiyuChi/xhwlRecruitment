package com.xhwl.recruitment.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午4:24 2018/4/22
 **/
@Entity
@Table(name = "dw_resume", schema = "xhwl", catalog = "")
public class DwResumeEntity {
    private long id;
    private long userId;
    private String selfAssessment;
    private String uploadResumePath;
    private String supportDetailPath;
    private String photoPath;
    private byte resumesForm;

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
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "self_assessment", nullable = true, length = -1)
    public String getSelfAssessment() {
        return selfAssessment;
    }

    public void setSelfAssessment(String selfAssessment) {
        this.selfAssessment = selfAssessment;
    }

    @Basic
    @Column(name = "upload_resume_path", nullable = true, length = 255)
    public String getUploadResumePath() {
        return uploadResumePath;
    }

    public void setUploadResumePath(String uploadResumePath) {
        this.uploadResumePath = uploadResumePath;
    }

    @Basic
    @Column(name = "support_detail_path", nullable = true, length = 255)
    public String getSupportDetailPath() {
        return supportDetailPath;
    }

    public void setSupportDetailPath(String supportDetailPath) {
        this.supportDetailPath = supportDetailPath;
    }

    @Basic
    @Column(name = "photo_path", nullable = true, length = 255)
    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Basic
    @Column(name = "resumes_form", nullable = false)
    public byte getResumesForm() {
        return resumesForm;
    }

    public void setResumesForm(byte resumesForm) {
        this.resumesForm = resumesForm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DwResumeEntity that = (DwResumeEntity) o;
        return id == that.id &&
                userId == that.userId &&
                resumesForm == that.resumesForm &&
                Objects.equals(selfAssessment, that.selfAssessment) &&
                Objects.equals(uploadResumePath, that.uploadResumePath) &&
                Objects.equals(supportDetailPath, that.supportDetailPath) &&
                Objects.equals(photoPath, that.photoPath);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, selfAssessment, uploadResumePath, supportDetailPath, photoPath, resumesForm);
    }
}
