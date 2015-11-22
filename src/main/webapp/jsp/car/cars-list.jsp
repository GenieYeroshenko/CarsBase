<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Журнал машин</title>

    <jsp:include page="../header.jsp"/>
</head>
<body>


<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/list-ord-driver">Журнал заявок</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Журнал машин</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="/add-car">Добавить машину</a></li>
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


        <h1>Журнал машин</h1>

        <h1></h1>
        <table class="table" style="width:900px">
            <thead>
            <tr>
                <th scope="col">Тип машины</th>
                <th scope="col">Марка машины</th>
                <th scope="col">Номер машины</th>
                <th scope="col">Состояние машины</th>
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
                                кондиционная
                            </c:when>
                            <c:otherwise>
                                не кондиционная
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${car.carStatus}">
                                <a class="btn btn-default" href="/change-car-status?id=${car.id}">сломать</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-info" href="/change-car-status?id=${car.id}">починить</a>
                            </c:otherwise>
                        </c:choose>
                        <a></a>
                    </td>
                    <td>
                        <a class="btn btn-warning" href="/update-car?id=${car.id}">редактировать</a><a></a>
                    </td>
                    <td>
                        <a class="btn btn-danger" href="/delete-car?id=${car.id}">удалить</a><a></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>