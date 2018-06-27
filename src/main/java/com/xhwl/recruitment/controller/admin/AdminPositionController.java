package com.xhwl.recruitment.controller.admin;

import com.xhwl.recruitment.dao.AdminAuthRepository;
import com.xhwl.recruitment.dao.DepartmentRepository;
import com.xhwl.recruitment.dao.PositionRepository;
import com.xhwl.recruitment.domain.AdminAuthEntity;
import com.xhwl.recruitment.exception.DepartmentException;
import com.xhwl.recruitment.exception.FormSubmitFormatException;
import com.xhwl.recruitment.exception.MyNoPermissionException;
import com.xhwl.recruitment.exception.PositionNoExistException;
import com.xhwl.recruitment.service.DeliverService;
import com.xhwl.recruitment.service.PositionService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.util.ValidateUtils;
import com.xhwl.recruitment.vo.PositionVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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

    @Autowired
    private PositionRepository positionRepository;

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
            throw new MyNoPermissionException("需要中级管理员以上权限");
        }

        //表单验证
        if (!formValid(positionVo)) {
            throw new FormSubmitFormatException("表单格式错误");
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
    public HashMap adminGetPosition(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {
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
                                           @RequestParam(value = "size", defaultValue = "20") Integer size,
                                           @RequestParam(value = "department", defaultValue = "0") Long department,
                                           @RequestParam(value = "positionName", defaultValue = "") String positionName,
                                           @RequestParam(value = "earlyDate", defaultValue = "") String early_date,
                                           @RequestParam(value = "lastDate", defaultValue = "") String last_date) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);

        // 管理员实现岗位查看时按 发布时间 逆序，即后添加的显示在前面
        Sort sort = new Sort(Sort.Direction.DESC, "publishDate");

        PageRequest request = new PageRequest(page - 1, size,sort);
        //日期格式必须正确
        if(!"".equals(early_date) && !ValidateUtils.isValidDate(early_date)){
            throw new FormSubmitFormatException("日期格式错误");
        }
        if(!"".equals(last_date) && !ValidateUtils.isValidDate(last_date)){
            throw new FormSubmitFormatException("日期格式错误");
        }
        if (adminAuthEntity.getDepartmentId() == PersonnelDepartmentId) {
            return positionService.adminGetAllPublishPositions(request, 1, department, positionName, early_date, last_date);
        } else {
            return positionService.adminGetDepartmentPositions(request, adminAuthEntity.getDepartmentId(), PositionisPublish, positionName, early_date, last_date);
        }
    }


    /**
     * 管理员（人事）按ID关闭岗位
     *
     * @param positionId
     */
    @DeleteMapping("/admin/position/{positionId}")
    @RequiresRoles("admin")
    public void closePosition(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        AdminAuthEntity adminAuthEntity = adminAuthRepository.findByUserId(userId);

        if (positionRepository.findOne(positionId) == null) {
            throw new PositionNoExistException("岗位不存在");
        }

        if (adminAuthEntity.getDepartmentId() == PersonnelDepartmentId) {
            positionService.adminClosePosition(positionId);
        } else {
            throw new MyNoPermissionException("没有权限");
        }
    }

    /**
     * 表单验证
     *
     * @param vo
     * @return
     */
    private boolean formValid(PositionVo vo) {
        boolean validRes = true;

        if (!ValidateUtils.Notempty(vo.getPositionName())) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(String.valueOf(vo.getDepartment()))) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(String.valueOf(vo.getResumeAuditDepartment()))) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(String.valueOf(vo.getAssessmentDepartment()))) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(String.valueOf(vo.getPositionType()))) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(String.valueOf(vo.getRecruitmentType()))) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(vo.getWorkPlace())) {
            validRes = false;
        }
        if (!ValidateUtils.Notempty(vo.getEducation())) {
            validRes = false;
        }
        if(!ValidateUtils.Notempty(String.valueOf(vo.getRecruitingNumbers()))){
            validRes = false;
        }
        if(!ValidateUtils.isValidDate(vo.getDeadline())){
            validRes = false;
        }

        return validRes;
    }
}
