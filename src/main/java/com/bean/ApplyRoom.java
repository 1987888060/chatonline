package com.bean;

//申请进入房间
public class ApplyRoom {
    private String username;
    private int applyId;
    private String reason;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ApplyRoom{" +
                "username='" + username + '\'' +
                ", applyId=" + applyId +
                ", reason='" + reason + '\'' +
                '}';
    }
}
