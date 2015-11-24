<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Order list</title>
    <jsp:include page="../header.jsp"/>
</head>
<body>
<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Order list</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="/list-car">Car list</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="/add-car">Add car</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout">Exit</a></li>
            </ul>
            <p class="navbar-text navbar-right">${sessionScope.authorizedUser.login}</p>
        </div>
    </div>
</div>
<div class="container">
    <div class="text-center">
        <h1>Order list</h1>

        <div style="margin-top: 50px;">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Order number</th>
                <th scope="col">Date</th>
                <th scope="col">Rout</th>
                <th scope="col">Car type</th>
                <th scope="col">Car model</th>
                <th scope="col">Driver</th>
                <th scope="col">Order status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ord" items="${requestScope.newListOfOrdsByDriver}">
                <tr>
                    <td><c:out value="${ord.id}"/></td>
                    <td><c:out value="${ord.date}"/></td>
                    <td><c:out value="${ord.rout}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${ord.carTypeLorry}">
                                lorry
                            </c:when>
                            <c:otherwise>
                                passenger car
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td><c:out value="${ord.car.model}"/></td>
                    <td><c:out value="${ord.car.cabDriver.login}"/></td>
                    <td>
                        <c:choose>
                        <c:when test="${ord.ordStatus == 'IN_QUEUE'}">
                            <c:out value="in queue"/>
                        </c:when>
                        <c:when test="${ord.ordStatus == 'ASSIGNED'}">
                            <c:out value="assigned"/>
                        </c:when>
                        <c:when test="${ord.ordStatus == 'IN_TRANSIT'}">
                            <c:out value="in transit"/>
                        </c:when>
                        <c:when test="${ord.ordStatus == 'DONE'}">
                            <c:out value="done"/>
                        </c:when>
                        </c:choose>
                    <td>


                    <td>
                        <a class="btn btn-warning" href="/change-ord-status?id=${ord.id}&status=IN_TRANSIT">set "in
                            transit"</a><a></a>
                    </td>
                    <td>
                        <a class="btn btn-danger" href="/change-ord-status?id=${ord.id}&status=DONE">set
                            "done"</a><a></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
</div>
</body>
</html>
