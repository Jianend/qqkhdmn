package com.jianen.qqclient.service;

import com.jianen.qqcommon.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread{

    //该线程需要持有Socker
    private Socket socket;

    @Override
    public void run() {
        //因为我们线程需要在后台和服务器通信 因此我们while循环
        while (true){
            System.out.println("客户端线程 等待从服务器端发送的消息");
            try {
                ObjectInputStream ois  = new ObjectInputStream(socket.getInputStream());
                Message o =(Message) ois.readObject();//如果服务器没有发送对象线程会阻塞这里
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    //构造器接收Socket对象
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
