package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.PositionEntity;
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

}
