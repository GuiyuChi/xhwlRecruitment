package com.xhwl.recruitment.controller.admin;

import com.xhwl.recruitment.dao.AdminAuthRepository;
import com.xhwl.recruitment.dao.DepartmentRepository;
import com.xhwl.recruitment.domain.AdminAuthEntity;
import com.xhwl.recruitment.domain.PositionEntity;
import com.xhwl.recruitment.exception.DepartmentException;
import com.xhwl.recruitment.exception.NoPermissionException;
import com.xhwl.recruitment.service.DeliverService;
import com.xhwl.recruitment.service.PositionService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.vo.PositionVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * @Description: 管理员关于岗位的api
 * @Date: Create in 上午11:33 2018/5/1
 **/
@RestController
public class AdminPositionController {
    private static final String SuperAdminRole = "superAdmin";

    private static final String SeniorAdminRole = "seniorAdmin";

    private static final String NormalAdminRole = "normalAdmin";

    /**
     * 人事部门的id
     */
    private static final Long PersonnelDepartmentId = 1L;

    /**
     * 发布中的工作状态
     */
    private static final Integer PositionisPublish = 1;

    @Autowired
    UserService userService;

    @Autowired
    PositionService positionService;

    @Autowired
    DeliverService deliverService;

    @Autowired
    private AdminAuthRepository adminAuthRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * 管理员（人事）添加和修改职位,前端判断全部非空
     *
     * @param headers
     * @param positionVo
     */
    @PostMapping("/admin/position")
    @RequiresRoles("admin")
    public void publishPosition(@RequestHeader HttpHeaders headers, @RequestBody PositionVo positionVo) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);
        if (!(adminAuthEntity.getRole().equalsIgnoreCase(SeniorAdminRole) || adminAuthEntity.getRole().equalsIgnoreCase(SuperAdminRole))) {
            throw new NoPermissionException("需要中级管理员以上权限");
        }

        Long department = positionVo.getDepartment();
        Long resumeAuditDepartment = positionVo.getResumeAuditDepartment();
        Long assessmentDepartment = positionVo.getAssessmentDepartment();

        if (departmentRepository.findOne(department) == null || departmentRepository.findOne(resumeAuditDepartment) == null ||
                departmentRepository.findOne(assessmentDepartment) == null) {
            throw new DepartmentException("部门输入错误");
        }
        positionService.addPosition(positionVo);
    }

    /**
     * 管理员获取岗位详情,
     *
     * @param positionId
     * @return
     */
    @GetMapping("/admin/position/{positionId}")
    @RequiresRoles("admin")
    public HashMap adminGetPosition(@RequestHeader HttpHeaders headers,@PathVariable("positionId") Long positionId) {
        return positionService.adminGetPosition(positionId);
    }

    /**
     * 管理员（人事）获取全部处于招聘中的岗位，中级和超级管理员可以获得全部，其余管理员只能看到自己部门的情况
     *
     * @return
     */
    @PostMapping("/admin/positions")
    @RequiresRoles("admin")
    public Page<HashMap> adminGetPositions(@RequestHeader HttpHeaders headers,
                                                  @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                  @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);

        PageRequest request = new PageRequest(page - 1, size);
        if(adminAuthEntity.getDepartmentId()==PersonnelDepartmentId){
            return positionService.adminGetAllPublishPositions(request,1);
        }else{
            return positionService.adminGetDepartmentPositions(request,adminAuthEntity.getDepartmentId(),PositionisPublish);
        }
    }


    /**
     * 管理员（人事）按ID删除岗位
     *
     * @param positionId
     */
    @DeleteMapping("/admin/position/{positionId}")
    @RequiresPermissions(logical = Logical.AND, value = {"position"})
    public void deletePosition(@PathVariable("positionId") Long positionId) {
        positionService.adminDeletePosition(positionId);
    }
}
