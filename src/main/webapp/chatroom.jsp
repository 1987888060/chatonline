<%--
  Created by IntelliJ IDEA.
  User: 911
  Date: 31/12/2020
  Time: 下午5:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>聊天室</title>
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
                        str = str + "<div style='display:block; color: blueviolet; background-color:#ffa400 ;'>\n" +
                            "            <h5>时间:"+json[i].times+"</h5>\n" +
                            "            <p>发送人:"+json[i].user+"</p>\n"+
                            "            <p>消息:"+json[i].context+"</p>\n" +
                            "        </div>\n";
                    }
                    document.getElementById("accept").innerHTML = str;
                    /*var acceptdiv = document.getElementById("accept");
                    acceptdiv.scrollTop = acceptdiv.scrollHeight;*/
                }
            }
            xmlhttp.open("POST","/LoadInformationServlet",true);
            xmlhttp.send();
        }
        function loadmembership()
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
                    var str = "在线成员";
                    for( var i = 0 ;i < json.length; i++){
                        var hrefStr = "/LoadPInformationServlet?ChatUserId="+json[i].userid;
                        str = str + "<a href='"+hrefStr+"' >\n"+
                            "<div style='display:block; color: blueviolet; background-color:#ffa400 ;'>\n" +
                            "            <h5>"+json[i].username+"</h5>\n" +
                        "        </div>\n"+
                        "</a>\n";
                    }
                    document.getElementById("membership").innerHTML = str;
                }
            }
            xml.open("POST","/LoadMembershipServlet",true);
            xml.send();
        }
        window.onload=function(){
            document.getElementById("send").value = "";
            setInterval(function () {
                loadXMLDoc();
                loadmembership();
            },10);
        }
        document.onkeydown = function (e) {
            if(e.keyCode == 13 && e.ctrlKey){
                // 这里实现换行
                document.getElementById("send").value += "\n";
            }else if(e.keyCode == 13){
                // 避免回车键换行
                e.preventDefault();
                // 下面写你的发送消息的代码
                var text = document.getElementById("send").value;
                if(text == ""){

                }else{
                    //window.alert(text);
                    document.getElementById("sendButton").click();
                    window.alert("suadhuaishduiui");
                    document.getElementById("send").value = "";
                }
            }
        }
    </script>
</head>
<body>
    <a href="/homepage.jsp">首页</a>
    <a href="/personroomlist.jsp">我的聊天室</a>
    <div id="accept" style="overflow-x: hidden; word-break:break-all; width: 800px ;height: 400px ; border: solid">
    </div>
    <form action="SendServlet" method="post" >
        <textarea id="send" name="text" style=" width: 800px ;height: 150px ; resize: none ;overflow-x: hidden" >
        </textarea>
        <input id="sendButton" type="submit" value="发送">
    </form>
    <div id="membership" style="overflow-x: hidden; word-break:break-all; width: 200px ;height: 400px ; border: solid">
    </div>
</body>
</html>
