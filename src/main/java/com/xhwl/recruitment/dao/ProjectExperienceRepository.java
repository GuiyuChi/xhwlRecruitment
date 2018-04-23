package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.ProjectExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午3:02 2018/4/9
 **/
@Repository
public interface ProjectExperienceRepository extends JpaRepository<ProjectExperienceEntity,Long>{
    List<ProjectExperienceEntity> findAllByResumeId(Long resumeId);
}
