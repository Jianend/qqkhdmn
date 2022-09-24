package com.jianen.qqclient.service;

import com.jianen.qqcommon.Message;
import com.jianen.qqcommon.MessageType;
import com.jianen.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 该类完成用户验证和用户注册功能
 */
public class UserClientService {
    private User u = new User();//可能在其他地方使用User信息
    Socket socket=null;

    /**
     * 根据userid 和pwd 到服务器验证用户是否合法
     *
     * @param userid 用户名
     * @param pwd    密码
     * @return
     */
    public boolean checkUser(String userid, String pwd) throws IOException, ClassNotFoundException {
        boolean b=false;//登录状态
        u.setUserid(userid);
        u.setPassword(pwd);
        socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(u);//发送对象



        //读取从服务器回复对象
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message ms =(Message) ois.readObject();
        if (ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) {
            //登录成功


            //创建一个和服务器端保持通信的线程 -》创建一个类ClientConnectServerThread
            ClientConnectServerThread cct = new ClientConnectServerThread(socket);
            cct.start();//启动客户端线程
            //为了后面扩展我们将线程放入集合中
            ManageClientConnectServerThread.addClientConnectServerThread(userid,cct);
            b=true;
        }
       else {
            //登录失败 我们不能启动线程
            socket.close();

        }
       return b;

    }

    //向服务器端请求在线用户列表
    public void onlineFriendList()  {
        //发送一个Message 类型 MESSAGE_GET_ONLINE_FRIEND
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        //发送给服务器
        //应该得到当前线程对应的ObjectOutputStream对象
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerYThread(u.getUserid()).getSocket().getOutputStream());//通过集合获取socket 在通过socket获取对象
            oos.writeObject(message);//发送对象


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
