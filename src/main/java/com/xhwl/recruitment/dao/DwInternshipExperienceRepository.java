package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.DwInternshipExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午2:09 2018/4/23
 **/
@Repository
public interface DwInternshipExperienceRepository extends JpaRepository<DwInternshipExperienceEntity, Long> {
    List<DwInternshipExperienceEntity> findAllByResumeId(Long resumeId);
}
