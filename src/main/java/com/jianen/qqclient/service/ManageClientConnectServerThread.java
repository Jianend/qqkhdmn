package com.jianen.qqclient.service;

import java.util.HashMap;

/**
 * ����ͻ������ӵ�����˵��̵߳���
 */
public class ManageClientConnectServerThread {

    private static HashMap<String,ClientConnectServerThread> hm= new HashMap<>();

    //��ĳ���̼߳��뼯����
    public static void addClientConnectServerThread(String userid,ClientConnectServerThread clientConnectServerThread){
        hm.put(userid,clientConnectServerThread);
    }
    //ͨ��userid ���Եõ���Ӧ�߳�
    public static ClientConnectServerThread getClientConnectServerYThread(String userid){
        return  hm.get(userid);
    }

}
