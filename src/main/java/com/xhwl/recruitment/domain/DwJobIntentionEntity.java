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
@Table(name = "dw_job_intention", schema = "xhwl", catalog = "")
public class DwJobIntentionEntity {
    private long id;
    private long resumeId;
    private String workPlace;
    private Integer salary;
    private Date expectedTimeForDuty;

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
    @Column(name = "work_place", nullable = false, length = 255)
    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    @Basic
    @Column(name = "salary", nullable = true)
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "expected_time_for_duty", nullable = false)
    public Date getExpectedTimeForDuty() {
        return expectedTimeForDuty;
    }

    public void setExpectedTimeForDuty(Date expectedTimeForDuty) {
        this.expectedTimeForDuty = expectedTimeForDuty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DwJobIntentionEntity that = (DwJobIntentionEntity) o;
        return id == that.id &&
                resumeId == that.resumeId &&
                Objects.equals(workPlace, that.workPlace) &&
                Objects.equals(salary, that.salary) &&
                Objects.equals(expectedTimeForDuty, that.expectedTimeForDuty);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, resumeId, workPlace, salary, expectedTimeForDuty);
    }

    @Override
    public String toString() {
        return "DwJobIntentionEntity{" +
                "id=" + id +
                ", resumeId=" + resumeId +
                ", workPlace='" + workPlace + '\'' +
                ", salary=" + salary +
                ", expectedTimeForDuty=" + expectedTimeForDuty +
                '}';
    }
}
