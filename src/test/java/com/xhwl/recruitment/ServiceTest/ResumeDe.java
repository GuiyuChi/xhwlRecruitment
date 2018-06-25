//package com.xhwl.recruitment.ServiceTest;
//
//        import com.xhwl.recruitment.dao.ResumeDeliverRepository;
//        import com.xhwl.recruitment.dao.UserRepository;
//        import com.xhwl.recruitment.domain.ResumeDeliverEntity;
//        import com.xhwl.recruitment.domain.UserEntity;
//        import com.xhwl.recruitment.util.EmailStateUtil;
//        import com.xhwl.recruitment.util.MD5Util;
//        import org.junit.Ignore;
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
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    @Ignore
//    public void cleanDB(){
//        List<ResumeDeliverEntity> resumeDeliverEntities =resumeDeliverRepository.findAll();
//        for(ResumeDeliverEntity deliverEntity :resumeDeliverEntities){
//            String old = deliverEntity.getEmailState();
//            deliverEntity.setReadFlag(0);
//            resumeDeliverRepository.save(deliverEntity);
//        }
//    }
//
//    @Test
//    public void changePassword(){
//        List<UserEntity> userEntities = userRepository.findAll();
//        for(UserEntity userEntity:userEntities){
//            String oldPassword = userEntity.getPassword();
//            userEntity.setPassword(MD5Util.md5Password(oldPassword));
//            userRepository.save(userEntity);
//        }
//
//
//    }
//
//}
