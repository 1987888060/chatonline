<%--
  Created by IntelliJ IDEA.
  User: 911
  Date: 2/1/2021
  Time: 上午9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>申请</title>
</head>
<body>
    <a href="homepage.jsp">首页</a>
    <form action="ApplyServlet" method="post">
        <textarea id="reason" name="reason" style=" width: 400px ;height: 200px ; resize: none ;overflow-x: hidden" >
        </textarea>
        <input id="sendButton" type="submit" value="提交">
        <script>
            window.onload = function () {
                document.getElementById("reason").value = "输入你的申请理由";
            }
        </script>
    </form>
</body>
</html>
