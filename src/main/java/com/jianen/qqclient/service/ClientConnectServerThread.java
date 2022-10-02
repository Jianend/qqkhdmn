package com.jianen.qqclient.service;

import com.jianen.qqcommon.Message;
import com.jianen.qqcommon.MessageType;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {

    //该线程需要持有Socker
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
        //因为我们线程需要在后台和服务器通信 因此我们while循环
        while (fool) {
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

                    System.out.println("\n===当前在线用户列表===");
//                    System.out.println(o.getContent());
                    for (int i = 0; i < s.length; i++) {
                        System.out.println("用户：  " + s[i]);
                    }
                } else if (o.getMesType().equals(MessageType.MESSAGE_COMM_MES)) {
                    System.out.println("\n"+o.getSender()+": "+o.getContent()+"\n"+o.getSendTime());
                }else if (o.getMesType().equals(MessageType.MESSAGE_TO_ALL_MES)){
                    System.out.println("\n"+o.getSender()+"对大家说"+o.getContent());
                }else if (o.getMesType().equals(MessageType.MESSAGE_TO_FILE_MES)){
                    BufferedOutputStream bos  = new BufferedOutputStream(new FileOutputStream(o.getDest()));
                    System.out.println(o.getSender()+"发送给"+o.getGetter()+"到"+o.getDest());
                    bos.write(o.getFilebytes());
                    bos.close();
                    System.out.println("保存文件成功");
                }

                else {
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
