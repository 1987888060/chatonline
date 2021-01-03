package com.bean;

public class Manager {
    private String account;
    private String password;
    private int ID;
    public Manager(String account, String password) {
        this.account = account;
        this.password = password;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", ID=" + ID +
                '}';
    }
}
