package com.xhwl.recruitment.controller.superAdmin;

import com.xhwl.recruitment.dao.AdminAuthRepository;
import com.xhwl.recruitment.dao.DepartmentRepository;
import com.xhwl.recruitment.dao.UserRepository;
import com.xhwl.recruitment.dto.AdminAuthDto;
import com.xhwl.recruitment.exception.DepartmentException;
import com.xhwl.recruitment.exception.MyNoPermissionException;
import com.xhwl.recruitment.exception.UserRepeatException;
import com.xhwl.recruitment.service.AdminAuthService;
import com.xhwl.recruitment.service.UserService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author: guiyu
 * @Description: 超级管理员（人事经理）控制下属管理员权限，增删改查
 * @Date: Create in 下午9:41 2018/4/30
 **/
@RestController
public class AuthController {

    private static final String SuperAdminRole = "superAdmin";

    private static final String SeniorAdminRole = "seniorAdmin";

    private static final String NormalAdminRole = "normalAdmin";

    @Autowired
    private AdminAuthService adminAuthService;
    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminAuthRepository adminAuthRepository;


    /**
     * 超级管理员添加管理员
     *
     * @param headers
     * @param hashMap
     */
    @PostMapping("/super/addAdmin")
    @RequiresRoles("admin")
    public void addAdmin(@RequestHeader HttpHeaders headers, @RequestBody HashMap<String, String> hashMap) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if (!adminAuthRepository.findByUserId(userId).getRole().equalsIgnoreCase(SuperAdminRole)) {
            throw new MyNoPermissionException("需要超级管理员权限");
        }

        String username = hashMap.get("username");
        String password = hashMap.get("password");
        Long departmentId = Long.valueOf(hashMap.get("department"));

        if (departmentRepository.findOne(departmentId) == null) {
            throw new DepartmentException("岗位错误");
        }

        if (userRepository.findByUsername(username) != null) {
            throw new UserRepeatException("用户已存在");
        }

        adminAuthService.addAdmin(username, password, departmentId);

    }

    /**
     * 超级管理员查看所有管理员的账号和密码
     *
     * @param headers
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/super/searchAdmins")
    @RequiresRoles("admin")
    public Page<AdminAuthDto> listAdmin(@RequestHeader HttpHeaders headers,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if (!adminAuthRepository.findByUserId(userId).getRole().equalsIgnoreCase(SuperAdminRole)) {
            throw new MyNoPermissionException("需要超级管理员权限");
        }

        PageRequest request = new PageRequest(page - 1, size);
        Page<AdminAuthDto> adminAuthDtoPage = adminAuthService.findAll(request);
        return adminAuthDtoPage;
    }

    /**
     * 超级管理员修改管理员的密码和岗位
     *
     * @param headers
     * @param hashMap
     * @return
     */
    @PostMapping("/super/modifyAdmin")
    @RequiresRoles("admin")
    public AdminAuthDto modifyAdmin(@RequestHeader HttpHeaders headers, @RequestBody HashMap<String, String> hashMap) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if (!adminAuthRepository.findByUserId(userId).getRole().equalsIgnoreCase(SuperAdminRole)) {
            throw new MyNoPermissionException("需要超级管理员权限");
        }

        String username = hashMap.get("username");
        String password = hashMap.get("password");
        Long departmentId = Long.valueOf(hashMap.get("department"));

        if (departmentRepository.findOne(departmentId) == null) {
            throw new DepartmentException("岗位错误");
        }

        return adminAuthService.modifyAdmin(username, password, departmentId);
    }

    /**
     * 超级管理员召回权限
     *
     * @param headers
     * @param adminName
     */
    @DeleteMapping("/super/recallPermission/{adminName}")
    @RequiresRoles("admin")
    public void recallPermission(@RequestHeader HttpHeaders headers, @PathVariable("adminName") String adminName) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if (!adminAuthRepository.findByUserId(userId).getRole().equalsIgnoreCase(SuperAdminRole)) {
            throw new MyNoPermissionException("需要超级管理员权限");
        }

        adminAuthService.deleteAdmin(adminName);
    }

    /**
     * 超级管理员按工号查找管理员
     *
     * @param headers
     * @param adminName
     * @return
     */
    @GetMapping("/super/searchAdmin/{adminName}")
    @RequiresRoles("admin")
    public AdminAuthDto searchAdmin(@RequestHeader HttpHeaders headers, @PathVariable("adminName") String adminName) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if (!adminAuthRepository.findByUserId(userId).getRole().equalsIgnoreCase(SuperAdminRole)) {
            throw new MyNoPermissionException("需要超级管理员权限");
        }

        return adminAuthService.searchAdmin(adminName);
    }
}
