package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.AwardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 上午12:13 2018/4/15
 **/
@Repository
public interface AwardRepository extends JpaRepository<AwardEntity,Long>{
    List<AwardEntity> findAllByResumeId(Long resumeId);
}
