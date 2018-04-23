//package com.xhwl.recruitment.ServiceTest;//package com.xhwl.recruitment.ServiceTest;
//
//import com.xhwl.recruitment.domain.EducationExperienceEntity;
//import com.xhwl.recruitment.domain.TrainingExperienceEntity;
//import com.xhwl.recruitment.service.ResumeService;
//import com.xhwl.recruitment.vo.EducationExperenceVo;
//import com.xhwl.recruitment.vo.PersonalInformationVo;
//import com.xhwl.recruitment.vo.TrainingExperienceVo;
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author: guiyu
// * @Description:
// * @Date: Create in 下午6:26 2018/4/7
// **/
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class ResumeServiceTest {
//    @Autowired
//    ResumeService resumeService;
//
//    @Test
//    @Ignore
//    public void findResumeByUserId(){
//        System.out.println(resumeService.findResumeByUserId(2l));
//    }
//
//    @Test
//    @Ignore
//    public void addEducationExperenceTest(){
//        EducationExperenceVo educationExperenceVo = new EducationExperenceVo();
//        educationExperenceVo.setStartTime("2015-06-01");
//        educationExperenceVo.setEndTime("2019-09-01");
//        educationExperenceVo.setEducationHistory((byte)3);
//        educationExperenceVo.setSchool("福州大学");
//        educationExperenceVo.setSpeciality("计算机科学");
//        educationExperenceVo.setRank(30);
//
//        System.out.println(resumeService.addEducationExperence(1L,educationExperenceVo));
//    }
//
//    @Test
//    @Ignore
//    public void modifyEducationExperienceTest(){
//        EducationExperenceVo educationExperenceVo = new EducationExperenceVo();
//        educationExperenceVo.setId(5L);
//        educationExperenceVo.setStartTime("2014-06-01");
//        educationExperenceVo.setEndTime("2019-09-01");
//        educationExperenceVo.setEducationHistory((byte)3);
//        educationExperenceVo.setSchool("厦门大学");
//        educationExperenceVo.setSpeciality("软件工程");
//        educationExperenceVo.setRank(30);
//
//        System.out.println(resumeService.modifyEducationExperience(educationExperenceVo));
//    }
//
//    @Test
//    @Ignore
//    public void getEducationExperiencesTest(){
//        List<EducationExperienceEntity> educationExperienceEntities = new ArrayList<>();
//        educationExperienceEntities = resumeService.getEducationExperiences(1L);
//        for(EducationExperienceEntity educationExperienceEntity:educationExperienceEntities){
//            System.out.println(educationExperienceEntity);
//        }
//    }
//
//    @Test
//    @Ignore
//    public void addPersonalInformationTest(){
//        PersonalInformationVo personalInformationVo = new PersonalInformationVo();
//        personalInformationVo.setBirthday("1996-01-05");
//        personalInformationVo.setName("长孙无忌");
//        personalInformationVo.setSex(1);
//        personalInformationVo.setIdType(1);
//        personalInformationVo.setIdNumber("350124199601052343");
//        personalInformationVo.setEmail("32453424534@qq.com");
//        personalInformationVo.setTelephone("15980245342");
//        personalInformationVo.setMaritalStatus(1);
//        personalInformationVo.setWorkSeniority("5");
//        personalInformationVo.setPoliticalStatus("团员");
//        personalInformationVo.setPresentAddress("福建省厦门市思明区厦大学生公寓");
//        personalInformationVo.setPhotoPath("/down/1.jpg");
//
//        System.out.println(resumeService.addPersonalInformation(1L,personalInformationVo));
//    }
//
//    @Test
//    @Ignore
//    public void modifyPersonalInformationTest(){
//        PersonalInformationVo personalInformationVo = new PersonalInformationVo();
//        personalInformationVo.setId(3L);
//        personalInformationVo.setBirthday("1996-12-05");
//        personalInformationVo.setName("张三丰");
//        personalInformationVo.setSex(1);
//        personalInformationVo.setIdType(1);
//        personalInformationVo.setIdNumber("350124199601052343");
//        personalInformationVo.setEmail("32453424534@qq.com");
//        personalInformationVo.setTelephone("15980245342");
//        personalInformationVo.setMaritalStatus(1);
//        personalInformationVo.setWorkSeniority("5");
//        personalInformationVo.setPoliticalStatus("团员");
//        personalInformationVo.setPresentAddress("福建省厦门市思明区厦大学生公寓");
//        personalInformationVo.setPhotoPath("/down/1.jpg");
//
//        System.out.println(resumeService.modifyPersonalInformation(personalInformationVo));
//    }
//
//    @Test
//    @Ignore
//    public void getPersonalInformationTest(){
//        System.out.println(resumeService.getPersonalInformation(1L));
//    }
//
//
//    @Test
////    @Ignore
//    public void TrainingExperienceEntityTest(){
//        TrainingExperienceVo trainingExperienceVo = new TrainingExperienceVo();
//        trainingExperienceVo.setStartTime("2017-06-01");
//        trainingExperienceVo.setEndTime("2018-07-01");
//        trainingExperienceVo.setTrainingInstitutions("厦门x公司");
//        trainingExperienceVo.setTrainingContent("电商网站开发");
//        trainingExperienceVo.setDescription("进行开发和测试，负责后台的搭建");
//        resumeService.addTrainingExperience(1L,trainingExperienceVo);
//    }
//
//    @Test
//    @Ignore
//    public void getTrainExperiencesTest(){
//        List<TrainingExperienceEntity> trainingExperienceEntities = resumeService.getTrainExperiences(1L);
//        for(TrainingExperienceEntity trainingExperienceEntity : trainingExperienceEntities){
//            System.out.println(trainingExperienceEntity);
//        }
//    }
//
//    @Test
//    @Ignore
//    public void deleteTrainingExperienceTest(){
//        resumeService.deleteTrainingExperience(2L);
//    }
//}
