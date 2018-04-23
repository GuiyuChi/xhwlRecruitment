package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.JobIntentionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午10:20 2018/4/16
 **/
public interface JobIntentionRepository extends JpaRepository<JobIntentionEntity, Long> {
    JobIntentionEntity findByResumeId(Long resumeId);
}
