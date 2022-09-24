package com.jianen.qqclient.service;

import java.util.HashMap;

/**
 * 管理客户端连接到服务端的线程的类
 */
public class ManageClientConnectServerThread {

    private static HashMap<String,ClientConnectServerThread> hm= new HashMap<>();

    //将某给线程加入集合中
    public static void addClientConnectServerThread(String userid,ClientConnectServerThread clientConnectServerThread){
        hm.put(userid,clientConnectServerThread);
    }
    //通过userid 可以得到对应线程
    public static ClientConnectServerThread getClientConnectServerYThread(String userid){
        return  hm.get(userid);
    }

}
