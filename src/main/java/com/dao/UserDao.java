package com.dao;

import com.bean.ChatRoom;
import com.bean.User;
import com.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public User login(User user) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }

        String sql = "select * from user where account =? and password = ?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1, user.getAccount());
        ps.setObject(2, user.getPassword());
        rs = ps.executeQuery();

        int count = 0;

        while (rs.next()){
            user.setUserid(rs.getInt("userid"));
            user.setCreateAt(rs.getDate("createAt"));
            user.setUsername(rs.getString("username"));
            user.setModifiedAt(rs.getDate("modifiedAt"));
            count++;
        }


        if(count > 1){
            //查询到两个以上的数据 报错
            DBUtil.close(conn,ps,rs);
            user = null;
        }else if(count < 1 ){
            //没有查到 返回null
            DBUtil.close(conn,ps,rs);
            user = null;
        }else{
            sql = "UPDATE user SET online = 1 WHERE userid = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,user.getUserid());
            ps.execute();

            DBUtil.close(conn,ps,rs);
        }
        return user;
    }

    public boolean save(User user) throws SQLException {
        UserDao dao  = new UserDao();
        if(dao.findByAccount(user.getAccount())){//账户存在
            return false;
        }else{
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            if(conn == null){
                conn = DBUtil.getMysqlConn();
                System.out.println(conn);
            }

            String sql = "insert into user(username,account,password,createAt,online)  values (?,?,?,?,0)";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, user.getUsername());
            ps.setObject(2, user.getAccount());
            ps.setObject(3, user.getPassword());
            ps.setObject(4, user.getCreateAt());
             if(ps.executeUpdate()>0) {
                 DBUtil.close(conn,ps);
                 return true;
             }else{
                 DBUtil.close(conn,ps);
                 return false;
             }
        }
    }

    //存在返回true 不存在返回false
    private boolean findByAccount(String account) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
            System.out.println(conn);
        }

        String sql = "SELECT COUNT(*) FROM `user` where account = ?";

        ps = conn.prepareStatement(sql);
        ps.setObject(1, account);
        rs = ps.executeQuery();

        Integer p = 0;

        if(rs.next()){
            p = rs.getInt(1);
        }
        DBUtil.close(conn,ps,rs);

        if(p>0) {
            return true;
        }else{
            return false;
        }
    }

    public List<User> loadMembership(int chatRoomId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }

        String sql = "SELECT userid,username FROM user WHERE online = 1 AND userid in\n" +
                "(SELECT userid from user_chatroom WHERE chatroomId = ?)";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,chatRoomId);
        rs = ps.executeQuery();

        List<User> list = new ArrayList<>();
        while (rs.next()){
            User user = new User();
            user.setUserid(rs.getInt("userid"));
            user.setUsername(rs.getString("username"));
            list.add(user);
        }

        DBUtil.close(conn,ps,rs);

        return list;
    }

    public void logout(int userid) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if(conn == null){
            conn = DBUtil.getMysqlConn();
        }

        String sql = "UPDATE user SET online = 0 WHERE userid = ?";
        ps = conn.prepareStatement(sql);
        ps.setObject(1,userid);
        ps.execute();

        DBUtil.close(conn,ps,rs);
    }
}
