package com.xhwl.recruitment.controller.superAdmin;

import com.xhwl.recruitment.dao.DepartmentRepository;
import com.xhwl.recruitment.dao.UserRepository;
import com.xhwl.recruitment.exception.DepartmentException;
import com.xhwl.recruitment.exception.UserRepeatException;
import com.xhwl.recruitment.service.AdminAuthService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import sun.jvm.hotspot.debugger.DebuggerException;

import java.util.HashMap;

/**
 * @Author: guiyu
 * @Description: 超级管理员（人事经理）控制下属管理员权限，增删改查
 * @Date: Create in 下午9:41 2018/4/30
 **/
@RestController
public class AuthController {
    @Autowired
    private AdminAuthService adminAuthService;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;


    /**
     * 超级管理员添加管理员
     * @param headers
     * @param hashMap
     */
    @PostMapping("/super/addAdmin")
    @RequiresRoles("admin")
    public void addAdmin(@RequestHeader HttpHeaders headers, @RequestBody HashMap<String, String> hashMap) {
        String username = hashMap.get("username");
        String password = hashMap.get("password");
        Long departmentId = Long.valueOf(hashMap.get("department"));

        if (departmentRepository.findOne(departmentId) == null) {
            throw new DepartmentException("岗位错误");
        }

        if(userRepository.findByUsername(username)!=null){
            throw new UserRepeatException("用户已存在");
        }

        adminAuthService.addAdmin(username,password,departmentId);

    }
}
