<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Журнал рейсов</title>
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
            <ul class="nav navbar-nav">
                <a class="active" class="navbar-brand" href="#">Журнал рейсов</a>
            </ul>
            <ul class="nav navbar-nav">
                <a class="navbar-brand" href="/jsp/add-car.jsp">Добавить машину</a>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="https://ya.ru/">Выход</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#En|Ru">En|Ru</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="/list-car">Журнал машин</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="https://ya.ru/">Выход</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#En|Ru">En|Ru</a></li>
            </ul>
        </div>


    </div>
</div>

<div class="container">


    <div class="text-center">


        <h1>Журнал рейсов</h1>

        <table class="table" style="width:500px">
            <thead>
            <tr>

                <th scope="col">Номер заявки</th>
                <th scope="col">Дата</th>
                <th scope="col">Маршрут</th>
                <th scope="col">Тип машины</th>
                <th scope="col">Марка машины</th>
                <th scope="col">Номер машины</th>
                <th scope="col">Статус</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach var="trip" items="${requestScope.newListOfTrips}">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${trip.tripStatus}">
                                выполнен
                            </c:when>
                            <c:otherwise>
                                в пути
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:out value="${trip.date}"/></td>
                    <td><c:out value="${trip.rout}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${trip.tripStatus}">
                                <a class="btn btn-default" href="/change-trip-status?id=${trip.id}">выполнен</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-info" href="/change-trip-status?id=${trip.id}">в пути</a>
                            </c:otherwise>
                        </c:choose>
                        <a></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

</div>

</body>
</html>
