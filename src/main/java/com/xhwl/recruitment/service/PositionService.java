package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.PositionRepository;
import com.xhwl.recruitment.domain.PositionEntity;
import com.xhwl.recruitment.vo.PositionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: guiyu
 * @Description: 处理职位的增删改查
 * @Date: Create in 上午10:49 2018/4/23
 **/
@Service
public class PositionService {
    @Autowired
    PositionRepository positionRepository;

//    public PositionEntity addPosition(PositionVo positionVo){
//        PositionEntity positionEntity = new PositionEntity();
//        positionEntity.setPositionName(positionVo.getPositionName());
//        positionEntity.setResumeAuditPosition();
//    }

    /**
     * 根据类型查询不同的校招岗位 1.校招 2.社招 3.实习
     *
     * @param recruitmentType
     * @return
     */
    public List<HashMap> getUnderwayPositions(Integer recruitmentType) {
        List<PositionEntity> positions = positionRepository.findAll();

        List<HashMap> res = new ArrayList<>();
        for (PositionEntity position : positions) {
            //简历已经发布
            if (position.getPublishType() == 1) {
                //判断简历类型
                if (position.getRecruitmentType() == recruitmentType) {
                    HashMap<String, String> hashMap = new LinkedHashMap<>();
                    hashMap.put("id", new Long(position.getId()).toString());
                    hashMap.put("positionName", position.getPositionName());
                    hashMap.put("workPlace", position.getWorkPlace());
                    hashMap.put("recruitingNumbers", position.getRecruitingNumbers().toString());
                    hashMap.put("publishDate", position.getPublishDate().toString());
                    hashMap.put("department", position.getDepartment());
                    hashMap.put("jobResponsibilities", position.getJobResponsibilities());
                    hashMap.put("jobRequirements", position.getJobRequirements());
                    res.add(hashMap);
                }
            }
        }
        return res;
    }

    /**
     * 按照岗位id号码发出岗位详细信息，用户访问
     *
     * @param positionId
     * @return
     */
    public HashMap<String, String> getPosition(Long positionId) {
        PositionEntity position = positionRepository.findOne(positionId);
        if (position == null) return null;
        if (position.getPublishType() == 1) {
            //简历已经处于被发布状态
            HashMap<String, String> hashMap = new LinkedHashMap<>();
            hashMap.put("id", new Long(position.getId()).toString());
            hashMap.put("positionName", position.getPositionName());
            hashMap.put("positionType", position.getPositionType());
            hashMap.put("department", position.getDepartment());
            hashMap.put("recruitingNumbers", position.getRecruitingNumbers().toString());
            hashMap.put("workPlace", position.getWorkPlace());
            hashMap.put("education", position.getEducation());
            hashMap.put("publishDate", position.getPublishDate().toString());
            hashMap.put("jobResponsibilities", position.getJobResponsibilities());
            hashMap.put("jobRequirements", position.getJobRequirements());
            return hashMap;
        }
        return null;
    }


}
