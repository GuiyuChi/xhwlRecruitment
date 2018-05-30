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
    //查询截止日期之前的发布项目
    @Query("select p from PositionEntity p where p.deadline>=?1 ")
    List<PositionEntity> findAllBeforeDeadline(Date LocalTime);

    //根据部门号显示截至日期之前的发布项目
    @Query("select p from PositionEntity p where p.deadline>=?1 and p.department=?2 ")
    List<PositionEntity> findBeforeDeadline(Date LocalTime,Long departmentId);

    //查询截至日期之后的发布项目
    @Query("select p from PositionEntity p where p.deadline<?1")
    List<PositionEntity> findAllAfterDeadline(Date LocalTime);

    //根据部门号查询截至日期之后的发布项目
    @Query("select p from PositionEntity p where p.deadline<?1 and p.department=?2")
    List<PositionEntity> findAfterDeadline(Date LocalTime,Long departmentId);

    //人事历史记录模糊查询
    List<PositionEntity> findAllByDeadlineBeforeAndDepartmentInAndPositionNameContainingAndPublishDateAfterAndDeadlineBefore(Date LocalTime, List<Long>department, String positionName, Date publishDate, Date endDate);

    //非人事历史记录模糊查询
    List<PositionEntity> findAllByDeadlineBeforeAndDepartmentAndPositionNameContainingAndPublishDateAfterAndDeadlineBefore(Date LocalTime, Long departmentId, String positionName, Date publishDate, Date endDate);
    //普通部门历史记录模糊查询
}
