package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.ResumeRepository;
import com.xhwl.recruitment.domain.ResumeEntity;
import com.xhwl.recruitment.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 上午11:00 2018/4/18
 **/
@Service
@Slf4j
public class FileService {

    @Autowired
    ResumeRepository resumeRepository;

    /**
     * 服务器存放文件的位置
     */
//    private final String ROOT = "/Users/chikatsurasakai/";
    private final String ROOT = "/home/ubuntu/";


    /**
     * 用户上传简历附件保存的位置
     */
    private final String ResumeAccessoryFileFolderPath = ROOT + "ResumeAccessory/";

    /**
     * 用户上传辅助材料的
     */
    private final String SupportDetailFileFolderPath = ROOT + "SupportDetail/";

    /**
     * 用户上传照片的位置
     */
    private final String PhotoFileFolderPath = ROOT + "Photo/";


    /**
     * 将照片保存在 照片路径 / 用户id / 上传文件名 位置 并写入数据库
     *
     * @param file
     * @param userId
     * @return
     * @throws IOException
     */
    public String savePhoto(MultipartFile file, String userId) throws IOException {
        //写入文件位置
        String name = FileUtil.saveMultipartFileToLocal(file, PhotoFileFolderPath + userId);
        String path = PhotoFileFolderPath + userId + "/" + name;
        log.info("photo save path:{}", path);

        //将照片文件夹下的具体位置写入数据表 resume表
        ResumeEntity resumeEntity = resumeRepository.findByUserId(new Long(userId));
        resumeEntity.setPhotoPath(userId + "/" + name);
        resumeRepository.saveAndFlush(resumeEntity);
        return path;
    }

    /**
     * 拼接照片位置和数据库中保存的位置读取到
     *
     * @param userId
     * @return
     * @throws IOException
     */
    public byte[] getPhoto(Long userId) throws IOException {
        //从数据库找到文件路径
        String lp = resumeRepository.findByUserId(userId).getPhotoPath();
        if (lp == null) return null;
        String path = PhotoFileFolderPath + lp;

        //读取文件
        return FileUtils.readFileToByteArray(new File(path));
    }

    /**
     * 将一个简历文件保存到用户的文件夹中并写入数据库
     *
     * @param file
     * @param userId
     * @throws IOException
     */
    public String saveResume(MultipartFile file, String userId) throws IOException {
        //写入文件
        String name = FileUtil.saveMultipartFileToLocal(file, ResumeAccessoryFileFolderPath + userId);
        String path = ResumeAccessoryFileFolderPath + userId + "/" + name;
        log.info("resume save path:{}", path);

        //将简历文件夹下的具体位置写入数据表 resume表
        ResumeEntity resumeEntity = resumeRepository.findByUserId(new Long(userId));
        resumeEntity.setUploadResumePath(userId + "/" + name);
        resumeRepository.saveAndFlush(resumeEntity);
        return path;
    }

    /**
     * 读取用户上传的简历
     *
     * @param userId
     * @return
     * @throws IOException
     */
    public byte[] getResume(Long userId) throws IOException {
        //从数据库找到文件路径
        String lp = resumeRepository.findByUserId(userId).getUploadResumePath();
        if (lp == null) return null;
        String path = ResumeAccessoryFileFolderPath + lp;

        //读取文件
        return FileUtils.readFileToByteArray(new File(path));

    }

    /**
     * 辅助材料上传，并写入数据库
     * @param file
     * @param userId
     * @return
     * @throws IOException
     */
    public String saveSupportDetail(MultipartFile file, String userId) throws IOException {
        //写入文件
        String name = FileUtil.saveMultipartFileToLocal(file, SupportDetailFileFolderPath + userId);
        String path = SupportDetailFileFolderPath + userId + "/" + name;
        log.info("SupportDetail save path:{}", path);

        //将简历文件夹下的具体位置写入数据表 resume表
        ResumeEntity resumeEntity = resumeRepository.findByUserId(new Long(userId));
        resumeEntity.setSupportDetailPath(userId + "/" + name);
        resumeRepository.saveAndFlush(resumeEntity);
        return path;
    }

    /**
     * 读取一个文件为字节流
     *
     * @param userId
     * @param fileName
     * @return 文件的byte数组
     * @throws IOException
     */
    public byte[] getFileEntity(String userId, String fileName) throws IOException {
        return FileUtils.readFileToByteArray(new File(String.format("%s/%s/%s", ResumeAccessoryFileFolderPath, userId, fileName)));
    }

}
