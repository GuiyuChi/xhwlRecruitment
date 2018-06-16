package com.xhwl.recruitment.util;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午7:32 2018/6/16
 **/
public class client {
    public static void main(String[] args) {
        String one = EmailStateUtil.stateInit();
        String two = EmailStateUtil.sendChange(one,2);
        System.out.println(EmailStateUtil.getEmailState(two,1));
    }
}
