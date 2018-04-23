package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.PersonalInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午10:03 2018/4/7
 **/
@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformationEntity,Long>{
    PersonalInformationEntity findByResumeId(Long resumeId);
}
