package com.servlet;

import com.dao.ApplyChatRoomDao;
import com.dao.ApplyRoomDao;
import com.dao.ChatRoomDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DisagreeChatRoomServlet")
public class DisagreeChatRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String userid = request.getParameter("userid");
        int Id = Integer.parseInt(userid);
        ApplyChatRoomDao dao = new ApplyChatRoomDao();
        try {
            dao.delete(Id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/managerpage.jsp").forward(request,response);
    }
}
