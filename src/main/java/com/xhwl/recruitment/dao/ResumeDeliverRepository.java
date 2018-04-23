package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.ResumeDeliverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午4:42 2018/4/23
 **/
@Repository
public interface ResumeDeliverRepository extends JpaRepository<ResumeDeliverEntity, Long> {
    List<ResumeDeliverEntity> findAllByUserId(Long userId);
}
