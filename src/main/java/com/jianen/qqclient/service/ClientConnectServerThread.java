package com.jianen.qqclient.service;

import com.jianen.qqcommon.Message;
import com.jianen.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {

    //该线程需要持有Socker
    private Socket socket;

    @Override
    public void run() {
        //因为我们线程需要在后台和服务器通信 因此我们while循环
        while (true) {
            System.out.println("客户端线程 等待从服务器端发送的消息");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message o = (Message) ois.readObject();//如果服务器没有发送对象线程会阻塞这里

                //判断对象类型
                //
                if (o.getMesType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)) {
                    //如果是读取的是服务器返回的在线用户列表
                    //取出在线列表并显示
                    String[] s = o.getContent().split(" ");//用空格分割
                    System.out.println("===当前在线用户列表===");
                    for (int i = 0; i < s.length; i++) {
                        System.out.println("用户："+s);
                    }
                } else {
                    System.out.println("其他类型");
                }
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
