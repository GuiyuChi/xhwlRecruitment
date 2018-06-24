package com.xhwl.recruitment.util;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午7:32 2018/6/16
 **/
public class client {
    public static void main(String[] args) {
        String a = MD5Util.md5Password("123456");
        System.out.println(a);
    }
}
