package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.WorkExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午3:40 2018/4/9
 **/
@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperienceEntity, Long> {
    List<WorkExperienceEntity> findAllByResumeId(Long resumeId);
}
