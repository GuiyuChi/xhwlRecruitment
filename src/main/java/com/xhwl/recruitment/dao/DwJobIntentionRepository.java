package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.DwJobIntentionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午2:48 2018/4/23
 **/
@Repository
public interface DwJobIntentionRepository extends JpaRepository<DwJobIntentionEntity, Long> {
    DwJobIntentionEntity findByResumeId(Long resumeId);
}
