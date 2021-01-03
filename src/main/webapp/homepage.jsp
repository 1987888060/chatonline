<%@ page import="com.bean.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bean.ChatRoom" %>
<%@ page import="com.dao.ChatRoomDao" %><%--
  Created by IntelliJ IDEA.
  User: 911
  Date: 29/12/2020
  Time: 下午3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>chatroom</title>
    <script type="text/javascript">
        function loadXMLDoc()
        {
            var xmlhttp;
            if (window.XMLHttpRequest)
            {
                //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
                xmlhttp=new XMLHttpRequest();
            }
            else
            {
                // IE6, IE5 浏览器执行代码
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xmlhttp.onreadystatechange=function()
            {
                if (xmlhttp.readyState==4 && xmlhttp.status==200)
                {

                    var json = JSON.parse(xmlhttp.responseText);
                    var str = "<h3>聊天室列表</h3>\n";
                    for( var i = 0 ;i < json.length; i++){
                        var hrefStr = "####";
                        <c:if test="${flag.equals('1')}">
                            hrefStr = "/ApplyServlet?chatRoomId="+json[i].chatRoomId;
                        </c:if>
                        str = str + "<a href='"+hrefStr+"' >\n"+
                            "<div style='display:block; color: blueviolet; background-color:#ffa400 ;'>\n" +
                            "            <h5>房间名:"+json[i].chatRoomName+"</h5>" +
                            "            <p>ID:"+json[i].chatRoomId+"</p>\n"+
                            "            <p>人数:"+json[i].number+"</p>\n"+
                            "            <p>介绍:"+json[i].introduce+"</p>\n" +
                            "        </div>\n"+
                            "</a>\n"
                        ;
                    }
                   document.getElementById("chatroomlist").innerHTML = str;
                    //console.log(json);
                }
            }
            xmlhttp.open("POST","/LoadRoomServlet",true);
            xmlhttp.send();
        }
        window.onload=function(){
            setInterval(function () {
                loadXMLDoc();
            },100);
        }
    </script>
</head>
<body>
    <%
        User user = (User)request.getSession().getAttribute("user");
    %>
    <div>
        <h2>聊天室</h2>
        <c:if test="${flag.equals('1')}">
            <div>
                <% out.write(user.getUsername());  %>
                <ul>
                    <li><a href="/managechatroom.jsp">聊天室管理</a></li>
                    <li><a href="/personroomlist.jsp">我的聊天室</a></li>
                </ul>
            </div>
            <a href="/LogoutServlet">退出登录</a>
        </c:if>
        <c:if test="${!flag.equals('1')}">
            <a href="/login.html">登录</a>
        </c:if>
    </div>

    <div id="chatroomlist" style="overflow-x: hidden; word-break:break-all; width: 800px ;height: 400px ;border: 1px " >
    </div>

</body>
</html>
