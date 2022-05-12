<%--
  Created by IntelliJ IDEA.
  User: yohoh
  Date: 08.02.2022
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="HomePage"/>


<html lang="${language}">
<head>
    <style >
        table {
            border: 1px solid #000;
            border-collapse: collapse;
            text-align: center;
        }

        th {
            border: 1px solid #000;
            text-align: center;
        }

        td {
            border: 1px solid #000;
            text-align: center;
        }


    </style>
    <title><fmt:message key="label.createAccountButton"/></title>
</head>
<body>
<c:if test="${loggedUser!=null}">
    <c:redirect url="home.jsp"/>
</c:if>

    <%--Language selection block--%>
    <div style="border: white; position:absolute; top:0;right: 18%;">

    <table style="border-collapse: collapse">
        <tr>
            <td style="padding: unset">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="changeLanguage">
                    <input type="hidden" name="changeLanguageTo" value="ru">
                    <input type="hidden" name="returnTo" value="register.jsp">
                    <input type="submit"
                    <c:if test="${language=='ru'}">disabled</c:if> value="Ru">
                </form>
            </td>
            <td style="padding: unset">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="changeLanguage">
                    <input type="hidden" name="changeLanguageTo" value="en">
                    <input type="hidden" name="returnTo" value="register.jsp">
                    <input type="submit"
                    <c:if test="${language=='en'}">disabled</c:if> value="En">
                </form>
            </td>
        </tr>
    </table>
    </div>


<div style="position: absolute; left:0; top:0"  >
    <a href="home.jsp"><fmt:message key="label.backToMain"/></a>
</div>
<div style="position: absolute; left:35%; top:0"  >
<form action="controller" method="post">
    <table>
        <input type="hidden" name="command" value="register"><br>
        <tr>
            <td><fmt:message key="label.email"/></td>
            <td><input name="email"></td>
        </tr>
        <tr>
            <td><fmt:message key="label.login"/></td>
            <td><input name="login"></td>
        </tr>
        <tr>
            <td><fmt:message key="label.phoneNumber"/></td>
            <td><input name="phone_number"></td>
        </tr>
        <tr>
            <td><fmt:message key="label.name"/></td>
            <td><input name="name"></td>
        </tr>
        <tr>
            <td><fmt:message key="label.surname"/></td>
            <td><input name="surname"></td>
        </tr>
        <tr>
            <td><fmt:message key="label.password"/></td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="<fmt:message key="label.createAccountButton"/>"></td>
        </tr>

    </table>
</form>
    <c:forEach items="${requestScope.error}" var="errorListItem">
        ${errorListItem}<br>
    </c:forEach>

</div>

</body>
</html>
