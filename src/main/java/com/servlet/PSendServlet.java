package com.servlet;

import com.bean.Information;
import com.bean.User;
import com.dao.InformationDao;
import com.dao.PInformationDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/PSendServlet")
public class PSendServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("text");
        int ChatUserId =Integer.parseInt((String)request.getSession().getAttribute("ChatUserId"));
        User user = (User)request.getSession().getAttribute("user");
        int userId = user.getUserid();
        System.out.println(userId);
        System.out.println(ChatUserId);
        System.out.println(text);
        PInformationDao dao = new PInformationDao();
        try {
            dao.add(userId,ChatUserId,text);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/chat.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
