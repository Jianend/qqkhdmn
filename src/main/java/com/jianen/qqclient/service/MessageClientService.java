package com.jianen.qqclient.service;

import com.jianen.qqcommon.Message;
import com.jianen.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 给对象提供消息相关的服务方法
 */
public class MessageClientService {
    /**
     * 私聊
     * @param content 内容
     * @param senderId  发送者
     * @param getterId  接收者
     * @throws IOException
     */
    public void sendMessageToOne(String content,String senderId,String getterId) throws IOException {
    //构建对象
        Message message = new Message();
        message.setSender(senderId);//发送者
        message.setGetter(getterId);//接收者
        message.setContent(content);//内容
        message.setSendTime(new Date().toString());//发送时间
        message.setMesType(MessageType.MESSAGE_COMM_MES);
        System.out.println(senderId+" 对 "+getterId+" 说 "+content);
        ObjectOutputStream oos  = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerYThread(senderId).getSocket().getOutputStream());
        oos.writeObject(message);
    }

    /**
     * 群发
     * @param content 内容
     * @param senderId 发送者
     */
    public void sendMessageToAll(String content,String senderId) throws IOException {
        Message message = new Message();
        message.setSender(senderId);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);//群发消息
        System.out.println(senderId+" 对大家说 "+content+" ");
        ObjectOutputStream oos  = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerYThread(senderId).getSocket().getOutputStream());
        oos.writeObject(message);
    }

}
