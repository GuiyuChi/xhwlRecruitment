package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.EducationExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午6:44 2018/4/7
 **/
@Repository
public interface EducationRepository extends JpaRepository<EducationExperienceEntity,Long> {

    List<EducationExperienceEntity> findAllByResumeId(Long resumeId);
}
