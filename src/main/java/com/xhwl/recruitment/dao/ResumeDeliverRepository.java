package com.xhwl.recruitment.dao;

import com.xhwl.recruitment.domain.ResumeDeliverEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    List<ResumeDeliverEntity> findAllByPositionId(Long positionId);

    ResumeDeliverEntity findById(Long resumeId);

    ResumeDeliverEntity saveAndFlush(ResumeDeliverEntity resumeDeliverEntity);

    /**
     * 分页查询，需要输入岗位id和简历的状态字
     * 状态字为模糊查询，例如输入 2 会查出该岗位下所有被拒绝的简历
     *
     * @param pageable
     * @param positionId
     * @param code
     * @return
     */
    Page<ResumeDeliverEntity> findAllByPositionIdAndRecruitmentStateContaining(Pageable pageable, Long positionId, String code);

}
