package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.DwResumeRepository;
import com.xhwl.recruitment.dao.ResumeDeliverRepository;
import com.xhwl.recruitment.dao.ResumeRepository;
import com.xhwl.recruitment.domain.ResumeEntity;
import com.xhwl.recruitment.util.FileUtil;
import com.xhwl.recruitment.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Autowired
    DwResumeRepository dwResumeRepository;

    @Autowired
    ResumeDeliverRepository resumeDeliverRepository;

    /**
     * 服务器存放文件的位置
     */
//    private final String ROOT = "/Users/chikatsurasakai/";
//    private final String ROOT = "/home/ubuntu/";
    private final String ROOT = "/root";


    /**
     * 用户上传简历附件保存的位置
     */
    private final String ResumeAccessoryFileFolderPath = ROOT + "ResumeAccessory/";

    /**
     * 用户上传辅助材料的位置
     */
    private final String SupportDetailFileFolderPath = ROOT + "SupportDetail/";

    /**
     * 用户上传照片的位置
     */
    private final String PhotoFileFolderPath = ROOT + "Photo/";

    /**
     * 用户投递时 存储简历附件的位置
     */
    private final String DwResumeAccessoryFileFolderPath = ROOT + "DwFile/" + "ResumeAccessory/";

    /**
     * 用户投递时 存储辅助材料的位置
     */
    private final String DwSupportDetailFileFolderPath = ROOT + "DwFile/" + "SupportDetail/";

    /**
     * 用户投递时 存储照片的位置
     */
    private final String DwPhotoFileFolderPath = ROOT + "DwFile/" + "Photo/";


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

        //获取文件的后缀
        String filename = file.getOriginalFilename();
        String type = filename.substring(filename.lastIndexOf('.') + 1);

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
     * 管理员下载用户上传的简历，传入投递编号
     *
     * @param deliverId
     * @return
     */
    public List<Object> getResumeByDeliver(Long deliverId) throws IOException {
        Long dwResumeId = resumeDeliverRepository.findOne(deliverId).getDwResumeId();
        String lp = dwResumeRepository.findOne(dwResumeId).getUploadResumePath();
        if (lp == null) return null;
        String path = DwResumeAccessoryFileFolderPath + lp;

        List<Object> res = new ArrayList<>();

        //获取文件后缀
        String type = lp.substring(lp.lastIndexOf('.') + 1);

        //下载文件名
        String downName = UUIDUtil.getUUID() + "." + type;

        //读取文件
        byte[] file = FileUtils.readFileToByteArray(new File(path));

        res.add(downName);
        res.add(file);
        return res;
    }

    /**
     * 管理员下载用户上传的附件，传入投递编号
     *
     * @param deliverId
     * @return
     */
    public List<Object> getSupportDetailByDeliver(Long deliverId) throws IOException {
        Long dwResumeId = resumeDeliverRepository.findOne(deliverId).getDwResumeId();
        String lp = dwResumeRepository.findOne(dwResumeId).getSupportDetailPath();
        if (lp == null) return null;
        String path = DwSupportDetailFileFolderPath + lp;

        List<Object> res = new ArrayList<>();

        //获取文件后缀
        String type = lp.substring(lp.lastIndexOf('.') + 1);

        //下载文件名
        String downName = UUIDUtil.getUUID() + "." + type;

        //读取文件
        byte[] file = FileUtils.readFileToByteArray(new File(path));

        res.add(downName);
        res.add(file);
        return res;
    }

    /**
     * 管理员下载用户上传的照片
     *
     * @param deliverId
     * @return
     * @throws IOException
     */
    public byte[] getPhotoByDeliver(Long deliverId) throws IOException {
        Long dwResumeId = resumeDeliverRepository.findOne(deliverId).getDwResumeId();
        //从数据库找到文件路径
        String lp = dwResumeRepository.findOne(dwResumeId).getPhotoPath();
        if (lp == null) return null;
        String path = DwPhotoFileFolderPath + lp;

        //读取文件
        return FileUtils.readFileToByteArray(new File(path));
    }

    /**
     * 辅助材料上传，并写入数据库
     *
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

    /**
     * 投递简历时复制 照片 到仓库文件夹,返回 userId+/+文件名
     *
     * @param userId
     * @return
     */
    private String movePhotoToDw(Long userId) {
        //从数据库找到文件路径
        String lp = resumeRepository.findByUserId(userId).getPhotoPath();
        if (lp == null) return null;
        String oldPath = PhotoFileFolderPath + lp;

        FileUtil.moveFile(oldPath, DwPhotoFileFolderPath + userId);
        log.info(DwPhotoFileFolderPath + lp);
        return lp;
    }

    /**
     * 投递简历时复制 简历附件 到仓库文件夹,返回 userId+/+文件名
     *
     * @param userId
     * @return
     */
    private String moveResumeAccessoryToDw(Long userId) {
        //从数据库找到文件路径
        String lp = resumeRepository.findByUserId(userId).getUploadResumePath();
        if (lp == null) return null;
        String oldPath = ResumeAccessoryFileFolderPath + lp;

        FileUtil.moveFile(oldPath, DwResumeAccessoryFileFolderPath + userId);
        log.info(DwResumeAccessoryFileFolderPath + lp);
        return lp;
    }

    /**
     * 投递简历时复制 辅助材料 到仓库文件夹,返回 userId+/+文件名
     *
     * @param userId
     * @return
     */
    private String moveSupportDetailToDw(Long userId) {
        //从数据库找到文件路径
        String lp = resumeRepository.findByUserId(userId).getSupportDetailPath();
        if (lp == null) return null;
        String oldPath = SupportDetailFileFolderPath + lp;

        FileUtil.moveFile(oldPath, DwSupportDetailFileFolderPath + userId);
        log.info(DwSupportDetailFileFolderPath + lp);
        return lp;
    }

    /**
     * 投递简历时复制文件
     *
     * @param userId
     * @return
     */
    public HashMap<String, String> copyDocument(Long userId) {
        HashMap<String, String> address = new HashMap<>();
        //测试的输入
        String photoPath = movePhotoToDw(userId);
        String uploadResumePath = moveResumeAccessoryToDw(userId);
        String supportDetailPath = moveSupportDetailToDw(userId);
        address.put("uploadResumePath", uploadResumePath);
        address.put("supportDetailPath", supportDetailPath);
        address.put("photoPath", photoPath);
        return address;
    }

}
