package com.xhwl.recruitment.Timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午6:24 2018/6/7
 **/
@Component
public class TestTimer {

    //    每分钟启动
    @Scheduled(cron = "0 50 19 * * ?")
    public void timerToNow(){
        System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

}
