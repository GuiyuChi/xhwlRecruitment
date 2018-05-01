package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.PositionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 上午10:47 2018/4/23
 **/
@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

    Page<PositionEntity> findAllByDepartmentAndPublishType(Pageable pageable,Long department,Integer publishType);


    Page<PositionEntity> findAllByPublishType(Pageable pageable,Integer publishType);
}
