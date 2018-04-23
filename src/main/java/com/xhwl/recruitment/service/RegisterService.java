package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.UserRepository;
import com.xhwl.recruitment.domain.UserEntity;
import com.xhwl.recruitment.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午3:52 2018/4/7
 **/
@Service
public class RegisterService {
    @Autowired
    UserRepository userRepository;

    //用户注册逻辑
    public UserEntity register(RegisterVo registerVo) {
        UserEntity givenUserEntity = new UserEntity();
        givenUserEntity.setUsername(registerVo.getUsername());
        givenUserEntity.setPassword(registerVo.getPassword());

        //todo 用户是否已经注册过的判断

        //在用户表中写入注册的用户
        UserEntity userEntity = userRepository.save(givenUserEntity);

        return userEntity;
    }
}
