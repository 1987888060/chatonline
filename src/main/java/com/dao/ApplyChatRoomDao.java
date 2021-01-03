package com.dao;

import com.bean.ApplyChatRoom;
import com.bean.ApplyRoom;
import com.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplyChatRoomDao {
    public void add(int userid, String chatroomname, String introduce, String reason) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "INSERT INTO apply_chatroom (userId,name,introduce,reason) VALUES (?,?,?,?) ";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,userid);
        ps.setObject(2,chatroomname);
        ps.setObject(3,introduce);
        ps.setObject(4,reason);
        ps.execute();

        DBUtil.close(conn,ps,rs);
    }

    public boolean search(int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "SELECT * FROM apply_chatroom WHERE userId = ?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,userId);
        rs = ps.executeQuery();

        if(rs.next()){
            DBUtil.close(conn,ps,rs);
            return true;
        }else{
            DBUtil.close(conn,ps,rs);
            return false;
        }
    }

    public void delete(int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "DELETE FROM apply_chatroom WHERE userId = ?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,userId);
        ps.execute();

        DBUtil.close(conn,ps,rs);
    }

    public List<ApplyChatRoom> load() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }

        String sql = "SELECT apply_chatroom.*,user.username FROM apply_chatroom,user WHERE user.userid = apply_chatroom.userid";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        List<ApplyChatRoom> list = new ArrayList<>();
        while (rs.next()){
            ApplyChatRoom applyChatRoom = new ApplyChatRoom();
            applyChatRoom.setUsername(rs.getString("user.username"));
            applyChatRoom.setUserid(rs.getInt("apply_chatroom.userid"));
            applyChatRoom.setName(rs.getString("apply_chatroom.name"));
            applyChatRoom.setIntroduce(rs.getString("apply_chatroom.introduce"));
            applyChatRoom.setReason(rs.getString("apply_chatroom.reason"));
            list.add(applyChatRoom);
        }

        DBUtil.close(conn,ps,rs);

        return list;
    }
}
