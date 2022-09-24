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
 * ��������û���֤���û�ע�Ṧ��
 */
public class UserClientService {
    private User u = new User();//�����������ط�ʹ��User��Ϣ
    Socket socket=null;

    /**
     * ����userid ��pwd ����������֤�û��Ƿ�Ϸ�
     *
     * @param userid �û���
     * @param pwd    ����
     * @return
     */
    public boolean checkUser(String userid, String pwd) throws IOException, ClassNotFoundException {
        boolean b=false;//��¼״̬
        u.setUserid(userid);
        u.setPassword(pwd);
        socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(u);//���Ͷ���



        //��ȡ�ӷ������ظ�����
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Message ms =(Message) ois.readObject();
        if (ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)) {
            //��¼�ɹ�


            //����һ���ͷ������˱���ͨ�ŵ��߳� -������һ����ClientConnectServerThread
            ClientConnectServerThread cct = new ClientConnectServerThread(socket);
            cct.start();//�����ͻ����߳�
            //Ϊ�˺�����չ���ǽ��̷߳��뼯����
            ManageClientConnectServerThread.addClientConnectServerThread(userid,cct);
            b=true;
        }
       else {
            //��¼ʧ�� ���ǲ��������߳�
            socket.close();

        }
       return b;

    }

    //������������������û��б�
    public void onlineFriendList()  {
        //����һ��Message ���� MESSAGE_GET_ONLINE_FRIEND
        Message message = new Message();
        message.setMesType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        //���͸�������
        //Ӧ�õõ���ǰ�̶߳�Ӧ��ObjectOutputStream����
        try {
            ObjectOutputStream oos = new ObjectOutputStream
                    (ManageClientConnectServerThread.getClientConnectServerYThread(u.getUserid()).getSocket().getOutputStream());//ͨ�����ϻ�ȡsocket ��ͨ��socket��ȡ����
            oos.writeObject(message);//���Ͷ���


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
