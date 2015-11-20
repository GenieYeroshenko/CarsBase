<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Журнал заявок</title>
    <jsp:include page="../header.jsp"/>
</head>

<body>
<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav"><li class="active"><a href="#">Журнал заявок</a></li></ul>

            <a class="navbar-brand" href="/add-ord">Добавить заявку</a>
            <a class="navbar-brand" href="/list-ord-assigned">Назначенные</a>
            <a class="navbar-brand" href="/list-ord-in-transit">В пути</a>
            <a class="navbar-brand" href="/list-ord-done">Выполненные</a>
            <a class="navbar-brand" href="/list-ord-in-queue">Не назначенные</a>

            <ul class="nav navbar-nav navbar-right"><li><a href="#En|Ru">En|Ru</a></li></ul>
            <ul class="nav navbar-nav navbar-right"><li><a href="/">Выход</a></li></ul>
        </div>
    </div>
</div>

<div class="container">
    <div class="text-center">
        <h1>Журнал заявок</h1>
        <h1></h1>

        <table class="table" style="width:900px">
            <thead>
            <tr>
                <th scope="col">Номер заявки</th>
                <th scope="col">Дата</th>
                <th scope="col">Маршрут</th>
                <th scope="col">Тип машины</th>
                <th scope="col">Номер машины</th>
                <th scope="col">Водитель</th>
                <th scope="col">Статус заявки</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="ord" items="${requestScope.newListOfOrds}">
                <tr>
                    <td><c:out value="${ord.id}"/></td>
                    <td><c:out value="${ord.date}"/></td>
                    <td><c:out value="${ord.rout}"/></td>
                    <td><c:out value="${ord.carTypeLorry}"/></td>
                    <td><c:out value="${ord.car.licencePlate}"/></td>
                    <td><c:out value="${ord.car.cabDriver.name}"/></td>
                    <td><c:out value="${ord.ordStatus}"/></td>
                    <td>
                        <a class="btn btn-warning" href="/update-ord?id=${ord.id}">редактировать</a><a></a>
                    </td>
                    <td>
                        <a class="btn btn-danger" href="/delete-ord?id=${ord.id}">удалить</a><a></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</form>
</body>
</html>
