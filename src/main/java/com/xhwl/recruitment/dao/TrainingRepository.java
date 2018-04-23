package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.TrainingExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description: 培训经历表的操作接口
 * @Date: Create in 下午9:50 2018/4/7
 **/
@Repository
public interface TrainingRepository extends JpaRepository<TrainingExperienceEntity, Long> {
    List<TrainingExperienceEntity> findAllByResumeId(Long resumeId);
}
