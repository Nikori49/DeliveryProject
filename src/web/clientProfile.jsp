<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cust" uri="WEB-INF/customLib.tld" %>

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
<c:if test="${loggedUser.role != 'client'}">
    <c:redirect url="home.jsp"></c:redirect>
</c:if>
<%--Language selection block--%>
<div style="border: white; position:absolute; top:0;right: 18%;">

    <table style="border-collapse: collapse">
        <tr>
            <td style="padding: unset">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="changeLanguage">
                    <input type="hidden" name="changeLanguageTo" value="ru">
                    <input type="hidden" name="returnTo" value="clientProfile.jsp">
                    <input type="submit"
                           <c:if test="${language=='ru'}">disabled</c:if> value="Ru">
                </form>
            </td>
            <td style="padding: unset">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="changeLanguage">
                    <input type="hidden" name="changeLanguageTo" value="en">
                    <input type="hidden" name="returnTo" value="clientProfile.jsp">
                    <input type="submit"
                           <c:if test="${language=='en'}">disabled</c:if> value="En">
                </form>
            </td>
        </tr>
    </table>


</div>
<div style="position: absolute; top: 0%; right: 0%;">
    <fmt:message key="label.welcome"/> ${loggedUser.name}!
    <a href="home.jsp"><fmt:message key="label.backToMain"/> </a>
    <form action="controller" method="get">
        <input hidden name="command" value="logout">
        <input type="submit" value="<fmt:message key="label.logoutButton"/> ">
    </form>
</div>
<c:if test="${clientsOrders == null}">
    <div><fmt:message key="label.noOrders"/></div>
</c:if>

<c:if test="${clientsOrders != null}">
    <c:if test="${clientsOrdersAwaitingPayment != null}">
        <div >
            <fmt:message key="label.orderAwaitingPayment"/>
            <table style="border: black; backface-visibility: visible; border-bottom-width: 1px" >
                <tr>
                    <th><fmt:message key="label.orderId"/></th>
                    <th><fmt:message key="label.orderedCargoName"/></th>
                    <th><fmt:message key="label.cargoMass"/></th>
                    <th><fmt:message key="label.tariff"/></th>
                    <th><fmt:message key="label.route"/></th>
                    <th><fmt:message key="label.orderedDeliveryAddress"/></th>
                    <th><fmt:message key="label.dateOfArrival"/></th>
                    <th><fmt:message key="label.orderPlacementTime"/></th>
                    <th><fmt:message key="label.orderStatus"/></th>
                    <th><fmt:message key="label.price"/></th>
                    <th><fmt:message key="label.actions"/></th>
                </tr>
                <c:forEach items="${clientsOrdersAwaitingPayment}" var="unpayed">
                    <tr>
                        <td>${unpayed.id}</td>
                        <td>${unpayed.cargoName}</td>
                        <td>${unpayed.cargoMass}</td>
                        <td><c:forEach items="${tariffList}" var="tariff">
                            <c:if test="${tariff.id == unpayed.tariffId}">
                                ${tariff.name}
                            </c:if>
                        </c:forEach></td>
                        <td><c:forEach items="${routeList}" var="route">
                            <c:if test="${route.id == unpayed.routeId}">
                                ${route.start}-${route.destination}
                            </c:if>
                        </c:forEach></td>
                        <td>${unpayed.deliveryAddress}</td>
                        <td>${unpayed.dateOfArrival}</td>
                        <td>${unpayed.orderPlacementTime}</td>
                        <td>${unpayed.orderStatus}</td>

                        <td>
                            <c:forEach items="${routeList}" var="route">
                                <c:if test="${route.id == unpayed.routeId}">
                                    <c:forEach items="${tariffList}" var="tariff">
                                        <c:if test="${tariff.id == unpayed.tariffId}">
                                            ${tariff.pricePerKm*route.length}
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach><fmt:message key="label.hrn"/>
                        </td>
                        <td>
                            <form action="controller" method="get">
                                <input hidden name="command" value="payForOrder">
                                <input hidden name="orderId" value="${unpayed.id}">

                                <input type="submit" value="<fmt:message key="label.payButton"/>">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
    <div><fmt:message key="label.yourOrders"/>
        <table>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <form action="controller" method="get">
                        <input hidden name="command" value="sortClientOrderList">
                        <input hidden name="clientOrderListSortedBy" value="orderPlacementTimeAscending">
                        <input type="submit" value="<fmt:message key="label.sortAscendingButton"/>">
                    </form>
                    <form action="controller" method="get">
                        <input hidden name="command" value="sortClientOrderList">
                        <input hidden name="clientOrderListSortedBy" value="orderPlacementTimeDescending">
                        <input type="submit" value="<fmt:message key="label.sortDescendingButton"/>">
                    </form>
                </td>
                <td>
                    <form action="controller" method="get">
                        <input hidden name="command" value="sortClientOrderList">
                        <input hidden name="clientOrderListSortedBy" value="statusAscending">
                        <input type="submit" value="<fmt:message key="label.sortAscendingButton"/>">
                    </form>
                    <form action="controller" method="get">
                        <input hidden name="command" value="sortClientOrderList">
                        <input hidden name="clientOrderListSortedBy" value="statusDescending">
                        <input type="submit" value="<fmt:message key="label.sortDescendingButton"/>">
                    </form>
                </td>
            </tr>
            <tr>
                <th><fmt:message key="label.orderId"/></th>
                <th><fmt:message key="label.orderedCargoName"/></th>
                <th><fmt:message key="label.cargoMass"/></th>
                <th><fmt:message key="label.tariff"/></th>
                <th><fmt:message key="label.route"/></th>
                <th><fmt:message key="label.orderedDeliveryAddress"/></th>
                <th><fmt:message key="label.pickUpDate"/></th>
                <th><fmt:message key="label.dateOfArrival"/></th>
                <th><fmt:message key="label.orderPlacementTime"/></th>
                <th><fmt:message key="label.orderStatus"/></th>
            </tr>
            <c:forEach items="${clientsOrders}" var="clientOrder">
                <tr>
                    <td>${clientOrder.id}</td>
                    <td>${clientOrder.cargoName}</td>
                    <td>${clientOrder.cargoMass}</td>
                    <td><c:forEach items="${tariffList}" var="tariff">
                        <c:if test="${tariff.id == clientOrder.tariffId}">
                            ${tariff.name}
                        </c:if>
                    </c:forEach></td>
                    <td><c:forEach items="${routeList}" var="route">
                        <c:if test="${route.id == clientOrder.routeId}">
                            ${route.start}-${route.destination}
                        </c:if>
                    </c:forEach></td>
                    <td>${clientOrder.deliveryAddress}</td>
                    <td>${clientOrder.pickUpDate}</td>
                    <td>${clientOrder.dateOfArrival}</td>
                    <td>${clientOrder.orderPlacementTime}</td>
                    <td>${clientOrder.orderStatus}</td>

                </tr>
            </c:forEach>

            <tr>
                <td>
                    <form action="controller" method="get">
                        <input hidden name="command" value="goToClientsOrdersPage">
                        <input type="hidden" name="goToPage" value="${currentClientsOrdersPage-1}">
                        <input
                        <c:if test="${currentClientsOrdersPage==0}"> disabled </c:if> type="submit" value="<fmt:message key="label.previousPageButton"/>">
                    </form>
                </td>
                <td>
                    <form action="controller" method="get">
                        <input hidden name="command" value="goToClientsOrdersPage">
                        <select name="goToPage">
                            <c:forEach begin="${1}" end="${clientsOrdersPageCount}" var="clientOrderPageNumber">
                                <option value="${clientOrderPageNumber-1}"
                                        <c:if test="${currentClientsOrdersPage==clientOrderPageNumber-1}">selected</c:if>>${clientOrderPageNumber}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="<fmt:message key="label.goToPageButton"/>">
                    </form>
                </td>
                <td>
                    <form action="controller" method="get">
                        <input hidden name="command" value="goToClientsOrdersPage">
                        <input type="hidden" name="goToPage" value="${currentClientsOrdersPage+1}">
                        <input
                        <c:if test="${currentClientsOrdersPage==clientsOrdersPageCount-1}"> disabled </c:if> type="submit" value="<fmt:message key="label.goToPageButton"/>">
                    </form>
                </td>
            </tr>
        </table>
    </div>

    <div style="position:absolute; right:0px; bottom:0px;">
        <fmt:message key="label.contactUs"/>
        <cust:supportTag/>
    </div>

</c:if>
</body>
</html>

