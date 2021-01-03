<%--
  Created by IntelliJ IDEA.
  User: 911
  Date: 31/12/2020
  Time: 下午4:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的聊天室</title>
    <script type="text/javascript">
        function loadpersonlist()
        {
            var xml;
            if (window.XMLHttpRequest)
            {
                //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
                xml=new XMLHttpRequest();
            }
            else
            {
                // IE6, IE5 浏览器执行代码
                xml=new ActiveXObject("Microsoft.XMLHTTP");
            }
            xml.onreadystatechange=function()
            {
                if (xml.readyState==4 && xml.status==200)
                {

                    var json = JSON.parse(xml.responseText);
                    var str = "<h3 style='color: white'>我的聊天室</h3>\n";
                    for( var i = 0 ;i < json.length; i++){
                        var hrefStr = "/LoadInformationServlet?chatRoomId="+json[i].chatRoomId;
                        str = str + "<a href='"+hrefStr+"' >\n"+
                            "<div style='display:block; color: white; '>\n" +
                            "            <h5>房间名:"+json[i].chatRoomName+"</h5>" +
                            "        </div>\n"+
                            "</a>\n"
                        ;
                    }
                    document.getElementById("personchatroomlist").innerHTML = str;
                    //console.log(json);
                }
            }
            xml.open("POST","/LoadPersonListServlet",true);
            xml.send();
        }
        window.onload=function(){
            setInterval(function () {
                loadpersonlist();
            },100);
        }
    </script>
    <link rel="stylesheet" href="/css/style3.css">
</head>
<body>
    <div class="top">
        <a href="homepage.jsp" style="color: white">首页</a>
    </div>
    <div id="personchatroomlist">
    </div>

</body>
</html>
