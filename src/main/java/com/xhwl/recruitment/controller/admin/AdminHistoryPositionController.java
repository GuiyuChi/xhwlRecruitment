package com.xhwl.recruitment.controller.admin;

import com.xhwl.recruitment.dao.AdminAuthRepository;
import com.xhwl.recruitment.dao.ResumeDeliverRepository;
import com.xhwl.recruitment.domain.AdminAuthEntity;
import com.xhwl.recruitment.domain.PositionEntity;
import com.xhwl.recruitment.domain.ResumeDeliverEntity;
import com.xhwl.recruitment.exception.FormSubmitFormatException;
import com.xhwl.recruitment.service.HistoryPositionService;
import com.xhwl.recruitment.service.PositionService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.util.ValidateUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    @Autowired
    PositionService positionService;

    /**
     * 人事部门的id
     */
    private static final Long PersonnelDepartmentId = 1L;

    //显示过期前项目
    @GetMapping("/admin/PositionsBeforeDeadline")
    @RequiresRoles("admin")
    public Page<HashMap> getPositionBeforeDeadline(@RequestHeader HttpHeaders headers,
                                                   @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        PageRequest request = new PageRequest(page - 1, size);
        return historyPositionService.getPositionBeforeDeadline(request, departmentId);
    }

    //显示过期后项目
    @GetMapping("/admin/PositionAfterDeadline")
    @RequiresRoles("admin")
    public Page<HashMap> getPositionAfterDeadline(@RequestHeader HttpHeaders headers,
                                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        PageRequest request = new PageRequest(page - 1, size);
        return historyPositionService.getPositionAfterDeadline(request, departmentId);
    }

    /**
     * 管理员（人事）获取全部处于招聘中的岗位，中级和超级管理员可以获得全部，其余管理员只能看到自己部门的情况
     *
     * @return
     */
    @PostMapping("/admin/searchPositionAfterDeadline")
    @RequiresRoles("admin")
    public Page<HashMap> adminGetPositions(@RequestHeader HttpHeaders headers,
                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "20") Integer size,
                                           @RequestParam(value = "department", defaultValue = "0") Long department,
                                           @RequestParam(value = "positionName", defaultValue = "") String positionName,
                                           @RequestParam(value = "publish_date", defaultValue = "") String early_date,
                                           @RequestParam(value = "end_date", defaultValue = "") String last_date) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);

        // 管理员实现岗位查看时按 发布时间 逆序，即后添加的显示在前面
        Sort sort = new Sort(Sort.Direction.DESC, "publishDate");

        PageRequest request = new PageRequest(page - 1, size, sort);
        //日期格式必须正确
        if (!"".equals(early_date) && !ValidateUtils.isValidDate(early_date)) {
            throw new FormSubmitFormatException("日期格式错误");
        }
        if (!"".equals(last_date) && !ValidateUtils.isValidDate(last_date)) {
            throw new FormSubmitFormatException("日期格式错误");
        }
        if (adminAuthEntity.getDepartmentId() == PersonnelDepartmentId) {
            return positionService.adminGetAllPublishPositions(request, 2, department, positionName, early_date, last_date);
        } else {
            return positionService.adminGetDepartmentPositions(request, adminAuthEntity.getDepartmentId(), 2, positionName, early_date, last_date);
        }
    }
}
