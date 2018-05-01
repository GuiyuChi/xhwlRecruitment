package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.AdminAuthEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午9:58 2018/4/30
 **/
public interface AdminAuthRepository extends JpaRepository<AdminAuthEntity, Long> {
    //分页查询
    @Override
    Page<AdminAuthEntity> findAll(Pageable pageable);

    AdminAuthEntity findByUserId(Long userId);
}
