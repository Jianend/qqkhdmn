package com.jianen.qqclient.service;

import com.jianen.qqcommon.Message;
import com.jianen.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * �������ṩ��Ϣ��صķ��񷽷�
 */
public class MessageClientService {
    /**
     * ˽��
     * @param content ����
     * @param senderId  ������
     * @param getterId  ������
     * @throws IOException
     */
    public void sendMessageToOne(String content,String senderId,String getterId) throws IOException {
    //��������
        Message message = new Message();
        message.setSender(senderId);//������
        message.setGetter(getterId);//������
        message.setContent(content);//����
        message.setSendTime(new Date().toString());//����ʱ��
        message.setMesType(MessageType.MESSAGE_COMM_MES);
        System.out.println(senderId+" �� "+getterId+" ˵ "+content);
        ObjectOutputStream oos  = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerYThread(senderId).getSocket().getOutputStream());
        oos.writeObject(message);
    }

    /**
     * Ⱥ��
     * @param content ����
     * @param senderId ������
     */
    public void sendMessageToAll(String content,String senderId) throws IOException {
        Message message = new Message();
        message.setSender(senderId);
        message.setContent(content);
        message.setSendTime(new Date().toString());
        message.setMesType(MessageType.MESSAGE_TO_ALL_MES);//Ⱥ����Ϣ
        System.out.println(senderId+" �Դ��˵ "+content+" ");
        ObjectOutputStream oos  = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerYThread(senderId).getSocket().getOutputStream());
        oos.writeObject(message);
    }

}
