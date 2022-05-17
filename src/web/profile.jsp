<%--
  Created by IntelliJ IDEA.
  User: yohoh
  Date: 08.02.2022
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ts" tagdir="/WEB-INF/tags"  %>
<html>
<head>
    <title>Greetings,${loggedUser.name}</title>
    <ts:tableStyle/>
</head>
<body>
    <c:if test="${loggedUser.role == 'client'}">
        <c:if test="${clientsOrders == null}">
            <div>You've yet to place your first order.</div>
        </c:if>
        <c:if test="${clientsOrders != null}">
        <div>Your order(s).
            <table>
                <tr>
                    <th>Order №</th>
                    <th>Cargo name</th>
                    <th>Cargo mass</th>
                    <th>Tariff</th>
                    <th>Route</th>
                    <th>Delivery address</th>
                    <th>Pick up date</th>
                    <th>Date of arrival</th>
                    <th>Order placement time</th>
                    <th>Order status</th>
                </tr>
                <c:forEach items="${clientsOrders}" var="clientOrder">
                    <tr>
                        <td>${clientOrder.id}</td>
                        <td>${clientOrder.cargoName}</td>
                        <td>${clientOrder.cargoMass}</td>
                        <td><c:forEach items="${tariffList}" var="tariff" >
                            <c:if test="${tariff.id == clientOrder.tariffId}">
                                ${tariff.name}
                            </c:if>
                        </c:forEach></td>
                        <td><c:forEach items="${routeList}" var="route" >
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
            </table>
        </div>
            <c:if test="${clientsOrdersAwaitingPayment != null}">
                <div>
                    <table>
                        <tr>
                            <th>Order №</th>
                            <th>Cargo name</th>
                            <th>Cargo mass</th>
                            <th>Tariff</th>
                            <th>Route</th>
                            <th>Delivery address</th>
                            <th>Date of arrival</th>
                            <th>Order placement time</th>
                            <th>Order status</th>
                        </tr>
                    <c:forEach items="${clientsOrdersAwaitingPayment}" var="unpayed">
                        <tr>
                            <td>${unpayed.id}</td>
                            <td>${unpayed.cargoName}</td>
                            <td>${unpayed.cargoMass}</td>
                            <td><c:forEach items="${tariffList}" var="tariff" >
                                <c:if test="${tariff.id == unpayed.tariffId}">
                                    ${tariff.name}
                                </c:if>
                            </c:forEach></td>
                            <td><c:forEach items="${routeList}" var="route" >
                                <c:if test="${route.id == unpayed.routeId}">
                                    ${route.start}-${route.destination}
                                </c:if>
                            </c:forEach></td>
                            <td>${unpayed.deliveryAddress}</td>
                            <td>${unpayed.dateOfArrival}</td>
                            <td>${unpayed.orderPlacementTime}</td>
                            <td>${unpayed.orderStatus}</td>
                        </tr>
                        <tr>
                            <td>Price =
                            <c:forEach items="${routeList}" var="route" >
                                <c:if test="${route.id == unpayed.routeId}">
                                    <c:forEach items="${tariffList}" var="tariff" >
                                        <c:if test="${tariff.id == unpayed.tariffId}">
                                            ${tariff.pricePerKm*route.length}
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>hrn.
                            </td>
                            <td>
                                <form action="controller" method="post">
                                    <input hidden name="command" value="payForOrder">
                                    <input hidden name="orderId" value="${unpayed.id}">

                                    <input type="submit" value="Pay">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </table>
                </div>
            </c:if>



        </c:if>





    </c:if>

    <c:if test="${loggedUser.role == 'manager'}">
        <div style = "position:absolute; top:0; left:0;" >
        <c:if test="${ordersPendingApproval!=null}">
            Orders pending approval.
        <table>
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Phone number</th>
                <th>E-mail</th>
                <th>Cargo name</th>
                <th>Route</th>
                <th>Price</th>
                <th>Order placement time</th>
                <th>Pick up date</th>
                <th>Date of arrival</th>
                <th>Status</th>
                <th>Actions</th>
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
                        pending approval
                    </td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="approveOrder">
                            <input type="hidden" name="orderId" value="${orders.id}">
                            <input type="submit" value="Approve">
                        </form>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="terminateOrder">
                            <input type="hidden" name="orderId" value="${orders.id}">
                            <input type="submit" value="Terminate">
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>
        </c:if>
            <c:if test="${ordersInProgress!=null}">
            Orders in progress.
            <table>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Phone number</th>
                    <th>E-mail</th>
                    <th>Cargo name</th>
                    <th>Route</th>
                    <th>Price</th>
                    <th>Order placement time</th>
                    <th>Pick up date</th>
                    <th>Date of arrival</th>
                    <th>Status</th>
                    <th>Actions</th>
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
                            in progress
                        </td>
                        <td>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="finishOrder">
                                <input type="hidden" name="orderId" value="${orders.id}">
                                <input type="submit" value="Finish">
                            </form>
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="terminateOrder">
                                <input type="hidden" name="orderId" value="${orders.id}">
                                <input type="submit" value="Terminate">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            </c:if>
            All orders.
            <table>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Phone number</th>
                    <th>E-mail</th>
                    <th>Cargo name</th>
                    <th>Route</th>
                    <th>Price</th>
                    <th>Order placement time</th>
                    <th>Pick up date</th>
                    <th>Date of arrival</th>
                    <th>Status</th>

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
            </table>
        </div>




    </c:if>



</body>
</html>
