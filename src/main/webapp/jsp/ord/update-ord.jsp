<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit order</title>
    <jsp:include page="../header.jsp"/>
</head>
<body>

<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/list-ord-manager">Order list</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="/add-ord">Add order</a></li>
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
        <h1>Edit order</h1>

        <div style="margin-bottom: 20px; margin-top: 50px;">
            <form action="/update-ord" method="post">
                <label style="line-height:1.6">rote</label>
                <input name="rout" size="15" type="text" value="${requestScope.updatedOrd.rout}"/>
                <div>
                    <label style="line-height:1.6">car type</label>
                    <c:choose>
                        <c:when test="${requestScope.updatedCar.carTypeLorry}">
                            <select name="carTypeLorry">
                                <option value="false">passenger car</option>
                                <option selected="selected" value="true">lorry</option>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <select name="carTypeLorry">
                                <option selected="selected" value="false">passenger car</option>
                                <option value="true">lorry</option>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div>
                    <label style="line-height:1.6">car and driver</label>
                    <select name="carId">
                        <c:forEach var="car" items="${requestScope.newListOfCars}">
                            <c:choose>
                                <c:when test="${car.id == requestScope.updatedOrd.car.id}">
                                    <option selected="selected" value="${car.id}">${car.model}
                                        - ${car.cabDriver.login}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${car.id}">${car.model}
                                        - ${car.cabDriver.login}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
                <label style="line-height:1.6">order status</label>
                <c:choose>
                <c:when test="${requestScope.updatedOrd.ordStatus == 'ASSIGNED'}">
                <select name="ordStatus">
                    <option selected="selected" value="ASSIGNED">assigned</option>
                    <option value="IN_QUEUE">in queue</option>
                </select>
                </c:when>
                <c:when test="${requestScope.updatedOrd.ordStatus == 'IN_QUEUE'}">
                <select name="ordStatus">
                    <option value="ASSIGNED">assigned</option>
                    <option selected="selected" value="IN_QUEUE">in queue</option>
                </select>
                </c:when>
                <c:otherwise>
                <select name="ordStatus">
                    <option value="ASSIGNED">assigned</option>
                    <option value="IN_QUEUE">in queue</option>
                </select>
                </c:otherwise>
                </c:choose>
        </div>
        <input value="${requestScope.updatedOrd.id}" type="hidden" name="id">
        <input name="Save" type="submit" value="Save"/>
    </div>
</div>
</body>
</html>


