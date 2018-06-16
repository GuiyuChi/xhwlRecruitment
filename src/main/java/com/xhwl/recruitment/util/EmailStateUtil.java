package com.xhwl.recruitment.util;

/**
 * @Author: guiyu
 * @Description: 对邮件投递状态进行处理的工具类
 * @Date: Create in 下午7:21 2018/6/16
 **/
public class EmailStateUtil {
    private final static Integer ResumeReview = 0; //简历审核

    private final static Integer HRFristReview = 1; //HR初审

    private final static Integer DepartmentWrittenExamination = 2; //部门笔试

    private final static Integer DepartmentInterview = 3; //部门面试

    private final static Integer HRInterview = 4; //HR面试

    private final static Integer Pass = 5; //已通过

    private final static Integer Refuse = 6; //已回绝

    /**
     * 初始化邮件状态
     *
     * @return
     */
    public static String stateInit() {
        return "0000000";
    }

    /**
     * 发送邮件后改变邮件状态
     * @param oldState
     * @param step
     * @return
     */
    public static String sendChange(String oldState,Integer step) {
        StringBuilder newState = new StringBuilder(oldState);
        newState.setCharAt(step,'1');
        return String.valueOf(newState);
    }

    /**
     * 邮件状态的解析
     * @param state
     * @param step
     * @return
     */
    public static Boolean getEmailState(String state,Integer step){
        if(state.charAt(step)=='1'){
            return true;
        }
        else{
            return false;
        }
    }
}
