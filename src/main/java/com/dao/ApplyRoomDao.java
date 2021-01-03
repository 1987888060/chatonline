package com.dao;

import com.bean.ApplyRoom;
import com.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplyRoomDao {
    public void add(int chatRoomId, int applyId,String reason) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql1 = "SELECT userid FROM chatroom where chatRoomId = ?";
        ps = conn.prepareStatement(sql1);
        ps.setObject(1,chatRoomId);
        rs = ps.executeQuery();
        if(rs.next()){
            int userId = rs.getInt("userid");
            String sql = "insert into applyroom(userId,chatRoomId,applyId,reason) values (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,userId);
            ps.setObject(2,chatRoomId);
            ps.setObject(3,applyId);
            ps.setObject(4,reason);
            ps.execute();
        }else{
            //报错 预留处理
        }

        DBUtil.close(conn,ps,rs);
    }

    public List<ApplyRoom> search(int chatRoomId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "SELECT user.username,applyroom.reason,applyId FROM user,applyroom WHERE chatRoomId = ? AND user.userid = applyroom.applyId";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,chatRoomId);
        rs = ps.executeQuery();
        List<ApplyRoom> list = new ArrayList<>();
        while (rs.next()){
            ApplyRoom apply = new ApplyRoom();
            apply.setApplyId(rs.getInt("applyId"));
            apply.setReason(rs.getString("applyroom.reason"));
            apply.setUsername(rs.getString("user.username"));
            list.add(apply);
        }
        DBUtil.close(conn,ps,rs);
        return list;
    }

    public boolean search(int chatRoomId,int applyId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "SELECT * FROM applyroom WHERE applyId = ? AND chatroomId = ?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,applyId);
        ps.setObject(2,chatRoomId);
        rs = ps.executeQuery();

        if(rs.next()){
            DBUtil.close(conn,ps,rs);
            return true;
        }else{
            DBUtil.close(conn,ps,rs);
            return false;
        }
    }

    public void delete(int chatRoomId ,int applyId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "DELETE FROM applyroom WHERE chatRoomId = ? AND applyId = ?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,chatRoomId);
        ps.setObject(2,applyId);
        ps.execute();

        DBUtil.close(conn,ps,rs);
    }
}
