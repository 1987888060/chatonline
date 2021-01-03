package com.bean;

import java.util.Date;

public class User {
    private int userid;
    private String username;
    private String account;
    private String password;
    private Date createAt;
    private Date modifiedAt;

    public  User() {
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public User(String account, String password, String username, Date date) {
        this.account = account;
        this.password = password;
        this.username = username;
        this.createAt = date;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", createAt=" + createAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
