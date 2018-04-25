package com.xhwl.recruitment.controller;

import com.xhwl.recruitment.bean.ResponseBean;
import com.xhwl.recruitment.domain.UserEntity;
import com.xhwl.recruitment.exception.MException;
import com.xhwl.recruitment.service.FileService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.util.JWTUtil;
import com.xhwl.recruitment.util.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午2:48 2018/4/10
 **/
@RestController
@Slf4j
public class LoginController {
    private String Captcha = null;//图片验证码的字符串
    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    /**
     * 获取验证码
     * @return
     */
    @GetMapping("/loginCaptcha")
    public ResponseEntity<byte[]> getCaptchaImage() //生成验证码
    {
        Object[] objs = VerifyUtil.createImage();

        Captcha = (String) objs[0];

        try {
            BufferedImage image = (BufferedImage) objs[1];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);//写入流中
            byte[] bytes = baos.toByteArray();

            //设置下载头
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(bytes, header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
       return null;
    }
    //objs[1]为验证码字符串，objs[2]为验证码图片

    /**
     * 用户登录的接口
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResponseBean login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        UserEntity userEntity = userService.getUser(username);
        if (userEntity.getPassword().equals(password)) {
            return new ResponseBean(200, "Login success", JWTUtil.sign(username, password));
        } else {
            throw new UnauthorizedException();
        }
    }

    /**
     * 用户登录，带验证码版
     * @param username
     * @param password
     * @param check
     * @return
     */
    @PostMapping("/loginWithCaptcha")
    public ResponseBean login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              @RequestParam("captcha") String check) {
        if(!check.equalsIgnoreCase(Captcha)) throw new MException("验证码认证失败");
        UserEntity userEntity = userService.getUser(username);
        if (userEntity.getPassword().equals(password)) {
            return new ResponseBean(200, "Login success", JWTUtil.sign(username, password));
        } else {
            throw new UnauthorizedException();
        }
    }

    /**
     * 管理员登录的接口
     * normalAdmin 普通管理员   seniorAdmin 高级管理员   superAdmin 超级管理员
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/adminLogin")
    public ResponseBean adminLogin(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        UserEntity userEntity = userService.getUser(username);
        if (userEntity.getPassword().equals(password)) {
            if (userEntity.getRole().equals("normalAdmin")) {
                return new ResponseBean(200, "normalAdmin", JWTUtil.sign(username, password));
            } else if (userEntity.getRole().equals("seniorAdmin")) {
                return new ResponseBean(200, "seniorAdmin", JWTUtil.sign(username, password));
            } else if (userEntity.getRole().equals("superAdmin")) {
                return new ResponseBean(200, "superAdmin", JWTUtil.sign(username, password));
            } else {
                throw new UnauthorizedException();
            }
        } else {
            throw new UnauthorizedException();
        }
    }

    /**
     * 登录是否过期的检验
     */
    @GetMapping("/tokenCheck")
    @RequiresAuthentication
    public void tokenCheck() {

    }

    /**
     * 登录并获取用户名的测试接口
     *
     * @param headers
     * @return
     */
    @GetMapping("/article")
    public ResponseBean article(@RequestHeader HttpHeaders headers) {

        if (headers.getFirst("authorization") != null) {
            String username = JWTUtil.getUsername(headers.getFirst("authorization"));
            log.info("username:{}", username);
        }

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseBean(200, "You are already logged in", null);
        } else {
            return new ResponseBean(200, "You are guest", null);
        }
    }

    /**
     * 权限控制的登录接口
     *
     * @return
     */
    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseBean requireAuth() {
        return new ResponseBean(200, "You are authenticated", null);
    }

    /**
     * 测试接口，获取admin
     *
     * @return
     */
    @GetMapping("/article2")
    public ResponseBean getUser(@RequestHeader HttpHeaders headers) {

        if (headers.getFirst("authorization") != null) {
            String username = JWTUtil.getUsername(headers.getFirst("authorization"));
            log.info("username:{}", username);
        }

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseBean(200, "You are already logged in", null);
        } else {
            return new ResponseBean(200, "You are guest", null);
        }
    }

    /**
     * 文件上传测试接口
     *
     * @param file
     */
    @PostMapping("upload-file")
    @Transactional(rollbackFor = Exception.class)
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        String userId = "1";
        log.info(String.format("temp team \"%s\" uploads a file \"%s\", size %d Byte", userId, file
                .getOriginalFilename(), file.getSize()));

        try {
            fileService.saveResume(file, userId);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(String.format("error occurred on attempting to save temp team \"%s\"'s uploaded file \"%s\"",
                    userId, file.getOriginalFilename()));
        }
    }

    @GetMapping("download-file/{teamId}/{fileName}/")
    public ResponseEntity<byte[]> downloadFile(@PathVariable("teamId") String teamId, @PathVariable("fileName")
            String fileName) {

        try {
            byte[] fileByteArray = fileService.getFileEntity(teamId, fileName);

            // 设置下载响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName, Charset.forName("utf-8"));

            return new ResponseEntity<>(fileByteArray, headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error(String.format("fail to download temp team %s file %s", teamId, fileName));
            e.printStackTrace();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName, Charset.forName("utf-8"));

            return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("down-png")
    public ResponseEntity<byte[]> down() {

        try {
            byte[] fileByteArray = fileService.getFileEntity("1", "a.png");
            // 设置下载响应头
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
//            headers.setContentDispositionFormData("attachment", fileName, Charset.forName("utf-8"));

            return new ResponseEntity<>(fileByteArray, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
