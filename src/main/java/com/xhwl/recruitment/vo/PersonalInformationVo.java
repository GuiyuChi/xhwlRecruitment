package com.xhwl.recruitment.vo;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午10:10 2018/4/7
 **/
public class PersonalInformationVo {
    private Long id;
    private String name;
    private Integer sex;
    private Integer idType;
    private String idNumber;
    private String birthday;
    private String email;
    private String telephone;
    private Integer maritalStatus;
    private String workSeniority;
    private String politicalStatus;
    private String presentAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getWorkSeniority() {
        return workSeniority;
    }

    public void setWorkSeniority(String workSeniority) {
        this.workSeniority = workSeniority;
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    @Override
    public String toString() {
        return "PersonalInformationVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", idType=" + idType +
                ", idNumber='" + idNumber + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", maritalStatus=" + maritalStatus +
                ", workSeniority='" + workSeniority + '\'' +
                ", politicalStatus='" + politicalStatus + '\'' +
                ", presentAddress='" + presentAddress + '\'' +
                '}';
    }
}
