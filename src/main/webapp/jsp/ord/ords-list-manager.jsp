<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Ord list</title>
    <jsp:include page="../header.jsp"/>
</head>
<body>
<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Ord list</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="/add-ord">Add ord</a></li>
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

        <div style="margin-bottom: 20px; margin-top: 50px;">
            <a href="/list-ord-manager" class="btn btn-default">all</a>
            <a href="/list-ord-manager?ordStatus=ASSIGNED" class="btn btn-default">assigned</a>
            <a href="/list-ord-manager?ordStatus=IN_QUEUE" class="btn btn-default">in queue</a>
            <a href="/list-ord-manager?ordStatus=IN_TRANSIT" class="btn btn-default">in transit</a>
            <a href="/list-ord-manager?ordStatus=DONE" class="btn btn-default">done</a>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Order number</th>
                <th scope="col">Date</th>
                <th scope="col">Rote</th>
                <th scope="col">Car type</th>
                <th scope="col">Car model</th>
                <th scope="col">Driver</th>
                <th scope="col">Order status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ord" items="${requestScope.newListOfOrds}">
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
                        <a class="btn btn-warning" href="/update-ord?id=${ord.id}">edit</a><a></a>
                    </td>
                    <td>
                        <a class="btn btn-danger" href="/delete-ord?id=${ord.id}">delete</a><a></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
