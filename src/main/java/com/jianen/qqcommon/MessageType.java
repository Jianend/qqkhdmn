package com.jianen.qqcommon;

/**
 * ��ʾ��Ϣ����
 */
public interface MessageType {
    //�ڽӿ��ж���һЩ����
    //��ͬ������ֵ��ʾ��ͬ����Ϣ����
    String MESSAGE_LOGIN_SUCCEED="1";//��ʾ��¼�ɹ�
    String MESSAGE_LOGIN_FAIL="2";//��ʾ��¼ʧ��

    String MESSAGE_COMM_MES="3";//˽��
    String MESSAGE_TO_ALL_MES="7";//Ⱥ����Ϣ
    String MESSAGE_GET_ONLINE_FRIEND="4"; //Ҫ�󷵻������û��б�
    String MESSAGE_RET_ONLINE_FRIEND="5";//Ҫ�󷵻������û��б�
    String MESSAGE_CLIENT="6";//�ͻ��������˳�

}
