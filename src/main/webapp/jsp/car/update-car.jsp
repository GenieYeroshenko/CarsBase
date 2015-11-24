<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit car</title>
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
        <h1>Edit car</h1>

        <div style="margin-bottom: 50px; margin-top: 50px;">
            <form action="/update-car" method="post">

                <div>
                    <label style="line-height:1.6">Car type</label>
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
                    <label style="line-height:1.6">car model</label>
                    <input name="model" size="15" type="text" value="${requestScope.updatedCar.model}"/>
                </div>

                <div>
                    <label style="line-height:1.6">licence plate</label>
                    <input name="licencePlate" size="15" type="text" value="${requestScope.updatedCar.licencePlate}"/>
                </div>

                <label style="line-height:1.6">serviceability status</label>
                <c:choose>
                <c:when test="${requestScope.updatedCar.carStatus}">
                <select name="carStatus">
                    <option value="false">defect</option>
                    <option selected="selected" value="true">OK</option>
                </select>
                </c:when>
                <c:otherwise>
                <select name="carStatus">
                    <option selected="selected" value="false">defect</option>
                    <option value="true">ОК</option>
                </select>
                </c:otherwise>
                </c:choose>
        </div>
        <input value="${requestScope.updatedCar.id}" type="hidden" name="id">
        <input name="Save" type="submit" value="Save"/>
    </div>
</div>

</body>
</html>


