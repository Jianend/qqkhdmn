package com.jianen.qqclient.view;

import com.jianen.qqclient.service.UserClientService;
import com.jianen.qqclient.utils.Utility;

import java.io.IOException;

/**
 * �ͻ��˵Ĳ˵�����
 */
public class qqview {
    private  boolean loop =true;//�����Ƿ���ʾ�˵�
    private  String key="";//�����û��ļ�������

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new qqview().mainMenu();
        System.out.println("�˳�ϵͳ");
    }



    //��ʾ���˵�
    private void mainMenu() throws IOException, ClassNotFoundException {
        while (loop){
            System.out.println("===============��ӭ��¼����ͨ��ϵͳ===============");
            System.out.println("\t\t 1 ��¼ϵͳ");
            System.out.println("\t\t 9 �˳�ϵͳ");
            System.out.print("���������ѡ��:");
            key= Utility.readString(1);
            switch (key){
                case "1":
                    System.out.print("�������û��ţ�");
                    String userId =Utility.readString(50);
                    System.out.print("����������");
                    String pwd = Utility.readString(50);
                    UserClientService ucs  = new UserClientService();//�������ڵ�¼����/ע���û�
//����������֤�û��Ƿ�Ϸ�
                    //���Ǳ�дһ����UserClientService �û���֤



                    if (ucs.checkUser(userId,pwd)){//��д�߼�
                        //��¼�ɹ�
                        System.out.println("===============��ӭ(�û�"+userId+")===============");
                        //��������˵�
                        while (loop){
                            System.out.println("\n===========����ͨ��ϵͳ�����˵��û�"+userId);
                            System.out.println("\t\t 1 ��ʾ�����û��б�");
                            System.out.println("\t\t 2 Ⱥ����Ϣ");
                            System.out.println("\t\t 3 ˽����Ϣ");
                            System.out.println("\t\t 4 �����ļ�");
                            System.out.println("\t\t 9 �˳�ϵͳ");
                            System.out.print("���������ѡ��");
                            key = Utility.readString(1);
                            switch (key){
                                case "1":
                                    //дһ������
                                    ucs.onlineFriendList();
                                    System.out.println("��ʾ�����û��б�");
                                    break;
                                case "2":
                                    System.out.println("2 Ⱥ����Ϣ");
                                    break;
                                case "3":
                                    System.out.println("\3 ˽����Ϣ");
                                    break;
                                case "4":
                                    System.out.println("4 �����ļ�");
                                    break;
                                case "9":
                                    System.out.println("5 �˳�ϵͳ");
                                    loop=false;
                                    break;

                            }
                        }
                    }else {
                        //��¼ʧ��
                        System.out.println("��¼ʧ��");
                    }


                    break;
                case "9":
                    loop=false;//�˳�ϵͳ
                    break;
            }
        }
    }
}
