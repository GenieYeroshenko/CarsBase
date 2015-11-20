<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Журнал рейсов</title>
    <jsp:include page="../header.jsp"/>
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
                <li class="active"><a href="#">Журнал рейсов</a></li>
            </ul>

            <a class="navbar-brand" href="/list-car">Журнал машин</a>

            <ul class="nav navbar-nav">
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#En|Ru">En|Ru</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="/">Выход</a></li>
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
                <th scope="col"></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="trip" items="${requestScope.newListOfTrips}">
            <tr>
                <td><c:out value="${trip.ord.id}"/></td>
                <td><c:out value="${trip.date}"/></td>
                <td><c:out value="${trip.ord.rout}"/></td>
                <td><c:out value="${trip.ord.car.carTypeLorry}"/></td>
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
                <td><c:out value="${trip.ord.car.model}"/></td>
                <td><c:out value="${trip.ord.car.licencePlate}"/></td>
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

                <td>
                    <c:choose>
                        <c:when test="${trip.tripStatus}">
                            <a class="btn btn-default" href="/change-trip-status?id=${trip.id}">в пути</a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-info" href="/change-trip-status?id=${trip.id}">выполнен</a>
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
