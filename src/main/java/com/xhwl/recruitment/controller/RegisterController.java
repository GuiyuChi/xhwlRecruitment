package com.xhwl.recruitment.controller;

import com.xhwl.recruitment.bean.ResponseBean;
import com.xhwl.recruitment.dao.UserRepository;
import com.xhwl.recruitment.domain.UserEntity;
import com.xhwl.recruitment.exception.ImperfectException;
import com.xhwl.recruitment.exception.PhoneCaptchaException;
import com.xhwl.recruitment.exception.UserNoExistException;
import com.xhwl.recruitment.exception.UserRepeatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    private String Captcha = null;//图片验证码的字符串
    private String PhoneCaptcha = "1";//手机验证码
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    private UserEntity userEntity;


    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @param phoneCaptcha
     * @return
     */
    @PostMapping("/register")
    public ResponseBean register(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("phoneCaptcha") String phoneCaptcha) {
        if (username == null || password == null | phoneCaptcha == null)
            throw new ImperfectException("信息未填写完整");

        //从redis缓存中获取验证码
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String key = username + "_PhoneCaptcha";
        String savedPhoneCaptcha = operations.get(key);

        if (!phoneCaptcha.equals(savedPhoneCaptcha)) {
            throw new PhoneCaptchaException("手机验证码输入错误");
        } else if (userRepository.findByUsername(username) != null) {
            throw new UserRepeatException("该手机已被注册");
        } else {
            userEntity = new UserEntity();
            userEntity.setUsername(username);
            userEntity.setPassword(password);
            userEntity.setRole("user");
            userRepository.save(userEntity);
            return new ResponseBean(200, "register success", null);
        }
    }

    /**
     * 用户重置密码
     *
     * @param username
     * @param password
     * @param phoneCaptcha
     * @return
     */
    @PostMapping("/resetPassword")
    public ResponseBean resetPassword(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      @RequestParam("phoneCaptcha") String phoneCaptcha) {
        if (username == null || password == null | phoneCaptcha == null)
            throw new ImperfectException("信息未填写完整");
        //从redis缓存中获取验证码
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String key = username + "_PhoneCaptcha";
        String savedPhoneCaptcha = operations.get(key);

        if (userRepository.findByUsername(username) == null) {
            throw new UserNoExistException("用户未注册");
        } else if (!phoneCaptcha.equals(savedPhoneCaptcha)) {
            throw new PhoneCaptchaException("手机验证码输入错误");
        } else {
            UserEntity userEntity = userRepository.findByUsername(username);
            userEntity.setPassword(password);
            userRepository.save(userEntity);
            return new ResponseBean(200, "resetPassword success", null);
        }
    }
}
