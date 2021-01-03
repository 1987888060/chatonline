package com.servlet;

import com.bean.Manager;
import com.bean.User;
import com.dao.ManagerDao;
import com.dao.UserDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        request.getSession().setAttribute("flag","0");

        if("0".equals(role)){//用户
            User user = new User(account,password);
            UserDao dao = new UserDao();
            try {
                user = dao.login(user);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            //user == null 表示没有查到相关用户 情况一：没有查到 情况二：查到两个或以上
            if(user == null){
                PrintWriter writer = response.getWriter();
                writer.write("<script>");
                writer.write("window.alert('账号或密码错误!');");
                writer.write("window.location.href='/login.html'");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }else {//登陆成功
                request.getSession().setAttribute("user",user);
                request.getSession().setAttribute("flag","1");
                request.getRequestDispatcher("/homepage.jsp").forward(request,response);
            }

        }else if("1".equals(role)){//管理员
            Manager manager = new Manager(account,password);
            ManagerDao dao = new ManagerDao();
            try {
                manager = dao.login(manager);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            if(manager == null){
                PrintWriter writer = response.getWriter();
                writer.write("<script>");
                writer.write("window.alert('账号或密码错误!');");
                writer.write("window.location.href='/login.html'");
                writer.write("</script>");
                writer.flush();
                writer.close();
            }else {//登陆成功

                request.getSession().setAttribute("manager",manager);
                //request.getRequestDispatcher("index.jsp").forward(request,response);
                response.sendRedirect("/managerpage.jsp");
            }
        }else{
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write("window.alert('系统异常!');");
            writer.write("window.location.href='/login.html'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }
}
