package com.xhwl.recruitment.controller.admin;

import com.xhwl.recruitment.dao.AdminAuthRepository;
import com.xhwl.recruitment.dao.DepartmentRepository;
import com.xhwl.recruitment.dao.PositionRepository;
import com.xhwl.recruitment.domain.AdminAuthEntity;
import com.xhwl.recruitment.dto.DeliverDto;
import com.xhwl.recruitment.exception.NoPermissionException;
import com.xhwl.recruitment.service.AuditDeliverService;
import com.xhwl.recruitment.service.DeliverService;
import com.xhwl.recruitment.service.PositionService;
import com.xhwl.recruitment.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: guiyu
 * @Description: 处理管理员关于简历投递的API
 * @Date: Create in 下午2:04 2018/5/2
 **/
@RestController
public class AdminDeliverController {

    /**
     * 人事部门的id
     */
    private static final Long PersonnelDepartmentId = 1L;

    @Autowired
    UserService userService;

    @Autowired
    PositionService positionService;

    @Autowired
    DeliverService deliverService;

    @Autowired
    AuditDeliverService auditDeliverService;

    @Autowired
    private AdminAuthRepository adminAuthRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    /**
     * 管理员查看岗位中的 简历初审 记录
     *
     * @param headers
     * @param positionId
     * @return
     */
    @GetMapping("/admin/ResumeReview/{positionId}")
    @RequiresRoles("admin")
    public List<DeliverDto> findDeliverInResumeReview(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (departmentId != PersonnelDepartmentId && departmentId != positionRepository.findOne(positionId).getDepartment()) {
            throw new NoPermissionException("没有权限");
        }
        return auditDeliverService.findDeliverInResumeReview(positionId, departmentId);
    }

    /**
     * 管理员查看岗位中的 HR初审 记录
     *
     * @param headers
     * @param positionId
     * @return
     */
    @GetMapping("/admin/HRFristReview/{positionId}")
    @RequiresRoles("admin")
    public List<DeliverDto> findDeliverInHRFristReview(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (departmentId != PersonnelDepartmentId && departmentId != positionRepository.findOne(positionId).getDepartment()) {
            throw new NoPermissionException("没有权限");
        }

        return auditDeliverService.findDeliverInHRFristReview(positionId, departmentId);
    }


    /**
     * 管理员查看岗位中的 部门笔试 记录
     *
     * @param headers
     * @param positionId
     * @return
     */
    @GetMapping("/admin/DepartmentWritten/{positionId}")
    @RequiresRoles("admin")
    public List<DeliverDto> findDeliverInDepartmentWrittenExamination(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (departmentId != PersonnelDepartmentId && departmentId != positionRepository.findOne(positionId).getDepartment()) {
            throw new NoPermissionException("没有权限");
        }

        return auditDeliverService.findDeliverInDepartmentWrittenExamination(positionId, departmentId);
    }

    /**
     * 管理员查看岗位中的 部门面试 记录
     * @param headers
     * @param positionId
     * @return
     */
    @GetMapping("/admin/DepartmentInterview/{positionId}")
    @RequiresRoles("admin")
    public List<DeliverDto> findDeliverInDepartmentInterview(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (departmentId != PersonnelDepartmentId && departmentId != positionRepository.findOne(positionId).getDepartment()) {
            throw new NoPermissionException("没有权限");
        }

        return auditDeliverService.findDeliverInDepartmentInterview(positionId, departmentId);
    }

    /**
     * 管理员查看岗位中的 HR面试 记录
     * @param headers
     * @param positionId
     * @return
     */
    @GetMapping("/admin/HRInterview/{positionId}")
    @RequiresRoles("admin")
    public List<DeliverDto> findDeliverInHRInterview(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (departmentId != PersonnelDepartmentId && departmentId != positionRepository.findOne(positionId).getDepartment()) {
            throw new NoPermissionException("没有权限");
        }

        return auditDeliverService.findDeliverInHRInterview(positionId, departmentId);
    }

    /**
     * 管理员查看岗位中的 已通过 记录
     * @param headers
     * @param positionId
     * @return
     */
    @GetMapping("/admin/Pass/{positionId}")
    @RequiresRoles("admin")
    public List<DeliverDto> findDeliverInPass(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (departmentId != PersonnelDepartmentId && departmentId != positionRepository.findOne(positionId).getDepartment()) {
            throw new NoPermissionException("没有权限");
        }

        return auditDeliverService.findDeliverInPass(positionId, departmentId);
    }

    @GetMapping("/admin/Refuse/{positionId}")
    @RequiresRoles("admin")
    public List<HashMap> findDeliverInRefuse(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (departmentId != PersonnelDepartmentId && departmentId != positionRepository.findOne(positionId).getDepartment()) {
            throw new NoPermissionException("没有权限");
        }

        return auditDeliverService.findDeliverInRefuse(positionId, departmentId);
    }

    /**
     * 管理员让简历流转到下一步，通过
     *
     * @param headers
     * @param deliverId
     */
    @PutMapping("/admin/{deliverId}")
    @RequiresRoles("admin")
    public void passToNextStep(@RequestHeader HttpHeaders headers, @PathVariable("deliverId") Long deliverId) {
        //todo 重复点击检验
        auditDeliverService.deliverToNextStep(deliverId);
    }
}
