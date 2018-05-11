package com.xhwl.recruitment.controller.admin;

import com.xhwl.recruitment.service.DwResumeService;
import com.xhwl.recruitment.service.FileService;
import com.xhwl.recruitment.service.ResumeService;
import com.xhwl.recruitment.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author: guiyu
 * @Description: 管理员查看投递用户的简历，以及下载用户相关附件的api
 * @Date: Create in 上午9:36 2018/5/10
 **/
@RestController
public class AdminResumeController {
    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;

    @Autowired
    FileService fileService;

    @Autowired
    DwResumeService dwResumeService;

    /**
     * 管理员从投递记录获取用户的简历信息
     *
     * @param deliverId
     * @return
     */
    @GetMapping("/admin/getResume/{deliverId}")
    @RequiresRoles("admin")
    public List<Object> adminGetResumeBydeliver(@PathVariable("deliverId") Long deliverId) {
        return dwResumeService.adminGetResume(deliverId);
    }
//
//    @GetMapping("/admin/download-resume/{userId}")
//    public ResponseEntity<byte[]> downloadResume(@RequestHeader HttpHeaders headers, @PathVariable("userId") Long userId) {
////        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
//        try {
//            byte[] resumeByteArray = fileService.getResume(userId);
//
//            // 设置下载响应头
//            HttpHeaders header = new HttpHeaders();
//            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            header.setContentDispositionFormData("attachment", getUUID() + ".doc", Charset.forName("utf-8"));
//
//            return new ResponseEntity<>(resumeByteArray, header, HttpStatus.OK);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


}
