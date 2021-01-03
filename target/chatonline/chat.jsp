<%--
  Created by IntelliJ IDEA.
  User: 911
  Date: 2/1/2021
  Time: 下午2:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>私聊</title>
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
                        str = str + "<div style='display:block; color: white'>\n" +
                            "            <p>发送人:"+json[i].sender+"</p>\n"+
                            "            <p>消息:"+json[i].text+"</p>\n" +
                            "        </div>\n";
                    }
                    document.getElementById("accept").innerHTML = str;
                    var acceptdiv = document.getElementById("accept");
                    acceptdiv.scrollTop = acceptdiv.scrollHeight;
                }
            }
            xmlhttp.open("POST","/LoadPInformationServlet",true);
            xmlhttp.send();
        }
        window.onload=function(){
            document.getElementById("send").value = "";
            setInterval(function () {
                loadXMLDoc();
            },10);
        }
        document.onkeydown = function (e) {
            if (e.keyCode == 13 && e.ctrlKey) {
                // 这里实现换行
                document.getElementById("send").value += "\n";
            } else if (e.keyCode == 13) {
                // 避免回车键换行
                e.preventDefault();
                // 下面写你的发送消息的代码
                var text = document.getElementById("send").value;
                if (text == "") {
                } else {
                    //window.alert(text);
                    document.getElementById("sendButton").click();
                    document.getElementById("send").value = "";
                }
            }
        }
    </script>
    <link rel="stylesheet" href="/css/style2.css">
</head>
<body>
    </form>
    <div class="top">
        <a href="/homepage.jsp" style="color: white">首页</a>
        <a href="/chatroom.jsp" style="color: white">我的聊天室</a>
    </div>
    <div class="left">
        <div id="accept" style="overflow-x: hidden; word-break:break-all; ">
        </div>
        <form action="PSendServlet" method="post" >
        <textarea id="send" name="text" style="resize: none ;overflow-x: hidden">
        </textarea>
            <input id="sendButton" type="submit" value="发送">
        </form>
    </div>
</body>
</html>
