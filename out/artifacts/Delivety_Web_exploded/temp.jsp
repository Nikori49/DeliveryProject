
<%--
  Created by IntelliJ IDEA.
  User: yohoh
  Date: 31.01.2022
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <title>Delivery</title>
</head>
<body>
<c:if test="${loggedUser== null }">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="login"><br>
        <input name="login" ><br>
        <input type="password" name="password"><br>
        <input type="submit" value="Login">
    </form>
</c:if>
<c:if test="${loggedUser != null}">
    Welcome, <a href="profile.jsp">${loggedUser.name}</a> !
</c:if>

amogus
</body>
</html>
