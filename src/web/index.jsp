<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cust" uri="WEB-INF/customLib.tld" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="HomePage"/>

<html lang="${language}">
<head>
    <title><fmt:message key="label.homeTitle"/> </title>
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
</head>
<body>

<%--Language selection block--%>
<div style="border: white; position:absolute; top:0;right: 18%;" >

    <table style="border-collapse: collapse" >
        <tr>
            <td style="padding: unset">
                <form action="controller" method="get">
                <input type="hidden" name="command" value="changeLanguage">
                <input type="hidden" name="changeLanguageTo" value="ru">
                <input type="hidden" name="returnTo" value="index.jsp">
                <input type="submit"  <c:if test="${language=='ru'}">disabled</c:if>  value="Ru">
            </form>
            </td>
            <td style="padding: unset">
                <form action="controller" method="get">
                <input type="hidden" name="command" value="changeLanguage">
                <input type="hidden" name="changeLanguageTo" value="en">
                <input type="hidden" name="returnTo" value="index.jsp">
                <input type="submit" <c:if test="${language=='en'}">disabled</c:if>  value="En">
            </form>
            </td>
        </tr>
    </table>



</div>

<%--Calculator and order placement block--%>
<div style="position:absolute; bottom:5%;">
    <c:if test="${orderPlaced != null}">
        <fmt:message key="label.thankYouMessage"/>
    </c:if>
    <c:if test="${selectedStart == null}">
    <c:forEach items="${calculatorError}" var="error">
        ${error}
    </c:forEach>
    <form action="controller" method="get">
        <input type="hidden" name="command" value="getAvailableDestinations">
        <fmt:message key="label.start"/><select name="start">
        <c:forEach items="${cityList}" var="city">
            <option value="${city}" selected>${city}</option>
        </c:forEach>
    </select>
        <input type="submit" value="<fmt:message key="label.getAvailableDestinationsButton"/>">
        <fmt:message key="label.destination"/><select disabled>
        <option disabled><fmt:message key="label.destination"/></option>
    </select>

        <fmt:message key="label.height"/><input disabled name="expectedHeight">
        <fmt:message key="label.length"/><input disabled name="expectedLength">
        <fmt:message key="label.width"/><input disabled name="expectedWidth">

        <fmt:message key="label.mass"/><input disabled name="expectedMass">
        <input disabled type="submit" value="<fmt:message key="label.calculateExpectedPriceButton"/>">
    </form>
    <c:if test="${expectedPrice != null}">
    <fmt:message key="label.expectedPrice"/>${expectedPrice}<fmt:message key="label.hrn"/> <fmt:message key="label.bestValue"/>${bestValueTariff.name}.
    <c:if test="${loggedUser.role == 'client' && orderPlaced== null}">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="placeOrder">
            <fmt:message key="label.cargoName"/><input name="cargoName">
            <fmt:message key="label.deliveryAddress"/><input name="deliveryAddress">
            <fmt:message key="label.pickUpDate"/><input type="date" name="pickUpDate" min="${todayDate}">
        <input type="submit" value="<fmt:message key="label.placeOrderButton"/>">

        </c:if>
        </c:if>

        </c:if>
        <c:if test="${selectedStart != null}">
        <c:forEach items="${calculatorError}" var="error">
            ${error}
        </c:forEach>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="calculateExpectedPrice">
            <fmt:message key="label.start"/><select disabled>
            <option value="${selectedStart}">${selectedStart}</option>
        </select>
            <input disabled type="submit" value="<fmt:message key="label.getAvailableDestinationsButton"/>">
            <fmt:message key="label.destination"/><select name="destination">
            <c:forEach items="${availableDestinations}" var="destination">
                <option value="${destination}" selected>${destination}</option>
            </c:forEach>
        </select>
            <fmt:message key="label.height"/><input name="expectedHeight">
            <fmt:message key="label.length"/><input name="expectedLength">
            <fmt:message key="label.width"/><input name="expectedWidth">
            <fmt:message key="label.mass"/><input name="expectedMass">
            <input type="submit" value="<fmt:message key="label.calculateExpectedPriceButton"/>">
        </form>
        </c:if>

</div>


<%--Login block--%>
<div style="position:absolute; right:0px; top:0px;">

    <c:if test="${loggedUser == null }">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="login"><br>
            <table>
                <tr>
                    <td>
                        <fmt:message key="label.login"/>
                    </td>
                    <td>
                        <input name="login">
                    </td>
                </tr>
                <tr>
                    <td>
                        <fmt:message key="label.password"/>
                    </td>
                    <td>
                        <input type="password" name="password"><br>
                    </td>
                </tr>
            </table>
            <input type="submit" value="<fmt:message key="label.loginButton"/>"><a href="register.jsp"><fmt:message key="label.createAccountButton"/></a>
        </form>
        ${loginError}
    </c:if>
    <c:if test="${loggedUser != null}">
        <c:if test="${loggedUser.role == 'client'}">
            <fmt:message key="label.welcome"/>${loggedUser.name}!
            <form action="controller" method="get">
                <input type="hidden" name="command" value="clientProfile">
                <input type="submit" value="<fmt:message key="label.yourProfileButton"/>">
            </form>
        </c:if>
        <c:if test="${loggedUser.role == 'manager' }">
            Welcome, ${loggedUser.name}!
            <form action="controller" method="get">
                <input type="hidden" name="command" value="managerProfile">
                <input type="submit" value="<fmt:message key="label.yourProfileButton"/>">
            </form>
        </c:if>

        <form action="controller" method="get">
            <input type="hidden" name="command" value="logout">
            <input type="submit" value="<fmt:message key="label.logoutButton"/>">
        </form>
    </c:if>
</div>

<%--Route table block--%>
<div style="position:absolute; left:0px; bottom: 330px;">
    <table>

        <tr>
            <form action="controller" method="get">
                <input type="hidden" name="command" value="filterList">
                <td>
                    <input name="startFilter">
                </td>
                <td>
                    <input name="destinationFilter">
                </td>
                <td>
                    <input type="submit" value="<fmt:message key="label.filterButton"/>">
                </td>

            </form>

        </tr>
        <tr>
            <td>
                <c:if test="${listSortedBy=='startDescending' || listSortedBy==null || listSortedBy=='destinationDescending' || listSortedBy=='destinationAscending'}">
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="sortRouteTable">
                        <input type="hidden" name="listSortedBy" value="startAscending">
                        <input type="submit" value="<fmt:message key="label.sortAscendingButton"/>">
                    </form>
                </c:if>
                <c:if test="${listSortedBy=='startAscending'}">
                    <form action="controller" method="get">
                        <input type="hidden" name="command" value="sortRouteTable">
                        <input type="hidden" name="listSortedBy" value="startDescending">

                        <input type="submit" value="<fmt:message key="label.sortDescendingButton"/>">
                    </form>
                </c:if>
            </td>
            <td>
                <c:if test="${listSortedBy=='destinationDescending' || listSortedBy==null || listSortedBy=='startDescending' || listSortedBy=='startAscending'}">
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="sortRouteTable">
                    <input type="hidden" name="listSortedBy" value="destinationAscending">
                    <input type="submit" value="<fmt:message key="label.sortAscendingButton"/>"
                    </c:if>
                    <c:if test="${listSortedBy=='destinationAscending'}">
                    <form action="controller" method="get">

                        <input type="hidden" name="command" value="sortRouteTable">
                        <input type="hidden" name="listSortedBy" value="destinationDescending">
                        <input type="submit" value="<fmt:message key="label.sortDescendingButton"/>">
                    </form>
                    </c:if>
            </td>
            <td>
                <%--
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="resetList">
                    <input type="submit" value="Reset List">
                </form>
                --%>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="label.startHeader"/></th>
            <th><fmt:message key="label.destinationHeader"/></th>
            <th><fmt:message key="label.lengthHeader"/></th>
        </tr>

        <c:forEach items="${routeListPage}" var="route">

            <tr>
                <td>${route.start}</td>
                <td>${route.destination}</td>
                <td>${route.length}km</td>
            </tr>


        </c:forEach>


        <tr>
            <td>
                <form action="controller" method="get">
                    <input hidden name="command" value="goToRoutePage">
                    <input type="hidden" name="goToPage" value="${currentRouteTablePageNumber-1}">
                    <input
                    <c:if test="${currentRouteTablePageNumber==0}"> disabled </c:if> type="submit" value="<fmt:message key="label.previousPageButton"/>">
                </form>
            </td>
            <td>
                <form action="controller" method="get">
                    <input hidden name="command" value="goToRoutePage">
                    <select name="goToPage">
                        <c:forEach begin="${1}" end="${routeTablePageQuantity}" var="routePageNumber">
                            <option value="${routePageNumber-1}"
                                    <c:if test="${currentRouteTablePageNumber==routePageNumber-1}">selected</c:if>>${routePageNumber}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="<fmt:message key="label.goToPageButton"/>">
                </form>
                <form action="controller" method="get">
                    <input hidden name="command" value="setItemsPerRoutePage">
                    <select name="itemsPerPage">
                        <c:forEach begin="${3}" end="${10}" var="itemsPerPage">
                            <option value="${itemsPerPage}"
                                    <c:if test="${itemsPerPage==itemsPerRoutePage}">selected</c:if>>${itemsPerPage}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="<fmt:message key="label.changeItemsPerPageButton"/>">
                </form>
            </td>
            <td>
                <form action="controller" method="get">
                    <input hidden name="command" value="goToRoutePage">
                    <input type="hidden" name="goToPage" value="${currentRouteTablePageNumber+1}">
                    <input
                    <c:if test="${currentRouteTablePageNumber==routeTablePageQuantity-1}"> disabled </c:if> type="submit" value="<fmt:message key="label.nextPageButton"/>">
                </form>
            </td>
        </tr>
    </table>
</div>


<%--Tariff table block--%>
<div style="position:absolute; left:0px; top:0px;">
    <table>
        <tr>
            <th><fmt:message key="label.tariffNameHeader"/></th>
            <th><fmt:message key="label.cargoHoldHeightHeader"/></th>
            <th><fmt:message key="label.cargoHoldLengthHeader"/></th>
            <th><fmt:message key="label.cargoHoldWidthHeader"/></th>
            <th><fmt:message key="label.cargoMassCapHeader"/></th>
            <th><fmt:message key="label.deliveryRangeHeader"/></th>
            <th><fmt:message key="label.pricePerKmHeader"/></th>
        </tr>
        <c:forEach items="${tariffList}" var="tariff">

            <tr>
                <td>${tariff.name}</td>
                <td>${tariff.cargoHoldHeight}m</td>
                <td>${tariff.cargoHoldLength}m</td>
                <td>${tariff.cargoHoldWidth}m</td>
                <td>${tariff.cargoMassCap}kg</td>
                <td>${tariff.deliveryRange}</td>
                <td>${tariff.pricePerKm}hrn</td>
            </tr>


        </c:forEach>
    </table>
</div>

<div style="position:absolute; right:0px; bottom:0px;">
    <fmt:message key="label.contactUs"/>
        <cust:supportTag/>
</div>


</body>
</html>
