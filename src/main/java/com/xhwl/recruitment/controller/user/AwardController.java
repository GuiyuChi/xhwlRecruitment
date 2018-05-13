package com.xhwl.recruitment.controller.user;

import com.xhwl.recruitment.domain.AwardEntity;
import com.xhwl.recruitment.exception.MException;
import com.xhwl.recruitment.exception.MyNoPermissionException;
import com.xhwl.recruitment.service.PermissionService;
import com.xhwl.recruitment.service.ResumeService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.vo.AwardVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 上午12:51 2018/4/15
 **/
@RestController
public class AwardController {
    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;

    @Autowired
    PermissionService permissionService;

    @GetMapping("/award")
    @RequiresAuthentication
    public List<AwardEntity> getAwards(@RequestHeader HttpHeaders headers) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        return resumeService.getAward(userId);
    }

    @PostMapping("/award")
    @RequiresAuthentication
    public AwardEntity changeAward(@RequestHeader HttpHeaders headers, @RequestBody AwardVo awardVo) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if (awardVo.getId() == null) {
            return resumeService.addAward(userId, awardVo);
        } else {
            if(permissionService.awardPermission(userId,awardVo.getId())){
                return resumeService.modifyAward(awardVo);
            }else{
                throw new MyNoPermissionException("无修改权限");
            }
        }
    }

    @DeleteMapping("/award/{id}")
    @RequiresAuthentication
    public void deleteAward(@RequestHeader HttpHeaders headers,@PathVariable("id") Long awardId){
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if(permissionService.awardPermission(userId,awardId)){
            resumeService.deleteAward(awardId);
        }else{
            throw new MyNoPermissionException("无修改权限");
        }
    }
}
