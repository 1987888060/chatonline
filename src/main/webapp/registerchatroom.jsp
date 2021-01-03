<%--
  Created by IntelliJ IDEA.
  User: 911
  Date: 3/1/2021
  Time: 上午3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册聊天室</title>
    <script>
        function validate() {
            var chatroomname = document.getElementById("chatroomname").value;

            if ( chatroomname == ''){
                alert("聊天室名字不能为空！");
                return false;
            }else if (chatroomname.length>9){
                alert("聊天室名字要小于9个字符");
                return false;
            }

            if(document.getElementById("reason").value == ""){
                document.getElementById("reason").value == "无理由";
            }
            if(document.getElementById("introduce") == ""){
                document.getElementById("introduce") == "无简介";
            }
            return ture;
        }
    </script>

    <link rel="stylesheet" href="/css/style3.css">
</head>
<body>
    <form action="ApplyChatRoomServlet" method="post" onsubmit="return validate()">
        <div class="top">
            <a href="/managechatroom.jsp" style="color:white">聊天室管理</a>
            <a href="/homepage.jsp" style="color: white">首页</a>
        </div>
        <div >
            <label>聊天室名字:</label>
            <input id="chatroomname"  type="text" name="chatroomname" placeholder="必填">
        </div>
        <div >
            <p >简介:</p>
            <textarea id="introduce"  name="introduce" style="width:400px;height: 200px; resize: none ;overflow-x: hidden" ></textarea>
        </textarea>
        </div>
        <div >
            <p >理由:</p>
            <textarea id="reason"  name="reason" style="width:400px; height:200px; resize: none ;overflow-x: hidden" ></textarea>
        </div>
        <div>
            <input type="submit" value="注册">
        </div>
    </form>
</body>
</html>
