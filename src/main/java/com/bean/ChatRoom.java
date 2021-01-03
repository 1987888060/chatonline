package com.bean;

import java.util.Date;

public class ChatRoom {
    int chatRoomId;
    int number;
    int userid;
    Date createAt;
    String introduce;
    String chatRoomName;

    public ChatRoom() {
    }

    public ChatRoom(int chatRoomId, int number, int userid, Date createAt, String introduce, String chatRoomName) {
        this.chatRoomId = chatRoomId;
        this.number = number;
        this.userid = userid;
        this.createAt = createAt;
        this.introduce = introduce;
        this.chatRoomName = chatRoomName;
    }

    public int getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(int chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getChatRoomName() {
        return chatRoomName;
    }

    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "chatRoomId=" + chatRoomId +
                ", number=" + number +
                ", userid=" + userid +
                ", createAt=" + createAt +
                ", introduce='" + introduce + '\'' +
                ", chatRoomName='" + chatRoomName + '\'' +
                '}';
    }
}
