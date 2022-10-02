package com.jianen.qqclient.service;

import com.jianen.qqcommon.Message;
import com.jianen.qqcommon.MessageType;

import java.io.*;

public class FileClientService {

    public   void  sendFileToOne(String src,String dest,String senderId,String getterId) {
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_TO_FILE_MES);
        message.setSender(senderId);
        message.setGetter(getterId);
        message.setSrc(src);
        message.setDest(dest);

        //文件读取
        BufferedInputStream bis=null;
        byte[] bytes;
        try {
           bis= new BufferedInputStream(new FileInputStream(src));
            bytes = new byte[(int) new File(src).length()];
            bis.read(bytes);
            message.setFilebytes(bytes);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (bis!=null){
                try {
                    bis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        System.out.println("\n " +senderId+"给"+getterId+"发送文件"+src +"到对方的目录"+dest);
        try {
            ObjectOutputStream oos  = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerYThread(senderId).getSocket().getOutputStream());
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
