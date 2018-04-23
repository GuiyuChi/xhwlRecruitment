package com.xhwl.recruitment.vo;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午3:03 2018/4/9
 **/
public class ProjectExperienceVo {
    private Long id;
    private String projectName;
    private String projectRole;
    private String projectDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    @Override
    public String toString() {
        return "ProjectExperienceVo{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", projectRole='" + projectRole + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                '}';
    }
}
