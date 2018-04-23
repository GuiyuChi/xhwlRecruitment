//package com.xhwl.recruitment.ServiceTest;
//
//import com.xhwl.recruitment.service.DeliverService;
//import com.xhwl.recruitment.service.PositionService;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.HashMap;
//
///**
// * @Author: guiyu
// * @Description:
// * @Date: Create in 下午5:22 2018/4/22
// **/
//@SpringBootTest
//@RunWith(SpringRunner.class)
//@Transactional
//public class DeliverServiceTest {
//    @Autowired
//    DeliverService deliverService;
//    @Autowired
//    PositionService positionService;
//
//    @Test
//    @Ignore
//    public void copyDatabaseTest(){
//        HashMap hashMap = deliverService.copyDocument();
//        deliverService.copyDatabase(4L,hashMap);
//    }
//
//    @Test
//    @Ignore
//    public void getUnderwayPositionTest(){
//        positionService.getUnderwayPositions(1);
//    }
//
//    @Test
//    public void deliverTest(){
//        deliverService.deliver(1L,4L);
//    }
//}
