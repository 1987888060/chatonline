package com.servlet;

import com.bean.User;
import com.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String username = request.getParameter("username");

        User user = new User(account,password,username,new Date());

        UserDao dao = new UserDao();
        boolean flag = false;
        try {
            flag = dao.save(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(flag){//注册成功
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write("alert('注册成功');");
            writer.write("window.location.href='login.html'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }else{//注册失败
            PrintWriter writer = response.getWriter();
            writer.write("<script>");
            writer.write("alert('账户已存在!');");
            writer.write("window.location.href='register.html'");
            writer.write("</script>");
            writer.flush();
            writer.close();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
