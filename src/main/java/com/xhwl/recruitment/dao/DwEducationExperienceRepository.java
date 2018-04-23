package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.DwEducationExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午8:48 2018/4/22
 **/
@Repository
public interface DwEducationExperienceRepository extends JpaRepository<DwEducationExperienceEntity, Long> {
    List<DwEducationExperienceEntity> findAllByResumeId(Long resumeId);
}
