package com.xhwl.recruitment.dto;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午11:42 2018/4/30
 **/
public class AdminAuthDto {
    private long id;
    private String userName;
    private String department;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
