package com.jianen.qqclient.service;

import com.jianen.qqcommon.Message;
import com.jianen.qqcommon.MessageType;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {

    //���߳���Ҫ����Socker
    private Socket socket;
    private boolean fool = true;

    public boolean isFool() {
        return fool;
    }

    public void setFool(boolean fool) {
        this.fool = fool;
    }

    @Override
    public void run() {
        //��Ϊ�����߳���Ҫ�ں�̨�ͷ�����ͨ�� �������whileѭ��
        while (fool) {
            System.out.println("�ͻ����߳� �ȴ��ӷ������˷��͵���Ϣ");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message o = (Message) ois.readObject();//���������û�з��Ͷ����̻߳���������

                //�ж϶�������
                //
                if (o.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)) {
                    //����Ƕ�ȡ���Ƿ��������ص������û��б�
                    //ȡ�������б���ʾ
                    String[] s = o.getContent().split(" ");//�ÿո�ָ�

                    System.out.println("\n===��ǰ�����û��б�===");
//                    System.out.println(o.getContent());
                    for (int i = 0; i < s.length; i++) {
                        System.out.println("�û���  " + s[i]);
                    }
                } else if (o.getMesType().equals(MessageType.MESSAGE_COMM_MES)) {
                    System.out.println("\n"+o.getSender()+": "+o.getContent()+"\n"+o.getSendTime());
                }else if (o.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)){
                    System.out.println("\n"+o.getSender()+"�Դ��˵"+o.getContent());
                }else if (o.getMesType().equals(MessageType.MESSAGE_TO_FILE_MES)){
                    BufferedOutputStream bos  = new BufferedOutputStream(new FileOutputStream(o.getDest()));
                    System.out.println(o.getSender()+"���͸�"+o.getGetter()+"��"+o.getDest());
                    bos.write(o.getFilebytes());
                    bos.close();
                    System.out.println("�����ļ��ɹ�");
                }

                else {
                    System.out.println("��������");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    //����������Socket����
    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
