package com.bean;

import java.io.Serializable;

public class ApplyChatRoom implements Serializable {
    private Integer userid;
    private String name;
    private String introduce;
    private String reason;
    private String username;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ApplyChatRoom{" +
                "userid=" + userid +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", reason='" + reason + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public ApplyChatRoom() {
    }
}
