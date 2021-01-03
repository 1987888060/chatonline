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
    <link href="/css/bootstrap.css" rel="stylesheet" type="text/css"
          media="all">
    <!-- Custom Theme files -->
    <link href="/css/style1.css" rel="stylesheet" type="text/css"
          media="all" />
    <!--Google Fonts-->
    <!-- start-smoth-scrolling -->
    <script type="text/javascript" src="/js/jquery-1.11.0.min.js"></script>
    <script type="text/javascript" src="/js/move-top.js"></script>
    <script type="text/javascript" src="/js/easing.js"></script>
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //end-smoth-scrolling -->
    <script src="/js/menu_jquery.js"></script>
    <!--script-->
    <script src="/js/modernizr.custom.97074.js"></script>
    <script src="/js/jquery.chocolat.js"></script>
    <link rel="stylesheet" href="/css/chocolat.css" type="text/css" media="screen" charset="utf-8">
    <script type="text/javascript" src="/js/jquery.hoverdir.js"></script>
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
                    var str = "";
                    for( var i = 0 ;i < json.length; i++){
                        var hrefStr = "####";
                        <c:if test="${flag.equals('1')}">
                            hrefStr = "/ApplyServlet?chatRoomId="+json[i].chatRoomId;
                        </c:if>
                        str = str + "<li>\n"+
                            "<a href='"+hrefStr+"' rel='title' class='b-link-stripe b-animate-go  thickbox '  >\n"+
                            "<div style='overflow: hidden'>\n" +
                            "            <h5>房间名:"+json[i].chatRoomName+"&nbsp;&nbsp;人数:"+json[i].number+"</h5>" +
                            "            <span>简介:"+json[i].introduce+"</span>\n" +
                            "        </div>\n"+
                            "</a>\n"+
                            "</li>"
                        ;
                    }
                   document.getElementById("da-thumbs").innerHTML = str;
                    //console.log(json);
                }
            }
            xmlhttp.open("POST","/LoadRoomServlet",true);
            xmlhttp.send();
        }
        window.onload=function(){
            /*setInterval(function () {
                loadXMLDoc();
            },1000);*/
            loadXMLDoc();
        }
    </script>
</head>
<body>
    <%
        User user = (User)request.getSession().getAttribute("user");
    %>
    <div class="header1">
        <div class="container">
            <div class="header-main">
                <div class="top-menu">
                    <ul>
                        <c:if test="${flag.equals('1')}">
                            <li><a href="/homepage.jsp"><% out.write(user.getUsername());  %></a></li>
                            <li><a href="/managechatroom.jsp" >聊天室管理</a></li>
                            <li><a href="/personroomlist.jsp" >我的聊天室</a></li>
                            <li><a href="/LogoutServlet" >退出登录</a></li>

                        </c:if>
                        <c:if test="${!flag.equals('1')}">
                            <li><a href="/login.html" >登录</a></li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div  class="gallery" id="chatroomlist" style="overflow-x: hidden; word-break:break-all; width: 100% ;height: 600px ;border: 1px " >
        <div class="container">
            <div class="gallery-top heading">
                <h2>聊天室</h2>
            </div>
            <section>
                <ul id="da-thumbs" class="da-thumbs">

                </ul>
            </section>
            <script type="text/javascript">
                $(function() {
                    $(' #da-thumbs > li ').each( function() { $(this).hoverdir(); } );
                });
            </script>
        </div>
    </div>

    <div class="copyright">
        <div class="container">
            <div class="copyright-main">
                <p style="color: black;">
                    在线网络聊天期待您的加入| <a href="/homepage.jsp" target="_blank"
                                     style="color: black;">ChatOnline</a>
                </p>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>

</body>
</html>
