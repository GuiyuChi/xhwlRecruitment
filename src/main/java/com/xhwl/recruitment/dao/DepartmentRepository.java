package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午10:00 2018/4/30
 **/
public interface DepartmentRepository  extends JpaRepository<DepartmentEntity,Long>{
    
}
