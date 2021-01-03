package com.servlet;

import com.alibaba.fastjson.JSON;
import com.bean.Information;
import com.bean.PInfo;
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
import java.util.List;

@WebServlet("/LoadPInformationServlet")
public class LoadPInformationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int ChatUserId =Integer.parseInt((String)request.getSession().getAttribute("ChatUserId"));
        User user = (User) request.getSession().getAttribute("user");
        PInformationDao dao = new PInformationDao();
        List<PInfo> list = null;
        try {
            list = dao.load(user.getUserid(),ChatUserId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        response.getWriter().write( JSON.toJSONString(list));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String str = request.getParameter("ChatUserId");
        request.getSession().setAttribute("ChatUserId",str);
        int ChatUserId =Integer.parseInt((String)request.getSession().getAttribute("ChatUserId"));
        int userId = ((User)request.getSession().getAttribute("user")).getUserid();
        if(ChatUserId == userId){
            request.getRequestDispatcher("/chatroom.jsp").forward(request,response);
        }else {
            request.getRequestDispatcher("/chat.jsp").forward(request,response);
        }



    }
}
