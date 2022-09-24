package com.jianen.qqclient.service;

import com.jianen.qqcommon.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread{

    //���߳���Ҫ����Socker
    private Socket socket;

    @Override
    public void run() {
        //��Ϊ�����߳���Ҫ�ں�̨�ͷ�����ͨ�� �������whileѭ��
        while (true){
            System.out.println("�ͻ����߳� �ȴ��ӷ������˷��͵���Ϣ");
            try {
                ObjectInputStream ois  = new ObjectInputStream(socket.getInputStream());
                Message o =(Message) ois.readObject();//���������û�з��Ͷ����̻߳���������
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
