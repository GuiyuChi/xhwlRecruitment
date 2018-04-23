package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.DwTrainingExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午9:12 2018/4/22
 **/
@Repository
public interface DwTrainingExperienceRepository extends JpaRepository<DwTrainingExperienceEntity, Long> {
    List<DwTrainingExperienceEntity> findAllByResumeId(Long resumeId);
}
