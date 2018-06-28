package com.xhwl.recruitment.controller.admin;

import com.xhwl.recruitment.dao.AdminAuthRepository;
import com.xhwl.recruitment.dao.DepartmentRepository;
import com.xhwl.recruitment.dao.PositionRepository;
import com.xhwl.recruitment.dao.ResumeDeliverRepository;
import com.xhwl.recruitment.domain.AdminAuthEntity;
import com.xhwl.recruitment.dto.DeliverDto;
import com.xhwl.recruitment.exception.MyNoPermissionException;
import com.xhwl.recruitment.exception.PositionNoExistException;
import com.xhwl.recruitment.service.AuditDeliverService;
import com.xhwl.recruitment.service.DeliverService;
import com.xhwl.recruitment.service.PositionService;
import com.xhwl.recruitment.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private ResumeDeliverRepository resumeDeliverRepository;

    /**
     * 管理员查看岗位中的 简历初审 记录
     *
     * @param headers
     * @param positionId
     * @return
     */
    @GetMapping("/admin/ResumeReview/{positionId}")
    @RequiresRoles("admin")
    public Page<DeliverDto> findDeliverInResumeReview(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId,
                                                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                      @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (!departmentId.equals(PersonnelDepartmentId) && !departmentId.equals(positionRepository.findOne(positionId).getDepartment())) {
            throw new MyNoPermissionException("没有权限");
        }
        PageRequest request = new PageRequest(page - 1, size);
        return auditDeliverService.findDeliverInResumeReview(request, positionId, departmentId);
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
    public Page<DeliverDto> findDeliverInHRFristReview(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId,
                                                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (!departmentId.equals(PersonnelDepartmentId) && !departmentId.equals(positionRepository.findOne(positionId).getDepartment())) {
            throw new MyNoPermissionException("没有权限");
        }
        PageRequest request = new PageRequest(page - 1, size);
        return auditDeliverService.findDeliverInHRFristReview(request, positionId, departmentId);
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
    public Page<DeliverDto> findDeliverInDepartmentWrittenExamination(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId,
                                                                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                                      @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (!departmentId.equals(PersonnelDepartmentId) && !departmentId.equals(positionRepository.findOne(positionId).getDepartment())) {
            throw new MyNoPermissionException("没有权限");
        }

        PageRequest request = new PageRequest(page - 1, size);
        return auditDeliverService.findDeliverInDepartmentWrittenExamination(request, positionId, departmentId);
    }

    /**
     * 管理员查看岗位中的 部门面试 记录
     *
     * @param headers
     * @param positionId
     * @return
     */
    @GetMapping("/admin/DepartmentInterview/{positionId}")
    @RequiresRoles("admin")
    public Page<DeliverDto> findDeliverInDepartmentInterview(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId,
                                                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (!departmentId.equals(PersonnelDepartmentId) && !departmentId.equals(positionRepository.findOne(positionId).getDepartment())) {
            throw new MyNoPermissionException("没有权限");
        }
        PageRequest request = new PageRequest(page - 1, size);
        return auditDeliverService.findDeliverInDepartmentInterview(request, positionId, departmentId);
    }

    /**
     * 管理员查看岗位中的 HR面试 记录
     *
     * @param headers
     * @param positionId
     * @return
     */
    @GetMapping("/admin/HRInterview/{positionId}")
    @RequiresRoles("admin")
    public Page<DeliverDto> findDeliverInHRInterview(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId,
                                                     @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (!departmentId.equals(PersonnelDepartmentId) && !departmentId.equals(positionRepository.findOne(positionId).getDepartment()))  {
            throw new MyNoPermissionException("没有权限");
        }
        PageRequest request = new PageRequest(page - 1, size);
        return auditDeliverService.findDeliverInHRInterview(request, positionId, departmentId);
    }

    /**
     * 管理员查看岗位中的 已通过 记录
     *
     * @param headers
     * @param positionId
     * @return
     */
    @GetMapping("/admin/Pass/{positionId}")
    @RequiresRoles("admin")
    public Page<DeliverDto> findDeliverInPass(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId,
                                              @RequestParam(value = "page", defaultValue = "1") Integer page,
                                              @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (!departmentId.equals(PersonnelDepartmentId) && !departmentId.equals(positionRepository.findOne(positionId).getDepartment()))  {
            throw new MyNoPermissionException("没有权限");
        }

        PageRequest request = new PageRequest(page - 1, size);
        return auditDeliverService.findDeliverInPass(request, positionId, departmentId);
    }

    /**
     * 管理员查看岗位中的 已拒绝 记录
     *
     * @param headers
     * @param positionId
     * @return
     */
    @GetMapping("/admin/Refuse/{positionId}")
    @RequiresRoles("admin")
    public Page<HashMap> findDeliverInRefuse(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId,
                                             @RequestParam(value = "page", defaultValue = "1") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        if (!departmentId.equals(PersonnelDepartmentId) && !departmentId.equals(positionRepository.findOne(positionId).getDepartment()))  {
            throw new MyNoPermissionException("没有权限");
        }
        PageRequest request = new PageRequest(page - 1, size);
        return auditDeliverService.findDeliverInRefuse(request, positionId, departmentId);
    }

    /**
     * 管理员让简历流转到下一步，通过
     *
     * @param headers
     * @param deliverId
     */
    @PutMapping("/admin/passToNext/{deliverId}")
    @RequiresRoles("admin")
    public void passToNextStep(@RequestHeader HttpHeaders headers, @PathVariable("deliverId") Long deliverId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        Long positionId = resumeDeliverRepository.findOne(deliverId).getPositionId();
        if (!departmentId.equals(PersonnelDepartmentId) && !departmentId.equals(positionRepository.findOne(positionId).getDepartment()))  {
            throw new MyNoPermissionException("没有权限");
        }
        auditDeliverService.deliverToNextStep(deliverId);
    }

    /**
     * 管理员回绝简历
     *
     * @param headers
     * @param deliverId
     */
    @PutMapping("/admin/giveRefuse/{deliverId}")
    @RequiresRoles("admin")
    public void refuseDeliver(@RequestHeader HttpHeaders headers, @PathVariable("deliverId") Long deliverId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        Long positionId = resumeDeliverRepository.findOne(deliverId).getPositionId();
        if (!departmentId.equals(PersonnelDepartmentId) && !departmentId.equals(positionRepository.findOne(positionId).getDepartment()))  {
            throw new MyNoPermissionException("没有权限");
        }
        auditDeliverService.refuseDeliver(deliverId);
    }

    /**
     * 管理员撤销回绝，回滚到拒绝前状态
     *
     * @param headers
     * @param deliverId
     */
    @PutMapping("/admin/cancelRefuse/{deliverId}")
    @RequiresRoles("admin")
    public void cancelRefuse(@RequestHeader HttpHeaders headers, @PathVariable("deliverId") Long deliverId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();

        if (!departmentId.equals(PersonnelDepartmentId)) {
            throw new MyNoPermissionException("没有权限");
        }
        auditDeliverService.cancelRefuse(deliverId);
    }

    /**
     * 管理员获取某岗位的投递条数
     *
     * @param positionId
     * @return
     */
    @GetMapping("/admin/getDeliverNum/{positionId}")
    @RequiresRoles("admin")
    public HashMap getDeliverNum(@PathVariable("positionId") Long positionId) {
        if (positionRepository.findOne(positionId) == null) {
            throw new PositionNoExistException("岗位不存在");
        }

        return deliverService.getNumOfDeliver(positionId);
    }

    /**
     * 管理员获取未读投递记录的条数
     *
     * @param headers
     * @param positionId
     * @return
     */
    @GetMapping("admin/getNotReadNum/{positionId}")
    @RequiresRoles("admin")
    public HashMap getNotReadNum(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        Long departmentId = adminAuthEntity.getDepartmentId();
        return auditDeliverService.getNotReadNum(positionId, departmentId);
    }
}
