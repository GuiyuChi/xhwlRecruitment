package com.xhwl.recruitment.util;

import java.util.UUID;

/**
 * @Author: guiyu
 * @Description:
 * @Date: Create in 下午7:26 2018/5/4
 **/
public class UUIDUtil {
    /**
     * 生成随机的UUID，用来给文件命名
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return UUID.randomUUID().toString();
    }
}
