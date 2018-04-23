package com.xhwl.recruitment.controller;

import com.xhwl.recruitment.service.PositionService;
import com.xhwl.recruitment.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/positions/{typeId}")
    @RequiresAuthentication
    public List<HashMap> getUnderwayPositionsByType(@RequestHeader HttpHeaders headers, @PathVariable("typeId") Integer typeId) {
        List<HashMap> res = positionService.getUnderwayPositions(typeId);
        return res;
    }

    @GetMapping("/position/{positionId}")
    @RequiresAuthentication
    public HashMap getPositionById(@RequestHeader HttpHeaders headers, @PathVariable("positionId") Long positionId){
        HashMap res = positionService.getPosition(positionId);
        return res;
    }
}
