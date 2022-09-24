package com.jianen.qqclient.view;

import com.jianen.qqclient.service.UserClientService;
import com.jianen.qqclient.utils.Utility;

import java.io.IOException;

/**
 * 客户端的菜单界面
 */
public class qqview {
    private  boolean loop =true;//控制是否显示菜单
    private  String key="";//接收用户的键盘输入

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new qqview().mainMenu();
        System.out.println("退出系统");
    }



    //显示主菜单
    private void mainMenu() throws IOException, ClassNotFoundException {
        while (loop){
            System.out.println("===============欢迎登录网络通信系统===============");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.print("请输入你的选择:");
            key= Utility.readString(1);
            switch (key){
                case "1":
                    System.out.print("请输入用户号：");
                    String userId =Utility.readString(50);
                    System.out.print("请输入密码");
                    String pwd = Utility.readString(50);
                    UserClientService ucs  = new UserClientService();//对象用于登录服务/注册用户
//到服务器验证用户是否合法
                    //我们编写一个类UserClientService 用户验证



                    if (ucs.checkUser(userId,pwd)){//先写逻辑
                        //登录成功
                        System.out.println("===============欢迎(用户"+userId+")===============");
                        //进入二级菜单
                        while (loop){
                            System.out.println("\n===========网络通信系统二级菜单用户"+userId);
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.print("请输入你的选择");
                            key = Utility.readString(1);
                            switch (key){
                                case "1":
                                    //写一个方法
                                    ucs.onlineFriendList();
                                    System.out.println("显示在线用户列表");
                                    break;
                                case "2":
                                    System.out.println("2 群发消息");
                                    break;
                                case "3":
                                    System.out.println("\3 私聊消息");
                                    break;
                                case "4":
                                    System.out.println("4 发送文件");
                                    break;
                                case "9":
                                    System.out.println("5 退出系统");
                                    loop=false;
                                    break;

                            }
                        }
                    }else {
                        //登录失败
                        System.out.println("登录失败");
                    }


                    break;
                case "9":
                    loop=false;//退出系统
                    break;
            }
        }
    }
}
