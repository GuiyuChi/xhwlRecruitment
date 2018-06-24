package com.xhwl.recruitment.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.sql.Date;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午10:27 2018/5/1
 **/
public class DeliverDto {
    private Long id;
    private String username;
    private String sex;
    private String highestEducation;
    private String workSeniority; //工作年限
    private Date deliverDate;
    private Integer auth;  //标注是否有修改权限，有权限为1，无则为0
    private String isSendEmail;//当前简历在当前轮询状态是否发送过简历
    private String isRead;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHighestEducation() {
        return highestEducation;
    }

    public void setHighestEducation(String highestEducation) {
        this.highestEducation = highestEducation;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public String getWorkSeniority() {
        return workSeniority;
    }

    public void setWorkSeniority(String workSeniority) {
        this.workSeniority = workSeniority;
    }

    public String getIsSendEmail() {
        return isSendEmail;
    }

    public void setIsSendEmail(String isSendEmail) {
        this.isSendEmail = isSendEmail;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "DeliverDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", highestEducation='" + highestEducation + '\'' +
                ", workSeniority='" + workSeniority + '\'' +
                ", deliverDate=" + deliverDate +
                ", auth=" + auth +
                ", isSendEmail='" + isSendEmail + '\'' +
                ", isRead='" + isRead + '\'' +
                '}';
    }
}
