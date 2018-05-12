package com.xhwl.recruitment.controller;

import com.xhwl.recruitment.service.FileService;
import com.xhwl.recruitment.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午6:50 2018/4/18
 **/
@RestController
@Slf4j
public class FileController {
    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    /**
     * 上传照片
     *
     * @param photo
     * @param headers
     */
    @PostMapping("upload-photo")
    @RequiresAuthentication
    public void uploadPhoto(@RequestParam("file") MultipartFile photo, @RequestHeader HttpHeaders headers) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
//        Long userId = 4L;
        String strUserId = userId.toString();

        log.info(String.format("temp team \"%s\" uploads a file \"%s\", size %d Byte", userId, photo
                .getOriginalFilename(), photo.getSize()));

        try {
            fileService.savePhoto(photo, strUserId);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(String.format("error occurred on attempting to save temp team \"%s\"'s uploaded file \"%s\"",
                    userId, photo.getOriginalFilename()));
        }
    }

    /**
     * 下载照片,用户自用
     *
     * @return
     */
    @GetMapping("download-photo")
    @RequiresAuthentication
    public ResponseEntity<byte[]> downloadPhoto(@RequestHeader HttpHeaders headers) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        try {
            byte[] photoByteArray = fileService.getPhoto(userId);

            //设置下载头
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(photoByteArray, header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传简历附件
     *
     * @param resume
     * @param headers
     */
    @PostMapping("upload-resume")
    @RequiresAuthentication
    public void uploadResume(@RequestParam("file") MultipartFile resume, @RequestHeader HttpHeaders headers) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        String strUserId = userId.toString();

        try {
            fileService.saveResume(resume, strUserId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载某用户的简历附件，传入 简历投递表的 id
     *
     * @param headers
     * @return
     */
    @GetMapping("/download-resume/{userId}")
    public ResponseEntity<byte[]> downloadResume(@RequestHeader HttpHeaders headers, @PathVariable("userId") Long userId) {
//        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        try {
            byte[] resumeByteArray = fileService.getResume(userId);

            // 设置下载响应头
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            header.setContentDispositionFormData("attachment", getUUID() + ".doc", Charset.forName("utf-8"));

            return new ResponseEntity<>(resumeByteArray, header, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 管理员下载用户投递的简历附件，根据投递记录的编号
     *
     * @param deliverId
     * @return
     */
    @GetMapping("admin/downloadResume/{deliverId}")
    @RequiresRoles("admin")
    public ResponseEntity<byte[]> downloadResumeByDeliver(@PathVariable("deliverId") Long deliverId) {
        try {
            List<Object> findList = fileService.getResumeByDeliver(deliverId);
            //文件不存在，传出空值
            if (findList == null) return null;

            byte[] resumeByteArray = (byte[]) findList.get(1);

            // 设置下载响应头
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            header.setContentDispositionFormData("attachment", String.valueOf(findList.get(0)), Charset.forName("utf-8"));

            return new ResponseEntity<>(resumeByteArray, header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 管理员下载用户投递的附件，根据投递记录的编号
     * @param deliverId
     * @return
     */
    @GetMapping("/admin/downloadSupportDetail/{deliverId}")
    @RequiresRoles("admin")
    public ResponseEntity<byte[]> downloadSupportDetailByDeliver(@PathVariable("deliverId") Long deliverId) {
        try {
            List<Object> findList = fileService.getSupportDetailByDeliver(deliverId);
            //文件不存在，传出空值
            if (findList == null) return null;

            byte[] resumeByteArray = (byte[]) findList.get(1);

            // 设置下载响应头
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            header.setContentDispositionFormData("attachment", String.valueOf(findList.get(0)), Charset.forName("utf-8"));

            return new ResponseEntity<>(resumeByteArray, header, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 上传其他辅助材料
     *
     * @param supportDetail
     * @param headers
     */
    @PostMapping("upload-support-detail")
    public void uploadSupportDetail(@RequestParam("file") MultipartFile supportDetail, @RequestHeader HttpHeaders headers) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));

        String strUserId = userId.toString();

        try {
            fileService.saveSupportDetail(supportDetail, strUserId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 上传文件测试接口
     *
     * @param photo
     * @param headers
     */
    @PostMapping("upload-photo-test")
    public void uploadPhotoTest(@RequestParam("file") MultipartFile photo, @RequestHeader HttpHeaders headers) {
//        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        Long userId = 2L;
        String strUserId = userId.toString();

        log.info(String.format("temp team \"%s\" uploads a file \"%s\", size %d Byte", userId, photo
                .getOriginalFilename(), photo.getSize()));

        try {
            fileService.savePhoto(photo, strUserId);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(String.format("error occurred on attempting to save temp team \"%s\"'s uploaded file \"%s\"",
                    userId, photo.getOriginalFilename()));
        }
    }

    /**
     * 生成随机的UUID，用来给文件命名
     *
     * @return
     */
    private static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return UUID.randomUUID().toString();
    }

}
