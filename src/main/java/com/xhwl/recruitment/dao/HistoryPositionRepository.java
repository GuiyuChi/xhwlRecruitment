package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

/**
 * @Author: kepiao
 * @Description:
 * @Date: Create in 上午10:47 2018/5/7
 **/
@Repository
public interface HistoryPositionRepository extends JpaRepository<PositionEntity, Long> {
    //查询截止日期之前或之后的发布项目
    List<PositionEntity> findAllByPublishType(Integer publish_type);

    //根据部门号显示截至日期之前或之后的发布项目
    List<PositionEntity> findAllByPublishTypeAndDepartment(Integer publish_type, Long departmentId);

    //人事历史记录模糊查询
    List<PositionEntity> findAllByPublishTypeAndDepartmentAndPositionNameContainingAndPublishDateAfterAndDeadlineBefore(Integer publish_type, Long department, String positionName, Date publishDate, Date endDate);
    //部门号为空时
    List<PositionEntity>findAllByPublishTypeAndPositionNameContainingAndPublishDateAfterAndDeadlineBefore(Integer publish_type,String positionName,Date publishDate,Date endDate);
}
