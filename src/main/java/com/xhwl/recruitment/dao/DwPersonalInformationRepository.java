package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.DwPersonalInformationEntity;
import com.xhwl.recruitment.domain.DwProjectExperienceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午5:42 2018/4/22
 **/
public interface DwPersonalInformationRepository extends JpaRepository<DwPersonalInformationEntity, Long> {
    DwPersonalInformationEntity findByResumeId(Long resumeId);
}
