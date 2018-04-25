package com.xhwl.recruitment.controller;

import com.xhwl.recruitment.exception.MException;
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

    @GetMapping("/positions/{typeId}")
    public List<HashMap> getUnderwayPositionsByType(@RequestHeader HttpHeaders headers, @PathVariable("typeId") Integer typeId) {
        List<HashMap> res = positionService.getUnderwayPositions(typeId);
        return res;
    }

    @GetMapping("/position/{positionId}")
    public HashMap getPositionById(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {
        HashMap res = positionService.getPosition(positionId);
        return res;
    }

    /**
     * 用户投递岗位
     *
     * @param headers
     * @param positionId
     * @return
     */
    @PutMapping("/deliver/{positionId}")
    public Long deliver(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId) {

        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));
        if (!deliverService.checkResumeType(userId, positionId)) throw new MException("简历类型不符");

        //判断重复投递
        if (deliverService.isFirst(userId, positionId)) {
            Long id = deliverService.deliver(positionId, userId);
            return id;
        } else {
            throw new MException("重复投递");
        }
    }

    /**
     * 用户获取自己的投递信息
     *
     * @param headers
     * @return
     */
    @GetMapping("/deliver")
    public List<HashMap> findResumeDelivers(@RequestHeader HttpHeaders headers) {
        Long userId = userService.getUserIdByToken(headers.getFirst("authorization"));

        List<HashMap> res = deliverService.findResumeDelivers(userId);

        return res;

    }

    /**
     * 管理员（人事）添加和修改职位,前端判断全部非空
     *
     * @param headers
     * @param positionVo
     */
    @PostMapping("/admin/position")
    @RequiresPermissions(logical = Logical.AND, value = {"position"})
    public void publishPosition(@RequestHeader HttpHeaders headers, @RequestBody PositionVo positionVo) {
        positionService.addPosition(positionVo);
    }

    /**
     * 管理员（人事）获取岗位详情
     * @param positionId
     * @return
     */
    @GetMapping("/admin/position/{positionId}")
    @RequiresPermissions(logical = Logical.AND, value = {"position"})
    public HashMap adminGetPosition(@PathVariable("positionId") Long positionId){
        return positionService.adminGetPosition(positionId);
    }

    /**
     * 管理员（人事）获取全部处于招聘中的岗位
     * @return
     */
    @GetMapping("/admin/positions")
    @RequiresPermissions(logical = Logical.AND, value = {"position"})
    public List<HashMap> adminGetPositions(){
        return positionService.adminGetPositions();
    }

//    管理员（人事）获取全部处于招聘中的岗位

    /**
     * 管理员（人事）按ID删除岗位
     * @param positionId
     */
    @DeleteMapping("/admin/position/{positionId}")
    @RequiresPermissions(logical = Logical.AND, value = {"position"})
    public void deletePosition(@PathVariable("positionId") Long positionId){
        positionService.adminDeletePosition(positionId);
    }
}
