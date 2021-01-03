package com.dao;

import com.bean.ChatRoom;
import com.bean.Information;
import com.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InformationDao {
    public List<Information> load(int chatRoomId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "select user.username,information.time,information.context from user,information \n" +
                "where information.chatRoomId = ? AND information.userId = user.userid";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,chatRoomId);
        rs = ps.executeQuery();

        List<Information> list = new ArrayList<>();
        while (rs.next()){
            Information information = new Information();
            information.setUser(rs.getString("user.username"));
            information.setContext(rs.getString("information.context"));
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String time = sdf.format(rs.getTimestamp("information.time"));
            information.setTimes(time);
            list.add(information);
        }

        DBUtil.close(conn,ps,rs);

        return list;
    }

    public void add(Information information) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "insert into information(chatRoomId,context,time,userId) values (?,?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,information.getChatRoomId());
        ps.setObject(2,information.getContext());
        ps.setObject(3,information.getTime());
        ps.setObject(4,information.getUserId());
        ps.execute();

        DBUtil.close(conn,ps,rs);
    }

}
