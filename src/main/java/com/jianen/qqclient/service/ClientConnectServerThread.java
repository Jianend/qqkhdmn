package com.jianen.qqclient.service;

import com.jianen.qqcommon.Message;
import com.jianen.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {

    //���߳���Ҫ����Socker
    private Socket socket;

    @Override
    public void run() {
        //��Ϊ�����߳���Ҫ�ں�̨�ͷ�����ͨ�� �������whileѭ��
        while (true) {
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
                    System.out.println("===��ǰ�����û��б�===");
                    for (int i = 0; i < s.length; i++) {
                        System.out.println("�û���"+s);
                    }
                } else {
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
