package com.xhwl.recruitment.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午7:16 2018/4/23
 **/
@Entity
@Table(name = "resume_deliver", schema = "xhwl", catalog = "")
public class ResumeDeliverEntity {
    private long id;
    private Long positionId;
    private Long userId;
    private Long dwResumeId;
    private String authorityCode;
    private Integer recruitmentState;
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
    @Column(name = "position_id", nullable = true)
    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "dw_resume_id", nullable = true)
    public Long getDwResumeId() {
        return dwResumeId;
    }

    public void setDwResumeId(Long dwResumeId) {
        this.dwResumeId = dwResumeId;
    }

    @Basic
    @Column(name = "authority_code", nullable = true, length = 255)
    public String getAuthorityCode() {
        return authorityCode;
    }

    public void setAuthorityCode(String authorityCode) {
        this.authorityCode = authorityCode;
    }

    @Basic
    @Column(name = "recruitment_state", nullable = true)
    public Integer getRecruitmentState() {
        return recruitmentState;
    }

    public void setRecruitmentState(Integer recruitmentState) {
        this.recruitmentState = recruitmentState;
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
        ResumeDeliverEntity that = (ResumeDeliverEntity) o;
        return id == that.id &&
                Objects.equals(positionId, that.positionId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(dwResumeId, that.dwResumeId) &&
                Objects.equals(authorityCode, that.authorityCode) &&
                Objects.equals(recruitmentState, that.recruitmentState) &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(gmtModified, that.gmtModified);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, positionId, userId, dwResumeId, authorityCode, recruitmentState, gmtCreate, gmtModified);
    }

    @Override
    public String toString() {
        return "ResumeDeliverEntity{" +
                "id=" + id +
                ", positionId=" + positionId +
                ", userId=" + userId +
                ", dwResumeId=" + dwResumeId +
                ", authorityCode='" + authorityCode + '\'' +
                ", recruitmentState=" + recruitmentState +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
