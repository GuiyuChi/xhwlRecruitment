package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.PositionRepository;
import com.xhwl.recruitment.dao.ResumeDeliverRepository;
import com.xhwl.recruitment.domain.AdminAuthEntity;
import com.xhwl.recruitment.domain.PositionEntity;
import com.xhwl.recruitment.domain.ResumeDeliverEntity;
import com.xhwl.recruitment.util.StatusCodeUtil;
import com.xhwl.recruitment.vo.PositionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
@Slf4j
public class PositionService {
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    ResumeDeliverRepository resumeDeliverRepository;

    /**
     * 管理员添加或修改职位，添加时传入id为null
     *
     * @param positionVo
     * @return
     */
    public PositionEntity addPosition(PositionVo positionVo) {
        PositionEntity positionEntity = new PositionEntity();
        if (positionVo.getId() == null) {
            positionVo.setId(0L);
        }
        BeanUtils.copyProperties(positionVo, positionEntity);

        //写入截止日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String deadline = positionVo.getDeadline();
        try {
            positionEntity.setDeadline(new Date(dateFormat.parse(deadline).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //写入发布状态,直接发布
        positionEntity.setPublishType(1);

        //创建时，写入发布时间，就是当前时间
        if (positionVo.getId() == 0) {
            Date currentDate = new java.sql.Date(System.currentTimeMillis());
            positionEntity.setPublishDate(currentDate);
        } else {
            positionEntity.setPublishDate(positionRepository.findOne(positionVo.getId()).getPublishDate());
        }

        //写入创建时间和修改时间
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        //第一次创建修改GmtCreate
        if (positionVo.getId() == 0) {
            positionEntity.setGmtCreate(timestamp);
        } else {
            positionEntity.setGmtCreate(positionRepository.findOne(positionVo.getId()).getGmtCreate());
        }

        positionEntity.setGmtModified(timestamp);

//        log.info("the result of addPosition:{}", positionEntity);
        return positionRepository.save(positionEntity);
    }

    /**
     * 管理员按position获取岗位详情，较用户获取更多的信息
     *
     * @param positionId
     * @return
     */
    public HashMap<String, String> adminGetPosition(Long positionId) {
        PositionEntity position = positionRepository.findOne(positionId);
        if (position == null) return null;

        HashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("id", new Long(position.getId()).toString());
        hashMap.put("positionName", position.getPositionName());
        hashMap.put("department", String.valueOf(position.getDepartment()));
        hashMap.put("resumeAuditDepartment", String.valueOf(position.getResumeAuditDepartment()));
        hashMap.put("assessmentDepartment", String.valueOf(position.getAssessmentDepartment()));
        hashMap.put("positionType", position.getPositionType());
        hashMap.put("recruitmentType", String.valueOf(position.getRecruitmentType()));
        hashMap.put("workSeniority", position.getWorkSeniority());
        hashMap.put("workPlace", position.getWorkPlace());
        hashMap.put("salary",position.getSalary());
        hashMap.put("education", position.getEducation());
        hashMap.put("recruitingNumbers", position.getRecruitingNumbers().toString());
        hashMap.put("deadline", String.valueOf(position.getDeadline()));
        hashMap.put("jobResponsibilities", position.getJobResponsibilities());
        hashMap.put("jobRequirements", position.getJobRequirements());
        //获取岗位投递数量
        List<ResumeDeliverEntity> resumeDeliverEntities = resumeDeliverRepository.findAllByPositionId(position.getId());
        hashMap.put("number", String.valueOf(resumeDeliverEntities.size()));
        return hashMap;
    }

    /**
     * 普通管理员查看发布中的工作，限本部门
     *
     * @return
     */
    public Page<HashMap> adminGetDepartmentPositions(Pageable pageable, Long department, Integer publicType,
                                                     String positionName, String early_date, String last_date) {
        // 时间处理
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date early = null;
        Date last = null;
        //默认为 1000年开始 9999年结束
        try {
            early = new Date(dateFormat.parse("1000-01-01").getTime());
            last = new Date(dateFormat.parse("9999-12-31").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (early_date != null && !"".equals(early_date)) {
            try {
                early = new Date(dateFormat.parse(early_date).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (last_date != null && !"".equals(last_date)) {
            try {
                last = new Date(dateFormat.parse(last_date).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Page<PositionEntity> positionEntityPage = positionRepository.
                findAllByPositionNameContainingAndDepartmentAndPublishTypeAndPublishDateBetween(positionName, department, publicType, early, last, pageable);

        List<PositionEntity> publicPositions = positionEntityPage.getContent();
        List<HashMap> res = new ArrayList<>();
        for (PositionEntity position : publicPositions) {
            HashMap<String, String> hashMap = new LinkedHashMap<>();
            hashMap.put("id", new Long(position.getId()).toString());
            hashMap.put("positionName", position.getPositionName());
            hashMap.put("department", String.valueOf(position.getDepartment()));
            hashMap.put("recruitmentType", String.valueOf(position.getRecruitmentType()));
            hashMap.put("workSeniority", position.getWorkSeniority());
            hashMap.put("workPlace", position.getWorkPlace());
            hashMap.put("salary",position.getSalary());
            hashMap.put("publishDate", String.valueOf(position.getPublishDate()));
            hashMap.put("deadline", String.valueOf(position.getDeadline()));
            //获取岗位投递数量
            List<ResumeDeliverEntity> resumeDeliverEntities = resumeDeliverRepository.findAllByPositionId(position.getId());
            hashMap.put("number", String.valueOf(resumeDeliverEntities.size()));
            res.add(hashMap);
        }

        Page<HashMap> resPage = new PageImpl<>(res, pageable, positionEntityPage.getTotalElements());
        return resPage;
    }


    /**
     * 人事部门查看所有已经发布的工作
     *
     * @param pageable
     * @param publicType
     * @return
     */
    public Page<HashMap> adminGetAllPublishPositions(Pageable pageable, Integer publicType, Long deaprtment,
                                                     String positionName, String early_date, String last_date) {
        // 时间处理
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date early = null;
        Date last = null;
        //默认为 1000年开始 9999年结束
        try {
            early = new Date(dateFormat.parse("1000-01-01").getTime());
            last = new Date(dateFormat.parse("9999-12-31").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (early_date != null && !"".equals(early_date)) {
            try {
                early = new Date(dateFormat.parse(early_date).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (last_date != null && !"".equals(last_date)) {
            try {
                last = new Date(dateFormat.parse(last_date).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Page<PositionEntity> positionEntityPage = null;
        if (deaprtment != 0) {
            positionEntityPage = positionRepository.findAllByPositionNameContainingAndDepartmentAndPublishTypeAndPublishDateBetween(positionName, deaprtment, publicType, early, last, pageable);
        } else {
            positionEntityPage = positionRepository.findAllByPositionNameContainingAndPublishTypeAndPublishDateBetween(positionName, publicType, early, last, pageable);
        }

        List<PositionEntity> publicPositions = positionEntityPage.getContent();
        List<HashMap> res = new ArrayList<>();
        for (PositionEntity position : publicPositions) {
            HashMap<String, String> hashMap = new LinkedHashMap<>();
            hashMap.put("id", new Long(position.getId()).toString());
            hashMap.put("positionName", position.getPositionName());
            hashMap.put("department", String.valueOf(position.getDepartment()));
            hashMap.put("recruitmentType", String.valueOf(position.getRecruitmentType()));
            hashMap.put("workSeniority", position.getWorkSeniority());
            hashMap.put("workPlace", position.getWorkPlace());
            hashMap.put("salary",position.getSalary());
            hashMap.put("publishDate", String.valueOf(position.getPublishDate()));
            hashMap.put("deadline", String.valueOf(position.getDeadline()));
            //获取岗位投递数量
            List<ResumeDeliverEntity> resumeDeliverEntities = resumeDeliverRepository.findAllByPositionId(position.getId());
            hashMap.put("number", String.valueOf(resumeDeliverEntities.size()));
            res.add(hashMap);
        }

        Page<HashMap> resPage = new PageImpl<>(res, pageable, positionEntityPage.getTotalElements());
        return resPage;
    }

    /**
     * 管理员关闭岗位，岗位的 publicType 被设置为2
     *
     * @param positionId
     */
    public void adminClosePosition(Long positionId) {
        PositionEntity positionEntity = positionRepository.findOne(positionId);

        //将岗位设置成关闭状态
        positionEntity.setPublishType(2);

        //将截止日期设置为当前日期
        positionEntity.setDeadline(new Date(System.currentTimeMillis()));

        positionRepository.saveAndFlush(positionEntity);

        //拒绝岗位下的所有简历
        refuseAllDeliverByPositionId(positionId);

    }


    /**
     * 根据类型查询不同的校招岗位 1.校招 2.社招 3.实习,用户访问
     *
     * @param recruitmentType
     * @return
     */
    public List<HashMap> getUnderwayPositions(Integer recruitmentType) {
        List<PositionEntity> positions = positionRepository.findAll();

        List<HashMap> res = new ArrayList<>();
        for (PositionEntity position : positions) {
            //简历已经发布
            if (isUnderwayPosition(position)) {
                //判断简历类型
                if (position.getRecruitmentType() == recruitmentType) {
                    HashMap<String, String> hashMap = new LinkedHashMap<>();
                    hashMap.put("id", new Long(position.getId()).toString());
                    hashMap.put("positionName", position.getPositionName());
                    hashMap.put("positionType", position.getPositionType());
                    hashMap.put("workSeniority", position.getWorkSeniority());
                    hashMap.put("workPlace", position.getWorkPlace());
                    hashMap.put("salary",position.getSalary());
                    hashMap.put("recruitingNumbers", position.getRecruitingNumbers().toString());
                    hashMap.put("publishDate", position.getPublishDate().toString());
                    hashMap.put("department", String.valueOf(position.getDepartment()));
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
        if (isUnderwayPosition(position)) {
            //简历已经处于被发布状态
            HashMap<String, String> hashMap = new LinkedHashMap<>();
            hashMap.put("id", new Long(position.getId()).toString());
            hashMap.put("positionName", position.getPositionName());
            hashMap.put("positionType", position.getPositionType());
            hashMap.put("department", String.valueOf(position.getDepartment()));
            hashMap.put("recruitingNumbers", position.getRecruitingNumbers().toString());
            hashMap.put("workSeniority", position.getWorkSeniority());
            hashMap.put("workPlace", position.getWorkPlace());
            hashMap.put("salary",position.getSalary());
            hashMap.put("education", position.getEducation());
            hashMap.put("publishDate", position.getPublishDate().toString());
            hashMap.put("jobResponsibilities", position.getJobResponsibilities());
            hashMap.put("jobRequirements", position.getJobRequirements());
            return hashMap;
        }
        return null;
    }

    /**
     * 用户对岗位进行模糊查询
     * 根据类型查询不同的校招岗位 1.校招 2.社招 3.实习,用户访问
     *
     * @param partOfName
     * @return
     */
    public Page<HashMap> getLikePositions(Pageable pageable, String workPlace, String partOfName, String positionType, Integer recruitmentType) {

        Page<PositionEntity> positionEntityPage = positionRepository.
                findAllByPositionNameContainingAndRecruitmentTypeAndPublishTypeAndWorkPlaceContainingAndPositionTypeContaining(partOfName, recruitmentType, 1, workPlace, positionType, pageable);

        List<PositionEntity> positions = positionEntityPage.getContent();

        List<HashMap> res = new ArrayList<>();
        for (PositionEntity position : positions) {
            //简历已经发布
            if (isUnderwayPosition(position)) {
                //判断简历类型
                if (position.getRecruitmentType() == recruitmentType) {
                    HashMap<String, String> hashMap = new LinkedHashMap<>();
                    hashMap.put("id", new Long(position.getId()).toString());
                    hashMap.put("positionName", position.getPositionName());
                    hashMap.put("positionType", position.getPositionType());
                    hashMap.put("workSeniority", position.getWorkSeniority());
                    hashMap.put("workPlace", position.getWorkPlace());
                    hashMap.put("salary", position.getSalary());
                    hashMap.put("education", position.getEducation());
                    hashMap.put("recruitingNumbers", position.getRecruitingNumbers().toString());
                    hashMap.put("publishDate", position.getPublishDate().toString());
                    hashMap.put("department", String.valueOf(position.getDepartment()));
                    hashMap.put("jobResponsibilities", position.getJobResponsibilities());
                    hashMap.put("jobRequirements", position.getJobRequirements());
                    res.add(hashMap);
                }
            }
        }
        Page<HashMap> resPage = new PageImpl<>(res, pageable, positionEntityPage.getTotalElements());
        return resPage;
    }

    /**
     * 判断一个岗位是否在发布中
     * 首先判断发布状态是否为 1
     * 若为 1 ，进一步比较deadline与当前时间，若已经过期，修改数据库
     *
     * @param position
     * @return
     */
    private Boolean isUnderwayPosition(PositionEntity position) {
        if (position.getPublishType() == 1) {
            Date deadLine = position.getDeadline();
            Date nowDate = new java.sql.Date(System.currentTimeMillis());
            if (nowDate.before(deadLine)) {
                return true;
            } else {
                PositionEntity newPosition = positionRepository.findOne(position.getId());
                newPosition.setPublishType(2);
                positionRepository.save(newPosition);

                // 判断其过期时，拒绝改岗位下的所有投递
                refuseAllDeliverByPositionId(position.getId());
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 将某一岗位下的所有投递都设置为拒绝
     * 在管理员关闭岗位和岗位过期的判断是触发
     *
     * @param positionId
     */
    private void refuseAllDeliverByPositionId(Long positionId) {
        List<ResumeDeliverEntity> delivers = resumeDeliverRepository.findAllByPositionId(positionId);
        for (ResumeDeliverEntity deliver : delivers) {
            String newState = StatusCodeUtil.codeChange(deliver.getRecruitmentState(), 2);
            deliver.setRecruitmentState(newState);
            resumeDeliverRepository.save(deliver);
        }
    }


}
