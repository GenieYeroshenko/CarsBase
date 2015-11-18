<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Редактирование заявки</title>
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
        <a class="navbar-brand" href="/list-trip">Журнал рейсов</a>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Журнал машин</a></li>

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
        <h1>Редактирование машины</h1>

        <form action="/update-ord" method="post">

            <div>
                <label style="line-height:1.6">Дата</label>
            </div>

            <div>
                <label style="line-height:1.6">Маршрут</label>
                <input name="rout" size="15" type="text" value="${requestScope.updatedOrd.rout}"/>
            </div>


            <div>
                <label style="line-height:1.6">Тип машины</label>
                <c:choose>
                    <c:when test="${requestScope.updatedCar.carTypeLorry}">
                        <select name="carTypeLorry">
                            <option value="">не выбран</option>
                            <option value="false">легковая</option>
                            <option selected="selected" value="true">грузовая</option>
                        </select>
                    </c:when>
                    <c:otherwise>
                        <select name="carTypeLorry">
                            <option value="">не выбран</option>
                            <option selected="selected" value="false">легковая</option>
                            <option value="true">грузовая</option>
                        </select>
                    </c:otherwise>
                </c:choose>
            </div>


            <div>
                <p>Машина</p>
                <select name="carId">
                    <c:forEach var="car" items="${requestScope.newListOfCars}">
                        <c:choose>
                            <c:when test="${car.id == requestScope.updatedOrd.car.id}">
                                <option selected="selected" value="${car.id}">${car.licencePlate}
                                    - ${car.cabDriver.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${car.id}">${car.licencePlate}
                                    - ${car.cabDriver.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>


            <label style="line-height:1.6">Статус заявки</label>


            <c:choose>

            <c:when test="${requestScope.updatedOrd.ordStatus == 'ASSIGNED'}">
            <select name="ordStatus">
                <option selected="selected" value="ASSIGNED">назначена</option>
                <option value="IN_QUEUE">не назначена</option>
                <option value="IN_TRANSIT">в пути</option>
                <option value="DONE">выполнена</option>
            </select>
            </c:when>
            <c:when test="${requestScope.updatedOrd.ordStatus == 'IN_QUEUE'}">
            <select name="ordStatus">
                <option value="ASSIGNED">назначена</option>
                <option selected="selected" value="IN_QUEUE">не назначена</option>
                <option value="IN_TRANSIT">в пути</option>
                <option value="DONE">выполнена</option>
            </select>
            </c:when>
            <c:when test="${requestScope.updatedOrd.ordStatus == 'IN_TRANSIT'}">
            <select name="ordStatus">
                <option value="ASSIGNED">назначена</option>
                <option value="IN_QUEUE">не назначена</option>
                <option selected="selected" value="IN_TRANSIT">в пути</option>
                <option value="DONE">выполнена</option>
            </select>
            </c:when>

            <c:when test="${requestScope.updatedOrd.ordStatus == 'DONE'}">
            <select name="ordStatus">
                <option value="ASSIGNED">назначена</option>
                <option value="IN_QUEUE">не назначена</option>
                <option value="IN_TRANSIT">в пути</option>
                <option selected="selected" value="DONE">выполнена</option>
            </select>
            </c:when>

            </c:choose>
    </div>
    <p>&nbsp;</p>
    <input value="${requestScope.updatedOrd.id}" type="hidden" name="id">
    <input name="Save" type="submit" value="Сохранить"/>

    <div>

    </div>
</div>
</div>


</form>
</body>
</html>


