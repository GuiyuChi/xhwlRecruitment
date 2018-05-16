package com.xhwl.recruitment.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午3:58 2018/5/16
 **/
@Entity
@Table(name = "resume_deliver", schema = "xhwl", catalog = "")
public class ResumeDeliverEntity {
    private long id;
    private Long positionId;
    private Long userId;
    private Long dwResumeId;
    private Integer emailState;
    private String recruitmentState;
    private Date deliverDate;

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
    @Column(name = "email_state", nullable = true)
    public Integer getEmailState() {
        return emailState;
    }

    public void setEmailState(Integer emailState) {
        this.emailState = emailState;
    }

    @Basic
    @Column(name = "recruitment_state", nullable = true, length = 255)
    public String getRecruitmentState() {
        return recruitmentState;
    }

    public void setRecruitmentState(String recruitmentState) {
        this.recruitmentState = recruitmentState;
    }

    @Basic
    @Column(name = "deliver_date", nullable = true)
    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
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
                Objects.equals(emailState, that.emailState) &&
                Objects.equals(recruitmentState, that.recruitmentState) &&
                Objects.equals(deliverDate, that.deliverDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, positionId, userId, dwResumeId, emailState, recruitmentState, deliverDate);
    }
}
