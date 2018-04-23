package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.PositionRepository;
import com.xhwl.recruitment.domain.PositionEntity;
import com.xhwl.recruitment.vo.PositionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    public List<HashMap> getUnderwayPosition(Integer recruitmentType) {
        List<PositionEntity> positions = positionRepository.findAll();

        List<HashMap> res = new ArrayList<>();
        for (PositionEntity position : positions) {
            //简历已经发布
            if (position.getPublishType() == 1) {
                //判断简历类型
                if (position.getRecruitmentType() == recruitmentType) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", new Long(position.getId()).toString());
                    hashMap.put("positionName",position.getPositionName());
                    hashMap.put("workPlace",position.getWorkPlace());
                    hashMap.put("recruiting_numbers",position.getRecruitingNumbers().toString());
                    hashMap.put("publishDate",position.getPublishDate().toString());
                    res.add(hashMap);
                }
            }
        }
        return res;
    }


}
