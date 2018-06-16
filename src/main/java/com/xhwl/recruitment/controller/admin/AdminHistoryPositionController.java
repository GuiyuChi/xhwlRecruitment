package com.xhwl.recruitment.controller.admin;

import com.xhwl.recruitment.dao.AdminAuthRepository;
import com.xhwl.recruitment.dao.ResumeDeliverRepository;
import com.xhwl.recruitment.domain.AdminAuthEntity;
import com.xhwl.recruitment.domain.PositionEntity;
import com.xhwl.recruitment.domain.ResumeDeliverEntity;
import com.xhwl.recruitment.service.HistoryPositionService;
import com.xhwl.recruitment.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @Autowired
    ResumeDeliverRepository resumeDeliverRepository;
    //显示过期前项目
    @GetMapping("/admin/PositionsBeforeDeadline")
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

    @PostMapping("/admin/searchPositionAfterDeadline")//查询历史记录
   @RequiresRoles("admin")
    public Page<HashMap>searchPositionAfterDeadline(@RequestHeader HttpHeaders headers,
                                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                    @RequestParam(value = "size", defaultValue = "20") Integer size,
                                                    @RequestParam(value="publish_date") String publish_date,
                                                    @RequestParam(value="end_date") String end_date,
                                                    @RequestParam(value="departmentName")String departmentName,
                                                    @RequestParam(value="positionName") String positionName) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date endDate=format.parse(end_date);
        Date publishDate=format.parse(publish_date);
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId=adminAuthEntity.getDepartmentId();
        PageRequest request = new PageRequest(page - 1, size);
        return historyPositionService.searchPositionAfterDeadline(request,departmentId,publishDate,endDate,departmentName,positionName);
    }
//    @GetMapping("admin/getMailState/{resumeId}")//管理员拿到email_state字段，判断是否发送邮件
//    @RequiresRoles("admin")
//    public int getMailState(@PathVariable("resumeId") Long resumeId){
//        ResumeDeliverEntity resumeDelieverEntity=resumeDeliverRepository.findById(resumeId);
//        return resumeDelieverEntity.getEmailState();
//    }
}
