package com.xhwl.recruitment.controller.user;

import com.xhwl.recruitment.dao.*;
import com.xhwl.recruitment.domain.ResumeEntity;
import com.xhwl.recruitment.exception.*;
import com.xhwl.recruitment.service.DeliverService;
import com.xhwl.recruitment.service.PositionService;
import com.xhwl.recruitment.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: guiyu
 * @Description: 用户投递简历和查看投递的api
 * @Date: Create in 下午3:33 2018/5/8
 **/
@RestController
public class DeliverController {
    @Autowired
    UserService userService;

    @Autowired
    PositionService positionService;

    @Autowired
    DeliverService deliverService;

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    PersonalInformationRepository personalInformationRepository;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    JobIntentionRepository jobIntentionRepository;

    @Autowired
    PositionRepository positionRepository;

    /**
     * 用户投递岗位
     *
     * @param headers
     * @param positionId
     * @return
     */
    @PutMapping("/deliver/{positionId}")
    @RequiresAuthentication
    public Long deliver(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {

        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));

        ResumeEntity resumeEntity = resumeRepository.findByUserId(userId);

        if (resumeEntity == null) {
            //未创建简历
            throw new ResumeNoExistException("未创建简历");
        } else {
            if (positionRepository.findOne(positionId)==null){
                //岗位不存在
                throw new PositionNoExistException("岗位不存在");
            }
            if (!deliverService.checkResumeType(userId, positionId)) {
                throw new ResumeTypeException("简历类型不符");
            }
            if (personalInformationRepository.findByResumeId(resumeEntity.getId()) == null) {
                //未填写个人信息表
                throw new PersonalInformationNoExistException("未填写个人信息");
            }
            if (educationRepository.findAllByResumeId(resumeEntity.getId()).size()==0) {
                //未填写个人教育经历
                throw new EducationNoExistException("未填写教育经历");
            }
            if (jobIntentionRepository.findByResumeId(resumeEntity.getId()) == null) {
                //未填写就业意向
                throw new JobIntentionNoExistException("未填写就业意向");
            }
            if (resumeEntity.getUploadResumePath() == null) {
                //未上传简历附件
                throw new UploadResumeNoExistException("未上传简历附件");
            }
            if(resumeEntity.getPhotoPath()==null){
                //未上传照片
                throw new PhotoNoExistException("未上传照片");
            }
            //判断重复投递
            if (deliverService.isFirst(userId, positionId)) {
                Long id = deliverService.deliver(positionId, userId);
                return id;
            } else {
                throw new DeliverRepeatException("重复投递");
            }
        }

    }

    /**
     * 用户获取自己的投递信息
     *
     * @param headers
     * @return
     */
    @GetMapping("/deliver")
    @RequiresAuthentication
    public List<HashMap> findResumeDelivers(@RequestHeader HttpHeaders headers) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));

        List<HashMap> res = deliverService.findResumeDelivers(userId);

        return res;

    }
}
