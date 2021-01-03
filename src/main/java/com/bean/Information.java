package com.bean;

import java.util.Date;

public class Information {
    private String context;
    private Date time;
    private String user;
    private String times;
    private int chatRoomId;
    private int userId;

    public int getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(int chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Information() {
    }

    public Information(String context, Date time, String user, String times) {
        this.context = context;
        this.time = time;
        this.user = user;
        this.times = times;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Information{" +
                "context='" + context + '\'' +
                ", time=" + time +
                ", user='" + user + '\'' +
                ", times='" + times + '\'' +
                ", chatRoomId=" + chatRoomId +
                ", userId=" + userId +
                '}';
    }
}
