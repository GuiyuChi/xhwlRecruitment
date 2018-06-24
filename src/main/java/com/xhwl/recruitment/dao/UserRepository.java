package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午6:00 2018/3/22
 **/
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    UserEntity findByUsername(String username);

    /**
     * 在数据库里面添加一条记录
     * @param userEntity
     * @return
     */
    UserEntity save(UserEntity userEntity);
}
