<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Car list</title>

    <jsp:include page="../header.jsp"/>
</head>
<body>


<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/list-ord-driver">Order list</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Car list</a></li>
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


        <h1>Car list</h1>

        <c:choose>
            <c:when test="${requestScope.error != null}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${requestScope.error}"/>
                </div>
            </c:when>
        </c:choose>

        <div style="margin-top: 50px;">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Car type</th>
                <th scope="col">Car model</th>
                <th scope="col">Licence plate</th>
                <th scope="col">Serviceability status</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="car" items="${requestScope.newListOfCars}">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${car.carTypeLorry}">
                                грузовая
                            </c:when>
                            <c:otherwise>
                                легковая
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:out value="${car.model}"/></td>
                    <td><c:out value="${car.licencePlate}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${car.carStatus}">
                                OK
                            </c:when>
                            <c:otherwise>
                                defect
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${car.carStatus}">
                                <a class="btn btn-default" href="/change-car-status?id=${car.id}">crush</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-info" href="/change-car-status?id=${car.id}">fix</a>
                            </c:otherwise>
                        </c:choose>
                        <a></a>
                    </td>
                    <td>
                        <a class="btn btn-warning" href="/update-car?id=${car.id}">edit</a><a></a>
                    </td>
                    <td>
                        <a class="btn btn-danger" href="/delete-car?id=${car.id}">delete</a><a></a>
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