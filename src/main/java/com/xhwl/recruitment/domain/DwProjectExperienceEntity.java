package com.xhwl.recruitment.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午4:24 2018/4/22
 **/
@Entity
@Table(name = "dw_project_experience", schema = "xhwl", catalog = "")
public class DwProjectExperienceEntity {
    private long id;
    private long resumeId;
    private String projectName;
    private String projectRole;
    private String projectDescription;

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
    @Column(name = "project_name", nullable = true, length = 50)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Basic
    @Column(name = "project_role", nullable = true, length = 50)
    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }

    @Basic
    @Column(name = "project_description", nullable = true, length = -1)
    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DwProjectExperienceEntity that = (DwProjectExperienceEntity) o;
        return id == that.id &&
                resumeId == that.resumeId &&
                Objects.equals(projectName, that.projectName) &&
                Objects.equals(projectRole, that.projectRole) &&
                Objects.equals(projectDescription, that.projectDescription);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, resumeId, projectName, projectRole, projectDescription);
    }

    @Override
    public String toString() {
        return "DwProjectExperienceEntity{" +
                "id=" + id +
                ", resumeId=" + resumeId +
                ", projectName='" + projectName + '\'' +
                ", projectRole='" + projectRole + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                '}';
    }
}
