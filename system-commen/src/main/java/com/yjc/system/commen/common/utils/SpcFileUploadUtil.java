package com.yjc.system.commen.common.utils;/*
 * 创建者 ：于峻成
 * 创建时间 ：2020/6/30
 * 所属功能
 */

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SCPOutputStream;
import com.jcraft.jsch.*;
import com.yjc.system.commen.common.file.entity.FileResultEntity;
import com.yjc.system.commen.common.file.entity.ScpConnectEntity;
import com.yjc.system.commen.common.file.entity.FileResultEntity;
import com.yjc.system.commen.common.file.entity.ScpConnectEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@Getter
public class SpcFileUploadUtil{


    public static  String url;

    public static String passWord;

    public static String userName;

    public static String path;

    public static long upload_maxsize ;



    @Value("${remoteServer.url}")
    public void setUrl(String url) {
        this.url = url;
    }
    @Value("${remoteServer.password}")
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    @Value("${remoteServer.username}")
    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Value("${remoteServer.path}")
    public  void setPath(String path) {
        this.path = path;
    }
    @Value("${remoteServer.upload_maxsize}")
    public  void setUpload_maxsize(long upload_maxsize) {
        SpcFileUploadUtil.upload_maxsize = upload_maxsize;
    }

    @Async
    public FileResultEntity uploadFile(MultipartFile file, String targetPath, String remoteFileName) throws Exception{
        ScpConnectEntity scpConnectEntity=new ScpConnectEntity();
        scpConnectEntity.setTargetPath(targetPath);
        scpConnectEntity.setUrl(url);
        scpConnectEntity.setPassWord(passWord);
        scpConnectEntity.setUserName(userName);

        String code = null;
        String message = null;
        try {
            if (file == null) {
                throw new IllegalArgumentException("请确保上传文件不为空！");
            }
            if (file.getSize() >this.upload_maxsize) {
                throw new IllegalArgumentException("文件过大，最大为20mb。");
            }
            if(remoteFileName==null || "".equals(remoteFileName.trim())){
                throw new IllegalArgumentException("远程服务器新建文件名不能为空!");
            }
            remoteUploadFile(scpConnectEntity, file, remoteFileName);
            code = "ok";
            String imgUrlStr="http://"+ url+targetPath+remoteFileName;
            message =imgUrlStr.replace("/IMG","");
        } catch (IllegalArgumentException e) {
            code = "Exception";
            message = e.getMessage();
        } catch (JSchException e) {
            code = "Exception";
            message = e.getMessage();
        } catch (IOException e) {
            code = "Exception";
            message = e.getMessage();
        } catch (Exception e) {
            throw e;
        } catch (Error e) {
            code = "Error";
            message = e.getMessage();
        }
        return new FileResultEntity(code, message, null);
    }


    private void remoteUploadFile(ScpConnectEntity scpConnectEntity, MultipartFile file,
                                  String remoteFileName) throws JSchException, IOException {

        Connection connection = null;
        ch.ethz.ssh2.Session session = null;
        SCPOutputStream scpo = null;
        FileInputStream fis = null;

        try {
            createDir(scpConnectEntity);
        }catch (JSchException e) {
            throw e;
        }

        try {
            connection = new Connection(scpConnectEntity.getUrl());
            connection.connect();

            if(!connection.authenticateWithPassword(scpConnectEntity.getUserName(),scpConnectEntity.getPassWord())){
                throw new RuntimeException("SSH连接服务器失败");
            }
            session = connection.openSession();

            SCPClient scpClient = connection.createSCPClient();

            Long num=file.getSize();
            scpo = scpClient.put(remoteFileName, file.getSize(), scpConnectEntity.getTargetPath(), "0666");


            try {
                fis = (FileInputStream) file.getInputStream();
            }catch (Exception e){
                throw new IOException("SSH上传文件至服务器出错"+e.getMessage());
            }

            byte[] buf = new byte[1024];
            int hasMore = fis.read(buf);

            while(hasMore != -1){
                scpo.write(buf);
                hasMore = fis.read(buf);
            }
        } catch (IOException e) {
            throw new IOException("SSH上传文件至服务器出错"+e.getMessage());
        }finally {
            if(null != fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != scpo){
                try {
                    scpo.flush();
//                    scpo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != session){
                session.close();
            }
            if(null != connection){
                connection.close();
            }
        }
    }


    private  boolean createDir(ScpConnectEntity scpConnectEntity ) throws JSchException {

        JSch jsch = new JSch();
        Session sshSession = null;
        Channel channel= null;
        try {
            sshSession = jsch.getSession(scpConnectEntity.getUserName(), scpConnectEntity.getUrl(), 22);
            sshSession.setPassword(scpConnectEntity.getPassWord());
            sshSession.setConfig("StrictHostKeyChecking", "no");
            sshSession.connect();
            channel = sshSession.openChannel("sftp");
            channel.connect();
        } catch (JSchException e) {
            e.printStackTrace();
            throw new JSchException("SFTP连接服务器失败"+e.getMessage());
        }
        ChannelSftp channelSftp=(ChannelSftp) channel;
        if (isDirExist(scpConnectEntity.getTargetPath(),channelSftp)) {
            channel.disconnect();
            channelSftp.disconnect();
            sshSession.disconnect();
            return true;
        }else {
            String pathArry[] = scpConnectEntity.getTargetPath().split("/");
            StringBuffer filePath=new StringBuffer("/");
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                try {
                    if (isDirExist(filePath.toString(),channelSftp)) {
                        channelSftp.cd(filePath.toString());
                    } else {
                        // 建立目录
                        channelSftp.mkdir(filePath.toString());
                        // 进入并设置为当前目录
                        channelSftp.cd(filePath.toString());
                    }
                } catch (SftpException e) {
                    e.printStackTrace();
                    throw new JSchException("SFTP无法正常操作服务器"+e.getMessage());
                }
            }
        }
        channel.disconnect();
        channelSftp.disconnect();
        sshSession.disconnect();
        return true;
    }

    private  boolean isDirExist(String directory,ChannelSftp channelSftp) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = channelSftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

}
