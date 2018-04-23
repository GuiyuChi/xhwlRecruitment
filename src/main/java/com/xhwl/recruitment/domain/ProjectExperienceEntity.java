package com.xhwl.recruitment.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午1:50 2018/3/22
 **/
@Entity
@Table(name = "project_experience", schema = "xhwl", catalog = "")
public class ProjectExperienceEntity {
    private long id;
    private Long resumeId;
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
    @Column(name = "resume_id", nullable = true)
    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
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
        ProjectExperienceEntity that = (ProjectExperienceEntity) o;
        return id == that.id &&
                Objects.equals(resumeId, that.resumeId) &&
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
        return "ProjectExperienceEntity{" +
                "id=" + id +
                ", resumeId=" + resumeId +
                ", projectName='" + projectName + '\'' +
                ", projectRole='" + projectRole + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                '}';
    }
}
