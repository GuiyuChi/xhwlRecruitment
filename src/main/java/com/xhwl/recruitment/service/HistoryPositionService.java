package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.HistoryPositionRepository;
import com.xhwl.recruitment.dao.PositionRepository;
import com.xhwl.recruitment.domain.PositionEntity;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: kepiao
 * @Description: 发送邮件
 * @Date: Create in 下午1:46 2018/4/13
 **/
@Service
@Slf4j
public class HistoryPositionService {
    @Autowired
    HistoryPositionRepository historyPositionRepository;
    //获得过期前的发布项目
    public List<PositionEntity> getPositionBeforeDeadline(){
        java.util.Date date1=new java.util.Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String str=sdf.format(date1);
        Date date2 = Date.valueOf(str);
        return historyPositionRepository.findBeforeDeadline(date2);
    }

    //获得过期后的发布项目
    public List<PositionEntity>getPositionAfterDeadline(){
        java.util.Date date1=new java.util.Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String str=sdf.format(date1);
        Date date2 =Date.valueOf(str);
        return historyPositionRepository.findAfterDeadline(date2);
    }
}
