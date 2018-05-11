package com.xhwl.recruitment.controller.user;

import com.xhwl.recruitment.dao.EducationRepository;
import com.xhwl.recruitment.dao.JobIntentionRepository;
import com.xhwl.recruitment.dao.PersonalInformationRepository;
import com.xhwl.recruitment.dao.ResumeRepository;
import com.xhwl.recruitment.domain.ResumeEntity;
import com.xhwl.recruitment.exception.*;
import com.xhwl.recruitment.service.DeliverService;
import com.xhwl.recruitment.service.PositionService;
import com.xhwl.recruitment.service.UserService;
import com.xhwl.recruitment.vo.PositionVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午1:05 2018/4/23
 **/
@RestController
public class PositionController {
    @Autowired
    UserService userService;

    @Autowired
    PositionService positionService;

    @Autowired
    DeliverService deliverService;

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    PersonalInformationRepository personalInformationRepository;

    @Autowired
    EducationRepository educationRepository;

    @Autowired
    JobIntentionRepository jobIntentionRepository;

    /**
     * 用户按类型获取所有岗位的信息
     * typeId 招聘类型 1校招 2社招 3实习生
     * workPlace 工作地点
     * positionType 职位类型 类似于 研发 设计...
     * positionName 职位名称
     *
     * @param headers
     * @param typeId
     * @return
     */
    @GetMapping("/positions/{typeId}")
    public List<HashMap> getUnderwayPositionsByType(@RequestHeader HttpHeaders headers, @PathVariable("typeId") Integer typeId,
                                                    @RequestParam(value="workPlace", required = false, defaultValue = "") String workPlace,
                                                    @RequestParam(value="positionType", required = false, defaultValue = "") String positionType,
                                                    @RequestParam(value="positionName", required = false, defaultValue = "") String positionName) {
        List<HashMap> res = positionService.getLikePositions(workPlace,positionName,positionType,typeId);
        return res;
    }

    /**
     * 用户获取岗位详情
     *
     * @param headers
     * @param positionId
     * @return
     */
    @GetMapping("/position/{positionId}")
    public HashMap getPositionById(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {
        HashMap res = positionService.getPosition(positionId);
        return res;
    }


}
