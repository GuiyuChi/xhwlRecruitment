package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.InternshipExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午11:57 2018/4/14
 **/
@Repository
public interface InternshipExperienceRepository extends JpaRepository<InternshipExperienceEntity, Long> {
    List<InternshipExperienceEntity> findAllByResumeId(Long resumeId);
}
