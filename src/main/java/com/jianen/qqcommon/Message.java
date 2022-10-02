package com.jianen.qqcommon;

import java.io.Serializable;

/**
 * 表示客户端和服务器端通信时的消息对象
 */
public class Message  implements Serializable {
    private static final long serialVersionUID = 4125096758372084309L;
    private String  sender;//发送者
    private  String  getter;//接收者
    private  String  content;//消息内容
    private  String  sendTime;//发送时间
    private String  mesType ;//消息类型在接口中定义消息类型
    private byte[] filebytes; //文件对象字节
    private int fileLen=0;
    private  String dest;//文件传送到哪里
    private  String src;//源文件

    public byte[] getFilebytes() {
        return filebytes;
    }

    public void setFilebytes(byte[] filebytes) {
        this.filebytes = filebytes;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getMesType() {
        return mesType;
    }

    public void setMesType(String mesType) {
        this.mesType = mesType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
