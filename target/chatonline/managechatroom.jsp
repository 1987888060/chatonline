<%--
  Created by IntelliJ IDEA.
  User: 911
  Date: 2/1/2021
  Time: 下午11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>聊天室管理</title>
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
                    //console.log(typeof xmlhttp.responseText);
                    //遍历数
                    var json = JSON.parse(xmlhttp.responseText);
                    var str = "";
                    for( var i = 0 ;i < json.length; i++){
                        var hrefStr = "/PerProApplyServlet?chatRoomId="+json[i].chatRoomId;
                        str = str + "<a href='"+hrefStr+"' style='color: white'>\n"+
                            "<div style='display:block;'>\n" +
                            "            <h5>房间名:"+json[i].chatRoomName+"</h5>" +
                            "        </div>\n"+
                            "</a>\n"
                        ;
                    }
                    document.getElementById("managelist").innerHTML = str;
                }
            }
            xmlhttp.open("POST","/LoadManageChatroomServlet",true);
            xmlhttp.send();
        }
        window.onload = function () {
            setInterval(function () {
                loadXMLDoc();
            },100);
        }
    </script>
    <link rel="stylesheet" href="/css/style2.css">
</head>
<body>
    <div class="top">
        <a href="/ApplyChatRoomServlet" style="color: white">申请聊天室</a>
        <a href="/homepage.jsp" style="color: white">首页</a>
    </div>
    <div style="color: white">
        我管理的聊天室
        <div id="managelist">

        </div>
    </div>
</body>
</html>
