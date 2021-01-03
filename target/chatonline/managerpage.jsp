<%--
  Created by IntelliJ IDEA.
  User: 911
  Date: 29/12/2020
  Time: 下午3:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员界面</title>
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
                    var str = "<h3 style='color: white'>申请列表</h3>\n";
                    for( var i = 0 ;i < json.length; i++){
                        str = str + "<div style='display:block; color: white'>\n" +
                            "            <h5>申请人:"+json[i].username+"</h5>\n" +
                            "            <h5>聊天室名字:"+json[i].name+"</h5>\n" +
                            "            <p>简介:"+json[i].introduce+"</p>\n"+
                            "            <p>理由:"+json[i].reason+"</p>\n"+
                            "            <a href='/AgreeChatRoomServlet?userid="+json[i].userid+"'>同意</a>\n"+
                            "            <a href='/DisagreeChatRoomServlet?userid="+json[i].userid+"'>不同意</a>\n"+
                            "        </div>\n"
                        ;
                    }
                    document.getElementById("list").innerHTML = str;
                }
            }
            xmlhttp.open("POST","/LoadChatRoomServlet",true);
            xmlhttp.send();
        }
        window.onload = function () {
            setInterval(function () {
                loadXMLDoc();
            },100)
        }
    </script>
    <link rel="stylesheet" href="/css/style3.css">
</head>
<body>
    <div class="top">
        <h3 style="color: white">管理员界面</h3>
        <a href="/LogoutServlet" style="color: white">退出</a>
    </div>
    <div id="list"  style="overflow-x: hidden; word-break:break-all; ">
        没有申请
    </div>

</body>
</html>
