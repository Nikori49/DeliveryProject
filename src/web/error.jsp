<%--
  Created by IntelliJ IDEA.
  User: yohoh
  Date: 08.02.2022
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="HomePage"/>


<html lang="${language}">
<head>
    <title><fmt:message key="label.errorTitle"/> </title>
</head>
<body>

<h3><fmt:message key="label.sorry"/> </h3>
${errorMessage}

</body>
</html>
