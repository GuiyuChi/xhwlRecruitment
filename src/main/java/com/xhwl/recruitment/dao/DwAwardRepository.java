package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.DwAwardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午2:31 2018/4/23
 **/
@Repository
public interface DwAwardRepository extends JpaRepository<DwAwardEntity, Long> {
    List<DwAwardEntity> findAllByResumeId(Long resumeId);
}
