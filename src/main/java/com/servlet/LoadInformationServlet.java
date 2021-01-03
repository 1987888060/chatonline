package com.servlet;

import com.alibaba.fastjson.JSON;
import com.bean.ChatRoom;
import com.bean.Information;
import com.bean.User;
import com.dao.InformationDao;
import com.dao.PersonRoomListDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/LoadInformationServlet")
public class LoadInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int chatRoomId =Integer.parseInt((String)request.getSession().getAttribute("chatRoomId"));
        InformationDao dao = new InformationDao();
        List<Information> list = null;
        try {
            list = dao.load(chatRoomId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.getWriter().write( JSON.toJSONString(list));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getParameter("chatRoomId");
        request.getSession().setAttribute("chatRoomId",str);
        request.getRequestDispatcher("/chatroom.jsp").forward(request,response);
    }
}
