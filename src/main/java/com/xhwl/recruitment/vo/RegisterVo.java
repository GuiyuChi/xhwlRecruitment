package com.xhwl.recruitment.vo;


/**
 * @Author: guiyu
 * @Description: 用户注册时传入的类
 * @Date: Create in 下午3:47 2018/4/7
 **/
public class RegisterVo {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterVo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
