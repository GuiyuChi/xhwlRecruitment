package com.xhwl.recruitment.service;

import com.xhwl.recruitment.dao.AdminAuthRepository;
import com.xhwl.recruitment.dao.DepartmentRepository;
import com.xhwl.recruitment.dao.UserRepository;
import com.xhwl.recruitment.domain.AdminAuthEntity;
import com.xhwl.recruitment.domain.UserEntity;
import com.xhwl.recruitment.dto.AdminAuthDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guiyu
 * @Description: 提供管理员的权限服务
 * @Date: Create in 下午9:46 2018/4/30
 **/
@Service
public class AdminAuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminAuthRepository adminAuthRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    /**
     * 人事部门的id
     */
    private static final Long PersonnelDepartmentId = 1L;

    private static final String SeniorAdminRole = "seniorAdmin";

    private static final String NormalAdminRole = "normalAdmin";

    /**
     * 人事经理添加管理员
     *
     * @param userName
     * @param passWord
     * @param departmentId
     */
    @Transactional
    public void addAdmin(String userName, String passWord, Long departmentId) {
        //保存到user表
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userName);
        userEntity.setPassword(passWord);
        userEntity.setRole("admin");
        UserEntity resUser = userRepository.save(userEntity);

        AdminAuthEntity adminAuthEntity = new AdminAuthEntity();
        adminAuthEntity.setUserId(resUser.getId());
        adminAuthEntity.setUserName(resUser.getUsername());
        adminAuthEntity.setDepartmentId(departmentId);

        if (departmentId == PersonnelDepartmentId) {
            adminAuthEntity.setRole(SeniorAdminRole);
        } else {
            adminAuthEntity.setRole(NormalAdminRole);
        }

        adminAuthRepository.save(adminAuthEntity);

    }

    /**
     * 超级管理员修改管理员部门或密码
     *
     * @param username
     * @param passWord
     * @param departmentId
     */
    public AdminAuthDto modifyAdmin(String username, String passWord, Long departmentId) {
        AdminAuthEntity admin = adminAuthRepository.findByUserName(username);
        Long userId = admin.getUserId();

        //修改user表
        UserEntity user = userRepository.findOne(userId);
        user.setPassword(passWord);
        UserEntity userEntity = userRepository.save(user);

        //修改管理员表
        admin.setDepartmentId(departmentId);
        if(!admin.getRole().equals("superAdmin"))
        if (departmentId == PersonnelDepartmentId) {
            admin.setRole(SeniorAdminRole);
        } else {
            admin.setRole(NormalAdminRole);
        }
        AdminAuthEntity adminAuthEntity = adminAuthRepository.saveAndFlush(admin);

        AdminAuthDto adminAuthDto = new AdminAuthDto();
        adminAuthDto.setId(adminAuthEntity.getId());
        adminAuthDto.setUsername(adminAuthEntity.getUserName());
        adminAuthDto.setPassword(userEntity.getPassword());
        adminAuthDto.setDepartment(String.valueOf(adminAuthEntity.getDepartmentId()));
        return adminAuthDto;

    }

    /**
     * 超级管理员删除管理员
     *
     * @param username
     */
    public void deleteAdmin(String username) {
        AdminAuthEntity admin = adminAuthRepository.findByUserName(username);
        Long userId = admin.getUserId();
        Long adminId = admin.getId();

        //删除user表记录
        userRepository.delete(userId);

        //删除管理员表记录
        adminAuthRepository.delete(adminId);

    }

    /**
     * 超级管理员查看全部人员信息，分页,模糊
     *
     * @param pageable
     * @return
     */
    public Page<AdminAuthDto> findByName(Pageable pageable,String adminName) {
        Page<AdminAuthEntity> adminAuthEntityPage = adminAuthRepository.findByUserNameContaining(pageable,adminName);
        List<AdminAuthEntity> adminAuthEntityList = adminAuthEntityPage.getContent();

        List<AdminAuthDto> adminAuthDtoList = new ArrayList<>();
        for (int i = 0; i < adminAuthEntityList.size(); i++) {
            AdminAuthDto adminAuthDto = new AdminAuthDto();

            if (userRepository.findOne(adminAuthEntityList.get(i).getUserId()) != null) {
                String department = String.valueOf(departmentRepository.findOne(adminAuthEntityList.get(i).getDepartmentId()).getId());
                String username = adminAuthEntityList.get(i).getUserName();

                adminAuthDto.setId(adminAuthEntityList.get(i).getId());
                adminAuthDto.setUsername(username);
                adminAuthDto.setDepartment(department);

                adminAuthDtoList.add(adminAuthDto);
            }else{
                System.out.println(i);
            }
        }
        Page<AdminAuthDto> adminAuthDtoPage = new PageImpl<AdminAuthDto>(adminAuthDtoList, pageable, adminAuthEntityPage.getTotalElements());
        return adminAuthDtoPage;
    }
}
