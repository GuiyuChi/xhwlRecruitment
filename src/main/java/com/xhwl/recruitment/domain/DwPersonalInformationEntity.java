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
@Table(name = "dw_personal_information", schema = "xhwl", catalog = "")
public class DwPersonalInformationEntity {
    private long id;
    private long resumeId;
    private String name;
    private byte sex;
    private Byte idType;
    private String idNumber;
    private Date birthday;
    private String email;
    private String telephone;
    private Byte maritalStatus;
    private String workSeniority;
    private String politicalStatus;
    private String presentAddress;

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
    @Column(name = "name", nullable = false, length = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "sex", nullable = false)
    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "id_type", nullable = true)
    public Byte getIdType() {
        return idType;
    }

    public void setIdType(Byte idType) {
        this.idType = idType;
    }

    @Basic
    @Column(name = "id_number", nullable = true, length = 30)
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    @Column(name = "birthday", nullable = true)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "telephone", nullable = false, length = 11)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "marital_status", nullable = true)
    public Byte getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Byte maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Basic
    @Column(name = "work_seniority", nullable = true, length = 5)
    public String getWorkSeniority() {
        return workSeniority;
    }

    public void setWorkSeniority(String workSeniority) {
        this.workSeniority = workSeniority;
    }

    @Basic
    @Column(name = "political_status", nullable = true, length = 5)
    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    @Basic
    @Column(name = "present_address", nullable = true, length = 255)
    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DwPersonalInformationEntity that = (DwPersonalInformationEntity) o;
        return id == that.id &&
                resumeId == that.resumeId &&
                sex == that.sex &&
                Objects.equals(name, that.name) &&
                Objects.equals(idType, that.idType) &&
                Objects.equals(idNumber, that.idNumber) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(email, that.email) &&
                Objects.equals(telephone, that.telephone) &&
                Objects.equals(maritalStatus, that.maritalStatus) &&
                Objects.equals(workSeniority, that.workSeniority) &&
                Objects.equals(politicalStatus, that.politicalStatus) &&
                Objects.equals(presentAddress, that.presentAddress);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, resumeId, name, sex, idType, idNumber, birthday, email, telephone, maritalStatus, workSeniority, politicalStatus, presentAddress);
    }

    @Override
    public String toString() {
        return "DwPersonalInformationEntity{" +
                "id=" + id +
                ", resumeId=" + resumeId +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", idType=" + idType +
                ", idNumber='" + idNumber + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", maritalStatus=" + maritalStatus +
                ", workSeniority='" + workSeniority + '\'' +
                ", politicalStatus='" + politicalStatus + '\'' +
                ", presentAddress='" + presentAddress + '\'' +
                '}';
    }
}
