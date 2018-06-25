//package com.xhwl.recruitment.redis;
//
//import com.xhwl.recruitment.dao.AdminAuthRepository;
//import com.xhwl.recruitment.dao.DepartmentRepository;
//import com.xhwl.recruitment.dao.PositionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Service;
//
///**
// * @Author: guiyu
// * @Description: 用于操作投递记录相关的缓存数据
// * @Date: Create in 上午9:57 2018/5/26
// **/
//@Service
//public class DeliverRedis {
//    @Autowired
//    RedisTemplate redisTemplate;
//
//    @Autowired
//    PositionRepository positionRepository;
//
//    @Autowired
//    AdminAuthRepository adminAuthRepository;
//
//    @Autowired
//    DepartmentRepository departmentRepository;
//
//    /**
//     * 设置某投递记录的阅读状态为 未读
//     *
//     * @param deliverId
//     */
//    public void setDeliverUnread(Long deliverId) {
//        ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
//        String key = "Deliver_" + deliverId.toString() + "_ReadFlag";
//        operations.set(key, 0);
//    }
//
//    /**
//     * 设置某投递记录的阅读状态为 已读
//     *
//     * @param deliverId
//     */
//    public void setDeliverRead(Long deliverId) {
//        ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
//        String key = "Deliver_" + deliverId.toString() + "_ReadFlag";
//        operations.set(key, 1);
//    }
//
//    /**
//     * 获取某投递记录的阅读状态 0未读 1已读
//     *
//     * @param deliverId
//     * @return
//     */
//    public int getDeliverReadFlag(Long deliverId) {
//        ValueOperations<String, Integer> operations = redisTemplate.opsForValue();
//        String key = "Deliver_" + deliverId.toString() + "_ReadFlag";
//        return operations.get(key);
//    }
//
//    /**
//     * 根据部门设置某投递的阅读状态为未读，对某部门的所有员工设为未读
//     * 投递成功时调用
//     */
//    public void setDeliverUnreadByDepartment(Long deliverId,Long departmentId){}
//
//
//}
