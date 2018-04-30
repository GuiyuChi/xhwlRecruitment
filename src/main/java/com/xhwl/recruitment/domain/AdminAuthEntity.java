package com.xhwl.recruitment.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午9:56 2018/4/30
 **/
@Entity
@Table(name = "admin_auth", schema = "xhwl", catalog = "")
public class AdminAuthEntity {
    private long id;
    private long userId;
    private String userName;
    private long departmentId;
    private String role;
    private String permission;

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
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name", nullable = true, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "department_id", nullable = false)
    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "role", nullable = true, length = 255)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "permission", nullable = true, length = 255)
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminAuthEntity that = (AdminAuthEntity) o;
        return id == that.id &&
                userId == that.userId &&
                departmentId == that.departmentId &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(role, that.role) &&
                Objects.equals(permission, that.permission);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, userName, departmentId, role, permission);
    }

    @Override
    public String toString() {
        return "AdminAuthEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", departmentId=" + departmentId +
                ", role='" + role + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
