package com.xhwl.recruitment.util;//package com.xhwl.recruitment.util;
//import org.apache.commons.compress.archivers.ArchiveOutputStream;
//import org.apache.commons.compress.archivers.ArchiveStreamFactory;
//import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
//import org.apache.commons.compress.utils.IOUtils;
//
//import java.io.*;
//
///**
// * @Author: guiyu
// * @Description:
// * @Date: Create in 上午10:24 2018/4/18
// **/
//public class ZipUtil {
//
//    public static void doCompress(String srcFile, String zipFile) throws Exception {
//        doCompress(new File(srcFile), new File(zipFile));
//    }
//
//    /**
//     * 文件压缩
//     * @param srcFile  目录或者单个文件
//     * @param destFile 压缩后的ZIP文件
//     */
//    public static void doCompress(File srcFile, File destFile) throws Exception {
////        // 如果目标文件所在路径不存在，则创建文件夹
////        if(!destFile.exists()) {
////            destFile.mkdirs();
////        }
//
//        OutputStream out = new FileOutputStream(destFile);
//
//        try (ArchiveOutputStream os = new ArchiveStreamFactory().createArchiveOutputStream("zip", out)) {
//            //如果是文件夹,分别将每个文件加入zip中
//            if (srcFile.isDirectory()) {
//                File[] files = srcFile.listFiles();
//                for (File file : files) {
//                    doCompress(file, os);
//                }
//            } else {
//                doCompress(srcFile, os);
//            }
//            out.flush();
//
//        }
//    }
//
//    public static void doCompress(String pathname, ArchiveOutputStream os) throws IOException {
//        doCompress(new File(pathname), os);
//    }
//
//    /**
//     * 将单个文件加到zip压缩包中
//     * @param file 单个文件
//     * @param os zip输出流
//     */
//    public static void doCompress(File file, ArchiveOutputStream os) throws IOException{
//        if( file.exists() ){
////            byte[] buffer = new byte[BUFFER_SIZE];
////            FileInputStream fis = new FileInputStream(file);
////            // 为放入下一个文件作准备
////            out.putNextEntry(new ZipEntry(file.getName()));
////            int len = 0 ;
////            // 读取文件的内容,打包到zip文件
////            while ((len = fis.read(buffer)) > 0) {
////                out.write(buffer, 0, len);
////            }
////            // 输出缓冲区内容到zip文件中
////            out.flush();
////            out.closeEntry();
////            fis.close();
//            // 用Commons Compress库压缩
//            os.putArchiveEntry(new ZipArchiveEntry(file.getName()));
//            IOUtils.copy(new FileInputStream(file),os);
//            os.closeArchiveEntry();
//        }
//    }
//}