package com.servlet;

import com.bean.User;
import com.dao.ApplyChatRoomDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/ApplyChatRoomServlet")
public class ApplyChatRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String chatroomname = request.getParameter("chatroomname");
        String introduce = request.getParameter("introduce");
        String reason = request.getParameter("reason");
        int userid = ((User)request.getSession().getAttribute("user")).getUserid();
        ApplyChatRoomDao dao = new ApplyChatRoomDao();

        try {
            dao.add(userid,chatroomname,introduce,reason);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.getRequestDispatcher("/managechatroom.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int userid = ((User)request.getSession().getAttribute("user")).getUserid();
        ApplyChatRoomDao dao = new ApplyChatRoomDao();
        boolean k =false;
        try {
            k = dao.search(userid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if(k){//已存在
            Writer writer = response.getWriter();
            writer.write("<script type='text/javascript'>");
            writer.write("window.alert('已发送过请求，请等待验证！');");
            writer.write("window.location.href = '/managechatroom.jsp';");
            writer.write("</script>");
        }else{
            request.getRequestDispatcher("/registerchatroom.jsp").forward(request,response);
        }


    }
}
