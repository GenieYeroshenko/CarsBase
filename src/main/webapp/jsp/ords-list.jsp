<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Журнал заявок</title>
    <jsp:include page="header.jsp"/>
</head>

<body>
<div class="navbar navbar-default navbar-static-top">
    <div class="container">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>


        <div class="collapse navbar-collapse">
            <a class="navbar-brand" href="/jsp/ords-list-assigned.jsp">Назначенные</a>
            <a class="navbar-brand" href="/jsp/ords-list-in-transit.jsp">В пути</a>
            <a class="navbar-brand" href="/jsp/ords-list-done.jsp">Выполненные</a>
            <a class="navbar-brand" href="/jsp/ords-list-in-queue.jsp">Не назначенные</a>

            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Журнал заявок</a></li>
            </ul>

            <ul class="nav navbar-nav">
                <a class="navbar-brand" href="/add-ord">Добавить заявку</a>
            </ul>




            <ul class="nav navbar-nav navbar-right">
                <li><a href="#En|Ru">En|Ru</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="https://ya.ru/">Выход</a></li>
            </ul>

        </div>


    </div>
</div>

<div class="container">


    <div class="text-center">


        <h1>Журнал заявок</h1>

        <table class="table" style="width:500px">
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
                <td><c:out value="${ord.date}"/></td>
                <td><c:out value="${ord.date}"/></td>
                <td><c:out value="${ord.date}"/></td>
            <td><c:out value="${ord.ordStatus}"/></td>
                <td>
                    <a class="btn btn-warning" href="/update-ord?id=${ord.id}">редактировать</a>
                    <a></a>
                </td>
                <td>
                    <a class="btn btn-danger" href="/delete-ord?id=${ord.id}">удалить</a>
                    <a></a>
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
