package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.DwWorkExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午9:59 2018/4/22
 **/
@Repository
public interface DwWorkExperienceRepository extends JpaRepository<DwWorkExperienceEntity, Long> {
    List<DwWorkExperienceEntity> findAllByResumeId(Long ResumeId);
}
