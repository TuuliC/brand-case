<%--
  Created by IntelliJ IDEA.
  User: chenweisong
  Date: 2022/11/12
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>

    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">

    <meta charset="UTF-8">
    <title>登录</title>
    <link href="<%=basePath%>css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv" style="height: 350px">
    <form action="/brand-case/user/login" id="form">
        <h1 id="loginMsg">登 录</h1>
        <div id="errorMsg">${login_msg}${register_msg}</div>
        <p>用户名：<input id="username" name="username" value="${cookie.username.value}" type="text"></p>
        <p>密&nbsp;&nbsp;&nbsp;码：<input id="password" name="password" value="${cookie.password.value}" type="password">
        </p>
        <p>记住我：<input id="remember" name="remember" value="1" checked="checked" type="checkbox"></p>
        <div id="subDiv">
            <input type="submit" class="button" value="登录">
            <input type="button" id="reset" class="button" value="重置">&nbsp;&nbsp;&nbsp;
            <a href="register.jsp">没有账号？</a>
        </div>
    </form>
</div>

<script>
    document.getElementById("reset").onclick = function () {
        document.getElementById("username").value = "";
        document.getElementById("password").value = "";
        document.getElementById("remember").checked = false;
    }
</script>

</body>
</html>