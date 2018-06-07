package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.PositionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 上午10:47 2018/4/23
 **/
@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

    Page<PositionEntity> findAllByDepartmentAndPublishType(Pageable pageable, Long department, Integer publishType);


    Page<PositionEntity> findAllByPublishType(Pageable pageable, Integer publishType);

    //根据岗位进行模糊查询
    List<PositionEntity> findAllByWorkPlaceContainingAndPositionNameContainingAndPositionTypeContaining
    (String workPlace, String name, String positionType);

    //模糊查询 名称模糊，部门精确 高级管理员以上调用
    Page<PositionEntity> findAllByPositionNameContainingAndDepartmentAndPublishType(Pageable pageable, String name, Long departmentId, Integer publishType);

    //模糊查询 名称模糊
    Page<PositionEntity> findAllByPositionNameContainingAndPublishType(Pageable pageable, String name, Integer publishType);

    //岗位名称 发布状态 工作地点 岗位类型 模糊分页查询
    Page<PositionEntity> findAllByPositionNameContainingAndRecruitmentTypeAndPublishTypeAndWorkPlaceContainingAndPositionTypeContaining(String positionName, Integer recruitmentType, Integer publishType,
                                                                                                                                        String workPlace, String positionType, Pageable pageable);
}
