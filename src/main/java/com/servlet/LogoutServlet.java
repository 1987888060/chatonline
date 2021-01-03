package com.servlet;

import com.bean.User;
import com.dao.PInformationDao;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String role = request.getParameter("role");
        if("0".equals(role)){
            User user = (User)request.getSession().getAttribute("user");
            UserDao dao = new UserDao();
            try {
                dao.logout(user.getUserid());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            PInformationDao dao1 = new PInformationDao();
            try {
                dao1.delete(user.getUserid());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        request.getSession().setAttribute("flag","0");
        request.getRequestDispatcher("/homepage.jsp").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
