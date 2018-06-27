package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.DepartmentRepository;
import com.xhwl.recruitment.dao.HistoryPositionRepository;
import com.xhwl.recruitment.dao.PositionRepository;
import com.xhwl.recruitment.domain.DepartmentEntity;
import com.xhwl.recruitment.domain.PositionEntity;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    DepartmentRepository departmentRepository;

    //获得过期前的发布项目
    public Page<HashMap> getPositionBeforeDeadline(Pageable pageable, long departmentId) {

        if (departmentId == 1L) {
            List<PositionEntity> positions = historyPositionRepository.findAllByPublishType(1);
            List<HashMap> res = new ArrayList<>();
            for (PositionEntity position : positions) {
                HashMap<String, String> hashMap = new LinkedHashMap<>();
                hashMap.put("id", new Long(position.getId()).toString());
                hashMap.put("positionName", position.getPositionName());
                hashMap.put("department", String.valueOf(position.getDepartment()));
                hashMap.put("recruitmentType", String.valueOf(position.getRecruitmentType()));
                hashMap.put("workPlace", position.getWorkPlace());
                hashMap.put("publishDate", String.valueOf(position.getPublishDate()));
                hashMap.put("deadline", String.valueOf(position.getDeadline()));
                res.add(hashMap);
            }
            Page<HashMap> resPage = new PageImpl<>(res, pageable, positions.size());
            return resPage;
        } else {
            List<PositionEntity> positions = historyPositionRepository.findAllByPublishTypeAndDepartment(1,departmentId);
            List<HashMap> res = new ArrayList<>();
            for (PositionEntity position : positions) {
                HashMap<String, String> hashMap = new LinkedHashMap<>();
                hashMap.put("id", new Long(position.getId()).toString());
                hashMap.put("positionName", position.getPositionName());
                hashMap.put("department", String.valueOf(position.getDepartment()));
                hashMap.put("recruitmentType", String.valueOf(position.getRecruitmentType()));
                hashMap.put("workPlace", position.getWorkPlace());
                hashMap.put("publishDate", String.valueOf(position.getPublishDate()));
                hashMap.put("deadline", String.valueOf(position.getDeadline()));
                res.add(hashMap);
            }
            Page<HashMap> resPage = new PageImpl<>(res, pageable, positions.size());
            return resPage;
        }
    }

    //获得过期后的发布项目
    public Page<HashMap> getPositionAfterDeadline(Pageable pageable, long departmentId) {
        if (departmentId == 1L) {
            List<PositionEntity> positions = historyPositionRepository.findAllByPublishType(2);
            List<HashMap> res = new ArrayList<>();
            for (PositionEntity position : positions) {
                HashMap<String, String> hashMap = new LinkedHashMap<>();
                hashMap.put("id", new Long(position.getId()).toString());
                hashMap.put("positionName", position.getPositionName());
                hashMap.put("department", String.valueOf(position.getDepartment()));
                hashMap.put("recruitmentType", String.valueOf(position.getRecruitmentType()));
                hashMap.put("workPlace", position.getWorkPlace());
                hashMap.put("publishDate", String.valueOf(position.getPublishDate()));
                hashMap.put("deadline", String.valueOf(position.getDeadline()));
                res.add(hashMap);
            }
            Page<HashMap> resPage = new PageImpl<>(res, pageable, positions.size());
            return resPage;
        } else {
            List<PositionEntity> positions = historyPositionRepository.findAllByPublishTypeAndDepartment(2,departmentId);
            List<HashMap> res = new ArrayList<>();
            for (PositionEntity position : positions) {
                HashMap<String, String> hashMap = new LinkedHashMap<>();
                hashMap.put("id", new Long(position.getId()).toString());
                hashMap.put("positionName", position.getPositionName());
                hashMap.put("department", String.valueOf(position.getDepartment()));
                hashMap.put("recruitmentType", String.valueOf(position.getRecruitmentType()));
                hashMap.put("workPlace", position.getWorkPlace());
                hashMap.put("publishDate", String.valueOf(position.getPublishDate()));
                hashMap.put("deadline", String.valueOf(position.getDeadline()));
                res.add(hashMap);
            }
            Page<HashMap> resPage = new PageImpl<>(res, pageable, positions.size());
            return resPage;
        }
    }

    //过期后的发布项目模糊查询
    public Page<HashMap> searchPositionAfterDeadline(Pageable pageable, Long departmentId, java.util.Date publish_date, java.util.Date end_date, Long departmentName, String positionName) {
        java.util.Date date1 = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(date1);
        Date date2 = Date.valueOf(str);
        str = sdf.format(publish_date);
        Date publishDate = Date.valueOf(str);
        str = sdf.format(end_date);
        Date endDate = Date.valueOf(str);
        if (departmentId == 1L)//人事查询

        {if(departmentName==0L)
        {List<PositionEntity>positions=historyPositionRepository.findAllByPublishTypeAndPositionNameContainingAndPublishDateAfterAndDeadlineBefore(2,  positionName, publishDate, endDate);
            List<HashMap> res = new ArrayList<>();
            for (PositionEntity position : positions) {
                HashMap<String, String> hashMap = new LinkedHashMap<>();
                hashMap.put("id", new Long(position.getId()).toString());
                hashMap.put("positionName", position.getPositionName());
                hashMap.put("department", String.valueOf(position.getDepartment()));
                hashMap.put("recruitmentType", String.valueOf(position.getRecruitmentType()));
                hashMap.put("workPlace", position.getWorkPlace());
                hashMap.put("publishDate", String.valueOf(position.getPublishDate()));
                hashMap.put("deadline", String.valueOf(position.getDeadline()));
                res.add(hashMap);
            }
            Page<HashMap> resPage = new PageImpl<>(res, pageable, positions.size());
            return resPage;}
        else
        {List<PositionEntity> positions = historyPositionRepository.findAllByPublishTypeAndDepartmentAndPositionNameContainingAndPublishDateAfterAndDeadlineBefore(2, departmentName, positionName, publishDate, endDate);
            List<HashMap> res = new ArrayList<>();
            for (PositionEntity position : positions) {
                HashMap<String, String> hashMap = new LinkedHashMap<>();
                hashMap.put("id", new Long(position.getId()).toString());
                hashMap.put("positionName", position.getPositionName());
                hashMap.put("department", String.valueOf(position.getDepartment()));
                hashMap.put("recruitmentType", String.valueOf(position.getRecruitmentType()));
                hashMap.put("workPlace", position.getWorkPlace());
                hashMap.put("publishDate", String.valueOf(position.getPublishDate()));
                hashMap.put("deadline", String.valueOf(position.getDeadline()));
                res.add(hashMap);
            }
            Page<HashMap> resPage = new PageImpl<>(res, pageable, positions.size());
            return resPage;}
        } else//非人事查询
        {
            List<PositionEntity> positions = historyPositionRepository.findAllByPublishTypeAndDepartmentAndPositionNameContainingAndPublishDateAfterAndDeadlineBefore(2, departmentId, positionName, publishDate, endDate);
            List<HashMap> res = new ArrayList<>();
            for (PositionEntity position : positions) {
                HashMap<String, String> hashMap = new LinkedHashMap<>();
                hashMap.put("id", new Long(position.getId()).toString());
                hashMap.put("positionName", position.getPositionName());
                hashMap.put("department", String.valueOf(position.getDepartment()));
                hashMap.put("recruitmentType", String.valueOf(position.getRecruitmentType()));
                hashMap.put("workPlace", position.getWorkPlace());
                hashMap.put("publishDate", String.valueOf(position.getPublishDate()));
                hashMap.put("deadline", String.valueOf(position.getDeadline()));
                res.add(hashMap);
            }
            Page<HashMap> resPage = new PageImpl<>(res, pageable, positions.size());
            return resPage;
        }
    }
}
