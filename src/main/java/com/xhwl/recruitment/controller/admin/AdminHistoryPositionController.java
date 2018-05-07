package com.xhwl.recruitment.controller.admin;

import com.xhwl.recruitment.domain.PositionEntity;
import com.xhwl.recruitment.service.HistoryPositionService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: ke piao
 * @Description: 管理员根据简历id发送邮件的API
 * @Date: Create in 上午11:33 2018/5/7
 **/
public class AdminHistoryPositionController {
    @Autowired
    HistoryPositionService historyPositionService;
    //显示过期前项目
    @GetMapping("/admin/PositionsBeforeDeadline")
    @RequiresAuthentication
    @RequiresRoles("admin")
    public List<PositionEntity> getPositionBeforeDeadline(){
        return historyPositionService.getPositionBeforeDeadline();
    }
//显示过期后项目
    @GetMapping("/admin/PositionAfterDeadline")
    @RequiresAuthentication
    @RequiresRoles("admin")
    public List<PositionEntity>getPositionAfterDeadline(){
        return historyPositionService.getPositionAfterDeadline();
    }
}
