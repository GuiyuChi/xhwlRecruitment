package com.xhwl.recruitment.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午1:50 2018/3/22
 **/
@Entity
@Table(name = "award", schema = "xhwl", catalog = "")
public class AwardEntity {
    private long id;
    private Long resumeId;
    private String awardName;
    private Date dateOfAward;

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
    @Column(name = "resume_id", nullable = true)
    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    @Basic
    @Column(name = "award_name", nullable = true, length = 50)
    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    @Basic
    @Column(name = "date_of_award", nullable = true)
    public Date getDateOfAward() {
        return dateOfAward;
    }

    public void setDateOfAward(Date dateOfAward) {
        this.dateOfAward = dateOfAward;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AwardEntity that = (AwardEntity) o;
        return id == that.id &&
                Objects.equals(resumeId, that.resumeId) &&
                Objects.equals(awardName, that.awardName) &&
                Objects.equals(dateOfAward, that.dateOfAward);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, resumeId, awardName, dateOfAward);
    }
}
