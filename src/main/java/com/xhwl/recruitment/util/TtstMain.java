package com.xhwl.recruitment.util;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午6:04 2018/5/1
 **/
public class TtstMain {
    public static void main(String[] args) {
        System.out.println(StatusCodeUtil.codeAnalysis("11112"));
        System.out.println(StatusCodeUtil.codeChange("11112",2));
        System.out.println(StatusCodeUtil.initCode());
        System.out.println(StatusCodeUtil.code2View("11100"));
    }
}
