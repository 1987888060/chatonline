package com.dao;

import com.bean.Manager;
import com.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDao {
    public Manager login(Manager manager) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }

        String sql = "select * from manager where account =? and password = ?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1, manager.getAccount());
        ps.setObject(2, manager.getPassword());
        rs = ps.executeQuery();

        int count = 0;

        while (rs.next()){
            manager.setID(rs.getInt("managerId"));
            count++;
        }

        DBUtil.close(conn,ps,rs);
        if(count > 1){
            //查询到两个以上的数据 报错
            manager = null;
        }else if(count < 1 ){
            //没有查到 返回null
            manager = null;
        }

        return manager;
    }
}
