package com.xhwl.recruitment.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午12:45 2018/4/8
 **/
public class JWTToken implements AuthenticationToken {
    //密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
