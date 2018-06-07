package com.xhwl.recruitment.Timer;

import com.xhwl.recruitment.dao.PositionRepository;
import com.xhwl.recruitment.dao.ResumeDeliverRepository;
import com.xhwl.recruitment.domain.PositionEntity;
import com.xhwl.recruitment.domain.ResumeDeliverEntity;
import com.xhwl.recruitment.util.StatusCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午7:51 2018/6/7
 **/
@Component
public class PositionTimer {

    @Autowired
    PositionRepository positionRepository;
    @Autowired
    ResumeDeliverRepository resumeDeliverRepository;

    /**
     * 每天1：30清理已经过期的项目
     * 将 publish_type 从1置为2
     */
    @Scheduled(cron = "0 30 1 * * ?")
    public void positionOverdueClear() {
        List<PositionEntity> positionEntities = positionRepository.findAll();
        positionEntities.stream()
                .filter(p->(p.getPublishType()==1 && p.getDeadline().before(new Date(System.currentTimeMillis()))))
                .forEach(p->changeAndSave(p));
    }

    private void changeAndSave(PositionEntity positionEntity){
        positionEntity.setPublishType(2);
        positionRepository.saveAndFlush(positionEntity);

        //将对应岗位下的简历全部拒绝
        refuseAllDeliverByPositionId(positionEntity.getId());
    }

    /**
     * 将某一岗位下的所有投递都设置为拒绝
     * 在管理员关闭岗位和岗位过期的判断时触发
     *
     * @param positionId
     */
    private void refuseAllDeliverByPositionId(Long positionId) {
        List<ResumeDeliverEntity> delivers = resumeDeliverRepository.findAllByPositionId(positionId);
        for (ResumeDeliverEntity deliver : delivers) {
            String newState = StatusCodeUtil.codeChange(deliver.getRecruitmentState(), 2);
            deliver.setRecruitmentState(newState);
            resumeDeliverRepository.save(deliver);
        }
    }
}
