package com.xhwl.recruitment.ServiceTest;
import com.xhwl.recruitment.dao.DepartmentRepository;
import com.xhwl.recruitment.dao.HistoryPositionRepository;
import com.xhwl.recruitment.domain.DepartmentEntity;
import com.xhwl.recruitment.domain.PositionEntity;
import com.xhwl.recruitment.service.HistoryPositionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HistoryTest {
  @Autowired
  HistoryPositionService historyPositionService;
  @Test
   public void getLikePositionsTest() throws ParseException {
      Long departmentId=1L;
      Date publish_date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-05-01");
      Date end_date=new SimpleDateFormat("yyyy-MM-dd").parse("2018-06-01");
      Date local_date=new SimpleDateFormat("yyyy-MM-dd").parse("2018-06-02");
      String departmentName="部门";
      String positionName="测试";
      SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
      String str=sdf.format(publish_date);
      java.sql.Date date2 = java.sql.Date.valueOf(str);
      str=sdf.format(end_date);
      java.sql.Date date3 = java.sql.Date.valueOf(str);
      str=sdf.format(local_date);
      java.sql.Date date4 = java.sql.Date.valueOf(str);
      PageRequest request = new PageRequest(0, 20);
      Page<HashMap>m= historyPositionService.searchPositionAfterDeadline(request,departmentId,date2,date3,departmentName,positionName);
      System.out.println(m);

   }
}