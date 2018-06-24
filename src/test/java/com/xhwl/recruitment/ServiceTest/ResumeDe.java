//package com.xhwl.recruitment.ServiceTest;
//
//        import com.xhwl.recruitment.dao.ResumeDeliverRepository;
//        import com.xhwl.recruitment.domain.ResumeDeliverEntity;
//        import com.xhwl.recruitment.util.EmailStateUtil;
//        import org.junit.Test;
//        import org.junit.runner.RunWith;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.boot.test.context.SpringBootTest;
//        import org.springframework.test.context.junit4.SpringRunner;
//
//        import java.util.List;
//
///**
// * @Author: guiyu
// * @Description:
// * @Date: Create in 下午7:42 2018/6/16
// **/
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class ResumeDe {
//    @Autowired
//    ResumeDeliverRepository resumeDeliverRepository;
//
//    @Test
//    public void cleanDB(){
//        List<ResumeDeliverEntity> resumeDeliverEntities =resumeDeliverRepository.findAll();
//        for(ResumeDeliverEntity deliverEntity :resumeDeliverEntities){
//            String old = deliverEntity.getEmailState();
//            deliverEntity.setReadFlag(0);
//            resumeDeliverRepository.save(deliverEntity);
//        }
//    }
//}
