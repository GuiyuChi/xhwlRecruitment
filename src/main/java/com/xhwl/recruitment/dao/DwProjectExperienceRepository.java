package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.DwProjectExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午9:28 2018/4/22
 **/
@Repository
public interface DwProjectExperienceRepository extends JpaRepository<DwProjectExperienceEntity, Long> {
    List<DwProjectExperienceEntity> findAllByResumeId(Long resumeId);
}
