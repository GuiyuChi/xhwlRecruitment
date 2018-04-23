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
@Table(name = "dw_award", schema = "xhwl", catalog = "")
public class DwAwardEntity {
    private long id;
    private long resumeId;
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
    @Column(name = "resume_id", nullable = false)
    public long getResumeId() {
        return resumeId;
    }

    public void setResumeId(long resumeId) {
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
        DwAwardEntity that = (DwAwardEntity) o;
        return id == that.id &&
                resumeId == that.resumeId &&
                Objects.equals(awardName, that.awardName) &&
                Objects.equals(dateOfAward, that.dateOfAward);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, resumeId, awardName, dateOfAward);
    }
}
