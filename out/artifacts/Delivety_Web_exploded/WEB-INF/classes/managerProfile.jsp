<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="HomePage"/>


<html lang="${language}">
<head>
    <title><fmt:message key="label.welcome"/> ${loggedUser.name}</title>
    <style>
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
</head>
<body>
<c:if test="${loggedUser.role != 'manager'}">
    <c:redirect url="index.jsp"></c:redirect>
</c:if>
<%--Language selection block--%>
<div style="border: white; position:absolute; top:0;right: 18%;">

    <table style="border-collapse: collapse">
        <tr>
            <td style="padding: unset">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="changeLanguage">
                    <input type="hidden" name="changeLanguageTo" value="ru">
                    <input type="hidden" name="returnTo" value="managerProfile.jsp">
                    <input type="submit"
                           <c:if test="${language=='ru'}">disabled</c:if> value="Ru">
                </form>
            </td>
            <td style="padding: unset">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="changeLanguage">
                    <input type="hidden" name="changeLanguageTo" value="en">
                    <input type="hidden" name="returnTo" value="managerProfile.jsp">
                    <input type="submit"
                           <c:if test="${language=='en'}">disabled</c:if> value="En">
                </form>
            </td>
        </tr>
    </table>


</div>

<div style="position: absolute; top: 0%; right: 0%;">
    <fmt:message key="label.welcome"/> ${loggedUser.name}!
    <a href="index.jsp"><fmt:message key="label.backToMain"/> </a>
    <form action="controller" method="get">
        <input hidden name="command" value="logout">
        <input type="submit" value="<fmt:message key="label.logoutButton"/>">
    </form>
</div>
<c:if test="${loggedUser.role == 'manager'}">
    <div style="position:absolute; top:0; left:0;">
        <c:if test="${ordersPendingApproval!=null}">
            <fmt:message key="label.ordersPendingApproval"/>
            <table>
                <tr>
                    <th><fmt:message key="label.name"/></th>
                    <th><fmt:message key="label.surname"/></th>
                    <th><fmt:message key="label.phoneNumber"/></th>
                    <th><fmt:message key="label.email"/></th>
                    <th><fmt:message key="label.orderedCargoName"/></th>
                    <th><fmt:message key="label.route"/></th>
                    <th><fmt:message key="label.price"/></th>
                    <th><fmt:message key="label.orderPlacementTime"/></th>
                    <th><fmt:message key="label.pickUpDate"/></th>
                    <th><fmt:message key="label.dateOfArrival"/></th>
                    <th><fmt:message key="label.orderStatus"/></th>
                    <th><fmt:message key="label.actions"/></th>
                </tr>
                <c:forEach items="${ordersPendingApproval}" var="orders">
                    <tr>
                        <td>
                            <c:forEach items="${userList}" var="user">
                                <c:if test="${user.id==orders.userId}">
                                    ${user.name}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${userList}" var="user">
                                <c:if test="${user.id==orders.userId}">
                                    ${user.surname}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${userList}" var="user">
                                <c:if test="${user.id==orders.userId}">
                                    ${user.phoneNumber}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${userList}" var="user">
                                <c:if test="${user.id==orders.userId}">
                                    ${user.email}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                                ${orders.cargoName}
                        </td>

                        <c:forEach items="${routeList}" var="route">
                            <c:if test="${route.id==orders.routeId}">
                                <td>${route.start}-${route.destination}</td>
                                <c:forEach items="${tariffList}" var="tariff">
                                    <c:if test="${tariff.id == orders.tariffId}">
                                        <td>
                                                ${route.length*tariff.pricePerKm}
                                        </td>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                        <td>

                                ${orders.orderPlacementTime}

                        </td>
                        <td>

                                ${orders.pickUpDate}

                        </td>
                        <td>

                                ${orders.dateOfArrival}

                        </td>
                        <td>
                                ${orders.orderStatus}
                        </td>
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="approveOrder">
                                <input type="hidden" name="orderId" value="${orders.id}">
                                <input type="submit" value="<fmt:message key="label.approve"/> ">
                            </form>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="terminateOrder">
                                <input type="hidden" name="orderId" value="${orders.id}">
                                <input type="submit" value="<fmt:message key="label.terminateButton"/>">
                            </form>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </c:if>
        <c:if test="${ordersInProgress!=null}">
            <fmt:message key="label.ordersInProgress"/>
            <table>
                <tr>
                    <th><fmt:message key="label.name"/></th>
                    <th><fmt:message key="label.surname"/></th>
                    <th><fmt:message key="label.phoneNumber"/></th>
                    <th><fmt:message key="label.email"/></th>
                    <th><fmt:message key="label.cargoName"/></th>
                    <th><fmt:message key="label.route"/></th>
                    <th><fmt:message key="label.price"/></th>
                    <th><fmt:message key="label.orderPlacementTime"/></th>
                    <th><fmt:message key="label.pickUpDate"/></th>
                    <th><fmt:message key="label.dateOfArrival"/></th>
                    <th><fmt:message key="label.orderStatus"/></th>
                    <th><fmt:message key="label.actions"/></th>
                </tr>
                <c:forEach items="${ordersInProgress}" var="orders">
                    <tr>
                        <td>
                            <c:forEach items="${userList}" var="user">
                                <c:if test="${user.id==orders.userId}">
                                    ${user.name}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${userList}" var="user">
                                <c:if test="${user.id==orders.userId}">
                                    ${user.surname}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${userList}" var="user">
                                <c:if test="${user.id==orders.userId}">
                                    ${user.phoneNumber}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${userList}" var="user">
                                <c:if test="${user.id==orders.userId}">
                                    ${user.email}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>
                                ${orders.cargoName}
                        </td>

                        <c:forEach items="${routeList}" var="route">
                            <c:if test="${route.id==orders.routeId}">
                                <td>${route.start}-${route.destination}</td>
                                <c:forEach items="${tariffList}" var="tariff">
                                    <c:if test="${tariff.id == orders.tariffId}">
                                        <td>
                                                ${route.length*tariff.pricePerKm}
                                        </td>
                                    </c:if>
                                </c:forEach>
                            </c:if>
                        </c:forEach>
                        <td>

                                ${orders.orderPlacementTime}

                        </td>
                        <td>

                                ${orders.pickUpDate}

                        </td>
                        <td>

                                ${orders.dateOfArrival}

                        </td>
                        <td>
                                ${orders.orderStatus}
                        </td>
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="finishOrder">
                                <input type="hidden" name="orderId" value="${orders.id}">
                                <input type="submit" value="<fmt:message key="label.finishButton"/>">
                            </form>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="terminateOrder">
                                <input type="hidden" name="orderId" value="${orders.id}">
                                <input type="submit" value="<fmt:message key="label.terminateButton"/>">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <fmt:message key="label.allOrders"/>
        <table>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <form action="controller" method="get">
                        <input hidden name="command" value="sortManagerOrderList">
                        <input hidden name="managerOrderListSortedBy" value="orderPlacementTimeAscending">
                        <input type="submit" value="<fmt:message key="label.sortAscendingButton"/>">
                    </form>
                    <form action="controller" method="get">
                        <input hidden name="command" value="sortManagerOrderList">
                        <input hidden name="managerOrderListSortedBy" value="orderPlacementTimeDescending">
                        <input type="submit" value="<fmt:message key="label.sortDescendingButton"/>">
                    </form>
                </td>
                <td></td>
                <td></td>
                <td>
                    <form action="controller" method="get">
                        <input hidden name="command" value="sortManagerOrderList">
                        <input hidden name="managerOrderListSortedBy" value="statusAscending">
                        <input type="submit" value="<fmt:message key="label.sortAscendingButton"/>">
                    </form>
                    <form action="controller" method="get">
                        <input hidden name="command" value="sortManagerOrderList">
                        <input hidden name="managerOrderListSortedBy" value="statusDescending">
                        <input type="submit" value="<fmt:message key="label.sortDescendingButton"/>">
                    </form>
                </td>

            </tr>
            <tr>
                <th><fmt:message key="label.name"/></th>
                <th><fmt:message key="label.surname"/></th>
                <th><fmt:message key="label.phoneNumber"/></th>
                <th><fmt:message key="label.email"/></th>
                <th><fmt:message key="label.cargoName"/></th>
                <th><fmt:message key="label.route"/></th>
                <th><fmt:message key="label.price"/></th>
                <th><fmt:message key="label.orderPlacementTime"/></th>
                <th><fmt:message key="label.pickUpDate"/></th>
                <th><fmt:message key="label.dateOfArrival"/></th>
                <th><fmt:message key="label.orderStatus"/></th>

            </tr>
            <c:forEach items="${allOrders}" var="orders">
                <tr>
                    <td>
                        <c:forEach items="${userList}" var="user">
                            <c:if test="${user.id==orders.userId}">
                                ${user.name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${userList}" var="user">
                            <c:if test="${user.id==orders.userId}">
                                ${user.surname}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${userList}" var="user">
                            <c:if test="${user.id==orders.userId}">
                                ${user.phoneNumber}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <c:forEach items="${userList}" var="user">
                            <c:if test="${user.id==orders.userId}">
                                ${user.email}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                            ${orders.cargoName}
                    </td>

                    <c:forEach items="${routeList}" var="route">
                        <c:if test="${route.id==orders.routeId}">
                            <td>${route.start}-${route.destination}</td>
                            <c:forEach items="${tariffList}" var="tariff">
                                <c:if test="${tariff.id == orders.tariffId}">
                                    <td>
                                            ${route.length*tariff.pricePerKm}
                                    </td>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                    <td>

                            ${orders.orderPlacementTime}

                    </td>
                    <td>

                            ${orders.pickUpDate}

                    </td>
                    <td>

                            ${orders.dateOfArrival}

                    </td>
                    <td>
                            ${orders.orderStatus}
                    </td>

                </tr>
            </c:forEach>
            <tr>
                <td>
                    <form action="controller" method="get">
                        <input hidden name="command" value="goToManagerOrdersPage">
                        <input type="hidden" name="goToPage" value="${currentManagerOrdersPage-1}">
                        <input
                        <c:if test="${currentManagerOrdersPage==0}"> disabled </c:if> type="submit"
                                                                     value="<fmt:message key="label.previousPageButton"/>">
                    </form>
                </td>
                <td>
                    <form action="controller" method="get">
                        <input hidden name="command" value="goToManagerOrdersPage">
                        <select name="goToPage">
                            <c:forEach begin="${1}" end="${managerOrdersPageCount}" var="managerOrderPageNumber">
                                <option value="${managerOrderPageNumber-1}"
                                        <c:if test="${currentManagerOrdersPage==managerOrderPageNumber-1}">selected</c:if>>${managerOrderPageNumber}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="<fmt:message key="label.goToPageButton"/>">
                    </form>
                </td>
                <td>
                    <form action="controller" method="get">
                        <input hidden name="command" value="goToManagerOrdersPage">
                        <input type="hidden" name="goToPage" value="${currentManagerOrdersPage+1}">
                        <input
                        <c:if test="${currentManagerOrdersPage==managerOrdersPageCount-1}"> disabled </c:if>
                                                                                            type="submit"
                                                                                            value="<fmt:message key="label.nextPageButton"/>">
                    </form>
                </td>
            </tr>
        </table>
    </div>


</c:if>

</body>
</html>