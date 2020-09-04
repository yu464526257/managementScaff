package com.yjc.system.commen.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Random;

/**
 * @description:
 * @author: zhucj
 * @date: 2019-08-07 9:34
 */
@Component
public class FileUploadUtil {

    private static String path;

    @Value("${file.path}")
    public  void setPath(String path) {
        FileUploadUtil.path = path;
    }





    /**
     * 上传图片至项目中,保存位置 resources下(量少可以用,数量大不太建议使用)
     * @param request
     * @param file 文件内容
     * @return 文件名字
     */
    public static String saveFile(HttpServletRequest request,
                                  MultipartFile file,String id) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                //保存服务器,注意在Linux系统和Windows系统,文件分隔符不通用
                //此处自动根据系统，选择不同分隔符 /static/upload/fileName
                String fileNewName=id+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                String filePath = FileUploadUtil.path  + File.separator + fileNewName;
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists()){
                    saveDir.getParentFile().mkdirs();
                }
                // 转存文件
                file.transferTo(saveDir);
                return filePath;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    /**
     * 上传文件到本地服务器磁盘
     * @param fileName 上传文件路径名称
     * E:/upload/img/12013212.jpg 
     * @param file
     * @return
     */
    public static String uploadFile(String fileName, MultipartFile file){
        //项目中保存在本地服务时  fileName  = 磁盘路径(注意区分系统) + 文件名称
        File  serverFile= new File(fileName);
        try {
            //判断文件是否已经存在
            if (serverFile.exists()) {
                throw new RuntimeException("文件已经存在");
            }
            //判断文件父目录是否存在
            if (!serverFile.getParentFile().exists()) {
                serverFile.getParentFile().mkdir();
            }
            //将上传的文件写入到服务器端文件内
            file.transferTo(serverFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return fileName;
    }


    /**
     * 文件下载
     * @param path 服务器文件地址
     * @param response
     * @return
     */
    public static int downloadFile(String path, HttpServletResponse response) {

        int result = 1;
        InputStream is = null;
        OutputStream os = null;
        try {
            File file = new File(path);
            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(file.getName(), "utf-8"));
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            is = new FileInputStream(file);
            os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) > 0) {
                os.write(b, 0, length);
            }
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            result = 0;
        } finally {
            closeInputStream(is);
            closeOutputStream(os);
        }

        return result;
    }

    private static void closeInputStream(InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeOutputStream(OutputStream os) {
        try {
            if (os != null) {
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成随机文件名
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        String str = millis + String.format("%03d", end3);

        return str;
    }



}