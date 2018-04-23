package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.UserRepository;
import com.xhwl.recruitment.domain.UserEntity;
import com.xhwl.recruitment.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午2:45 2018/4/10
 **/
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public UserEntity getUser(String username) {

        return userRepository.findByUsername(username);
    }

    /**
     * 根据用户名获取用户id
     *
     * @param username
     * @return
     */
    public Long getUserId(String username) {
        if (username == null) return null;
        return getUser(username).getId();
    }

    /**
     * 通过token找到用户id,必须在登录后调用
     *
     * @param token
     * @return
     */
    public Long getUserIdByToken(String token) {
        String username = JWTUtil.getUsername(token);
        return getUserId(username);
    }
}
