<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add order</title>
    <jsp:include page="../header.jsp"/>
</head>
<body>

<form action="/add-ord" method="post">
    <div class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/list-ord-manager">Order list</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Add order</a></li>
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
            <h1>Add order</h1>

            <h1></h1>

            <div style="margin-bottom: 20px; margin-top: 50px;">
                <label style="line-height:1.6">rote</label>
                <input name="rout" size="15" type="text"/>
            </div>
            <div>
                <label style="line-height:1.6">car type</label>
                <select name="carTypeLorry">
                    <option value="false">passenger car</option>
                    <option value="true">lorry</option>
                </select>
            </div>
            <label style="line-height:1.6">car and driver</label>
            <select name="carId">
                <c:forEach var="car" items="${requestScope.newListOfCars}">
                    <option value="${car.id}">${car.model} - ${car.cabDriver.login}</option>
                </c:forEach>
            </select>

            <div>
                <label style="line-height:1.6">Order status</label>
                <select name="ordStatus">
                    <option value="ASSIGNED">assigned</option>
                    <option value="IN_QUEUE">in queue</option>
                </select>

                <p>&nbsp;</p>

                <input name="Save" type="submit" value="Save"/>
            </div>
        </div>
    </div>
</form>
</body>
</html>