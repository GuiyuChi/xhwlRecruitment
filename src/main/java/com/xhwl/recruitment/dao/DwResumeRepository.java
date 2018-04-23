package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.DwResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午5:10 2018/4/22
 **/
@Repository
public interface DwResumeRepository extends JpaRepository<DwResumeEntity, Long> {
    DwResumeEntity findByUserId(Long userId);
}
