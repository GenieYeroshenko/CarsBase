<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавление заявки</title>
    <jsp:include page="../header.jsp"/>
</head>
<body>

<form action="/add-ord" method="post">
    <div class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/list-ord-manager">Журнал заявок</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Добавить заявку</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/logout">Выход</a></li>
                </ul>
                <p class="navbar-text navbar-right">${sessionScope.authorizedUser.login}</p>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="text-center">
            <h1>Добавление заявки</h1>

            <h1></h1>

            <div>
                <label style="line-height:1.6">Маршрут</label>
                <input name="rout" size="15" type="text"/>
            </div>
            <div>
                <label style="line-height:1.6">Тип машины</label>
                <select name="carTypeLorry">
                    <option value="false">легковая</option>
                    <option value="true">грузовая</option>
                </select>
            </div>
            <label style="line-height:1.6">Машина и водитель</label>
            <select name="carId">
                <c:forEach var="car" items="${requestScope.newListOfCars}">
                    <option value="${car.id}">${car.licencePlate} - ${car.cabDriver.login}</option>
                </c:forEach>
            </select>

            <div>
                <label style="line-height:1.6">Статус заявки</label>
                <select name="ordStatus">
                    <option value="ASSIGNED">назначена</option>
                    <option value="IN_QUEUE">не назначена</option>
                </select>

                <p>&nbsp;</p>

                <input name="Save" type="submit" value="Сохранить"/>
            </div>
        </div>
    </div>
</form>
</body>
</html>