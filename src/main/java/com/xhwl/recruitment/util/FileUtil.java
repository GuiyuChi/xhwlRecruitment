package com.xhwl.recruitment.util;

import org.apache.commons.io.FileUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/**
 * @Author: guiyu
 * @Description: 文件处理工具
 * @Date: Create in 上午10:14 2018/4/18
 **/
public class FileUtil {
    private FileUtil() {

    }


    /**
     * 获取某个目录下的所有文件
     *
     * @param directoryPath 目标文件夹,结尾不需要加分号
     * @return
     */
    public static ArrayList<String> getAllFileNameInDirectory(String directoryPath) {
        File file = new File(directoryPath);
        if (file.exists()) {
            return new ArrayList<String>(Arrays.asList(file.list()));
        }
        return null;
    }

    /**
     * 复制文件至某个目录下
     *
     * @param srcFile    需要移动的文件
     * @param destFolder 目标文件夹路径,结尾不需要加/
     */
    public static void moveFile(File srcFile, File destFolder) {
        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }
        try {
            FileUtils.copyFileToDirectory(srcFile,destFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        srcFile.renameTo(new File(destFolder + "/" + srcFile.getName()));
    }


    public static void moveFile(String srcFile, String destFolder) {
        moveFile(new File(srcFile), new File(destFolder));
    }

    /**
     * 将某个文件夹下所有文件移动到另一个文件夹。如果目标文件夹不存在，则创建它。
     *
     * @param srcFolder  源文件夹。路径必须是绝对路径。
     * @param destFolder 目标文件夹。路径必须是绝对路径。
     */
    public static void moveFilesInFolder(File srcFolder, File destFolder) {
        ArrayList<String> files = getAllFileNameInDirectory(srcFolder.getAbsolutePath());

        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }

        files.forEach(e -> moveFile(new File(srcFolder.getAbsolutePath() + "/" + e), destFolder));
    }

    public static void moveFilesInFolder(String srcFolder, String destFolder) {
        moveFilesInFolder(new File(srcFolder), new File(destFolder));
    }

    /**
     * 将一个Multipart格式的文件保存到本地,文件名为上传文件的名字
     *
     * @param srcFile    源文件
     * @param destFolder 目标文件夹,路径结尾不需要加"/"
     * @return 保存的文件名
     * @throws IOException 目标文件夹未找到或者
     */
    public static String saveMultipartFileToLocal(MultipartFile srcFile, File destFolder) throws IOException {
        if (!destFolder.exists()) {
            destFolder.mkdirs();
        }
        //获取文件的后缀
        String filename = srcFile.getOriginalFilename();
        String type = filename.substring(filename.lastIndexOf('.')+1);

        String newName = getUUID()+"."+type;
        String outPath = destFolder.getAbsolutePath() + "/" + newName ;
        FileOutputStream out = new FileOutputStream(outPath);

        FileCopyUtils.copy(srcFile.getInputStream(), out);
        return newName;
    }

    public static String saveMultipartFileToLocal(MultipartFile srcFile, String destFolderPath) throws IOException {
        return saveMultipartFileToLocal(srcFile, new File(destFolderPath));
    }

    /**
     * 生成随机的UUID，用来给文件命名
     *
     * @return
     */
    private static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return UUID.randomUUID().toString();
    }

}
