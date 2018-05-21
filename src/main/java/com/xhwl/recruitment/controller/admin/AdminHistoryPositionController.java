package com.xhwl.recruitment.controller.admin;

import com.xhwl.recruitment.dao.AdminAuthRepository;
import com.xhwl.recruitment.domain.AdminAuthEntity;
import com.xhwl.recruitment.domain.PositionEntity;
import com.xhwl.recruitment.service.HistoryPositionService;
import com.xhwl.recruitment.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: ke piao
 * @Description: 管理员根据简历id发送邮件的API
 * @Date: Create in 上午11:33 2018/5/7
 **/
@RestController
public class AdminHistoryPositionController {
    @Autowired
    HistoryPositionService historyPositionService;
    @Autowired
    UserService userService;
    @Autowired
    AdminAuthRepository adminAuthRepository;
    //显示过期前项目
    @GetMapping("/admin/PositionsBeforeDeadline")
    @RequiresAuthentication
    @RequiresRoles("admin")
    public Page<HashMap> getPositionBeforeDeadline(@RequestHeader HttpHeaders headers,
                                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "20") Integer size){
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId=adminAuthEntity.getDepartmentId();
        PageRequest request = new PageRequest(page - 1, size);
        return historyPositionService.getPositionBeforeDeadline(request,departmentId);
    }
//显示过期后项目
    @GetMapping("/admin/PositionAfterDeadline")
    @RequiresAuthentication
    @RequiresRoles("admin")
    public Page<HashMap>getPositionAfterDeadline(@RequestHeader HttpHeaders headers,
                                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        @RequestParam(value = "size", defaultValue = "20") Integer size){
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId=adminAuthEntity.getDepartmentId();
        PageRequest request = new PageRequest(page - 1, size);
        return historyPositionService.getPositionAfterDeadline(request,departmentId);
    }
}
