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

            return ture;
        }
    </script>
</head>
<body>
    <form action="ApplyChatRoomServlet" method="post" onsubmit="return validate()">
        <div>
            聊天室注册
        </div>
        <div >
            <label>聊天室名字:</label>
            <input id="chatroomname"  type="text" name="chatroomname" placeholder="必填">
        </div>
        <div >
            <label >简介:</label>
            <input id="introduce" type="text" name="introduce" value="无简介">
        </div>
        <div >
            <label >理由:</label>
            <input id="reason" type="text" name="reason" value="无理由">
        </div>
        <div>
            <input type="submit" value="注册" >
        </div>
    </form>
</body>
</html>
