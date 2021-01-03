package com.servlet;

import com.alibaba.fastjson.JSON;
import com.bean.ChatRoom;
import com.bean.Information;
import com.bean.User;
import com.dao.ChatRoomDao;
import com.dao.InformationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/LoadManageChatroomServlet")
public class LoadManageChatroomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        User user = (User)request.getSession().getAttribute("user");
        int userid = user.getUserid();
        ChatRoomDao dao = new ChatRoomDao();
        List<ChatRoom> list = null;
        try {
            list = dao.search(userid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.getWriter().write( JSON.toJSONString(list));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
