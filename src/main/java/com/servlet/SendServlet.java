package com.servlet;

import com.bean.Information;
import com.bean.User;
import com.dao.InformationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/SendServlet")
public class SendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("text");
        int chatRoomId =Integer.parseInt((String)request.getSession().getAttribute("chatRoomId"));
        User user = (User)request.getSession().getAttribute("user");
        int userId = user.getUserid();
        Information information = new Information();
        information.setChatRoomId(chatRoomId);
        information.setContext(text);
        information.setTime(new Date(System.currentTimeMillis()));
        information.setUserId(userId);

        InformationDao dao = new InformationDao();
        try {
            dao.add(information);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/chatroom.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
