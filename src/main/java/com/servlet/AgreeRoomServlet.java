package com.servlet;

import com.dao.ApplyRoomDao;
import com.dao.PersonRoomListDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AgreeRoomServlet")
public class AgreeRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String applyId = request.getParameter("applyId");
        int Id = Integer.parseInt(applyId);
        int chatRoomId = Integer.parseInt((String)request.getSession().getAttribute("chatRoomId"));
        PersonRoomListDao dao = new PersonRoomListDao();
        try {
            dao.add(chatRoomId,Id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ApplyRoomDao dao1 = new ApplyRoomDao();
        try {
            dao1.delete(chatRoomId,Id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/PerProcessApply.jsp").forward(request,response);
    }
}
