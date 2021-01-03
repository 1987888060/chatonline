package com.dao;

import com.bean.ChatRoom;
import com.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonRoomListDao {
    public List<ChatRoom> load(int userid) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "select * from chatroom where chatRoomId in\n" +
                "(SELECT chatRoomId FROM user_chatroom where userId = ?)";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,userid);
        rs = ps.executeQuery();

        List<ChatRoom> list = new ArrayList<>();
        while (rs.next()){
            ChatRoom room = new ChatRoom();
            room.setChatRoomId(rs.getInt("chatRoomId"));
            room.setChatRoomName(rs.getString("chatRoomName"));
            /*room.setCreateAt(rs.getDate("createAt"));
            room.setNumber(rs.getInt("number"));
            room.setIntroduce(rs.getString("introduce"));*/
            list.add(room);
        }

        DBUtil.close(conn,ps,rs);

        return list;
    }
    public boolean search(int chatRoomId,int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "SELECT * FROM user_chatroom WHERE userId = ? AND chatroomId = ?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,userId);
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

    public void add(int chatRoomId,int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "INSERT into user_chatroom(userId,chatRoomId,time) VALUES (?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,userId);
        ps.setObject(2,chatRoomId);
        ps.setObject(3,new Date());
        ps.execute();
        DBUtil.close(conn,ps,rs);

        ChatRoomDao dao = new ChatRoomDao();
        dao.update(chatRoomId);
    }

    public void delete(int chatRoomId,int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "DELETE from user_chatroom WHERE userId = ? AND chatroomId = ?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,userId);
        ps.setObject(2,chatRoomId);
        ps.execute();

        DBUtil.close(conn,ps,rs);
    }
}
