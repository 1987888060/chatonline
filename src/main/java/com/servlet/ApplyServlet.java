package com.servlet;

import com.bean.User;
import com.dao.ApplyRoomDao;
import com.dao.PersonRoomListDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@WebServlet("/ApplyServlet")
public class ApplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String reason = request.getParameter("reason");
        int chatRoomId =Integer.parseInt((String)request.getSession().getAttribute("chatRoomId"));
        User user = (User) request.getSession().getAttribute("user");
            ApplyRoomDao dao = new ApplyRoomDao();
            try {
                dao.add(chatRoomId,user.getUserid(),reason);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            Writer writer = response.getWriter();
            writer.write("<script type='text/javascript'>");
            writer.write("window.alert('请求已发送！');");
            writer.write("window.location.href = '/homepage.jsp';");
            writer.write("</script>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String str = request.getParameter("chatRoomId");
        request.getSession().setAttribute("chatRoomId",str);
        int chatRoomId =Integer.parseInt(str);
        User user = (User) request.getSession().getAttribute("user");
        boolean k =false;
        PersonRoomListDao dao = new PersonRoomListDao();
        try {
            k = dao.search(chatRoomId,user.getUserid());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        boolean kk = false;
        int userId = ((User)request.getSession().getAttribute("user")).getUserid();
        ApplyRoomDao dao1 = new ApplyRoomDao();
        try {
            kk = dao1.search(chatRoomId,userId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(k){//存在
            Writer writer = response.getWriter();
            writer.write("<script type='text/javascript'>");
            writer.write("window.location.href = '/LoadInformationServlet?chatRoomId="+chatRoomId+"';");
            writer.write("</script>");
        }else if(kk){//已发送请求
            Writer writer = response.getWriter();
            writer.write("<script type='text/javascript'>");
            writer.write("window.alert('已发送过请求，请等待验证！');");
            writer.write("window.location.href = '/homepage.jsp';");
            writer.write("</script>");
        } else{//不存在
            request.getRequestDispatcher("/applyForRoom.jsp").forward(request,response);
        }
    }
}
