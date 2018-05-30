package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午10:00 2018/4/30
 **/
public interface DepartmentRepository  extends JpaRepository<DepartmentEntity,Long>{
    //根据部门名称模糊查询
    List<DepartmentEntity> findAllByNameContaining(String departmentName);
}
