package com.dao;

import com.bean.Information;
import com.bean.PInfo;
import com.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PInformationDao {
    public List<PInfo> load(int sendId,int accptId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "SELECT user.username,p_information.text FROM user,p_information WHERE (user.userid = p_information.userid1 and p_information.userid2 = ? ) OR(user.userid = p_information.userid1 and p_information.userid2 = ? )";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,sendId);
        ps.setObject(2,accptId);
        rs = ps.executeQuery();

        List<PInfo> list = new ArrayList<>();
        while (rs.next()){
            PInfo info = new PInfo();
            info.setSender(rs.getString("user.username"));
            info.setText(rs.getString("p_information.text"));
            list.add(info);
        }
        DBUtil.close(conn,ps,rs);

        return list;
    }

    public void add(int sendId,int accepterId,String text) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "INSERT INTO p_information(userid1,userid2,text) VALUES(?,?,?)";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,sendId);
        ps.setObject(2,accepterId);
        ps.setObject(3,text);
        ps.execute();
        DBUtil.close(conn,ps,rs);
    }

    public void delete(int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }
        String sql = "DELETE FROM p_information WHERE userid1 = ? OR userid2 = ?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,userId);
        ps.setObject(2,userId);
        ps.execute();
        DBUtil.close(conn,ps,rs);
    }

}
