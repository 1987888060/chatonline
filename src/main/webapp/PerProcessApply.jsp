<%--
  Created by IntelliJ IDEA.
  User: 911
  Date: 3/1/2021
  Time: 上午12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户申请处理</title>
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
                    var str = "<h3>申请列表</h3>\n";
                    for( var i = 0 ;i < json.length; i++){
                        str = str + "<div style='display:block; color: blueviolet; background-color:#ffa400 ;'>\n" +
                            "            <h5>申请人:"+json[i].username+"</h5>\n" +
                            "            <p>理由:"+json[i].reason+"</p>\n"+
                            "            <a href='/AgreeRoomServlet?applyId="+json[i].applyId+"'>同意</a>\n"+
                            "            <a href='/DisagreeRoomServlet?applyId="+json[i].applyId+"'>不同意</a>\n"+
                            "        </div>\n"
                        ;
                    }
                    document.getElementById("processlist").innerHTML = str;
                }
            }
            xmlhttp.open("POST","/PerProApplyServlet",true);
            xmlhttp.send();
        }
        window.onload = function () {
            setInterval(function () {
                loadXMLDoc();
            },100)
        }
    </script>
</head>
<body>
    <a href="/homepage.jsp">首页</a>
    <a href="/managechatroom.jsp">聊天室管理</a>
    <div id="processlist">
    </div>
</body>
</html>
