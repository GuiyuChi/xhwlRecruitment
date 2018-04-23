package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.ResumeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午4:39 2018/4/7
 **/
@Repository
public interface ResumeRepository extends JpaRepository<ResumeEntity, Long> {

    //添加一个新的简历
    ResumeEntity save(ResumeEntity resumeEntity);

    //根据用户id查找简历表
    ResumeEntity findByUserId(Long userId);

}
