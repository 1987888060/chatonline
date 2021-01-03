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

public class ChatRoomDao {
    public List<ChatRoom> load() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }

        String sql = "select * from chatroom";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        List<ChatRoom> list = new ArrayList<>();
        while (rs.next()){
            ChatRoom room = new ChatRoom();
            room.setChatRoomId(rs.getInt("chatRoomId"));
            room.setChatRoomName(rs.getString("chatRoomName"));
            room.setCreateAt(rs.getDate("createAt"));
            room.setNumber(rs.getInt("number"));
            room.setIntroduce(rs.getString("introduce"));
            list.add(room);
        }

        DBUtil.close(conn,ps,rs);

        return list;
    }

    public List<ChatRoom> search(int userid) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }

        String sql = "SELECT * FROM chatroom WHERE userid = ?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,userid);
        rs = ps.executeQuery();

        List<ChatRoom> list = new ArrayList<>();
        while (rs.next()){
            ChatRoom room = new ChatRoom();
            room.setChatRoomId(rs.getInt("chatRoomId"));
            room.setChatRoomName(rs.getString("chatRoomName"));
            list.add(room);
        }

        DBUtil.close(conn,ps,rs);

        return list;
    }

    public void add(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql1 = "SELECT * FROM apply_chatroom WHERE userid = ?";
        ps = conn.prepareStatement(sql1);
        ps.setObject(1,id);
        rs = ps.executeQuery();
        String introduce = null;
        String chatRoomName = null;
        while (rs.next()){
            introduce = rs.getString("introduce");
            chatRoomName = rs.getString("name");
        }

        String sql = "INSERT INTO chatroom(number,userid,createAt,introduce,chatRoomName) VALUES(1,?,?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,id);
        ps.setObject(2,new Date(System.currentTimeMillis()));
        ps.setObject(3,introduce);
        ps.setObject(4,chatRoomName);
        ps.execute();

        String sql2 = "SELECT chatroomId FROM chatroom WHERE userid = ? and chatroomId NOT IN\n" +
                "(SELECT chatroomId FROM user_chatroom WHERE userId = ?)";
        ps = conn.prepareStatement(sql2);
        ps.setObject(1,id);
        ps.setObject(2,id);
        rs = ps.executeQuery();
        PersonRoomListDao dao = new PersonRoomListDao();
        while(rs.next()){
            try {
                dao.add(rs.getInt("chatRoomId"),id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        DBUtil.close(conn,ps,rs);
    }

    public void update(int chatRoomId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }

        String sql = "UPDATE chatroom SET number = number + 1  WHERE chatRoomId = ?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,chatRoomId);
        ps.execute();
        DBUtil.close(conn,ps,rs);
    }
}
