<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Редактирование машины</title>
    <jsp:include page="../header.jsp"/>
</head>
<body>

<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/list-ord-driver">Журнал рейсов</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="/list-car">Журнал машин</a></li>
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
        <h1>Редактирование машины</h1>

        <h1></h1>

        <div>
            <form action="/update-car" method="post">

                <div>
                    <label style="line-height:1.6">Тип машины</label>
                    <c:choose>
                        <c:when test="${requestScope.updatedCar.carTypeLorry}">
                            <select name="carTypeLorry">
                                <option value="false">легковая</option>
                                <option selected="selected" value="true">грузовая</option>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <select name="carTypeLorry">
                                <option selected="selected" value="false">легковая</option>
                                <option value="true">грузовая</option>
                            </select>
                        </c:otherwise>
                    </c:choose>
                </div>


                <div>
                    <label style="line-height:1.6">Марка машины</label>
                    <input name="model" size="15" type="text" value="${requestScope.updatedCar.model}"/>
                </div>

                <div>
                    <label style="line-height:1.6">Номер машины</label>
                    <input name="licencePlate" size="15" type="text" value="${requestScope.updatedCar.licencePlate}"/>
                </div>

                <label style="line-height:1.6">Состояние машины</label>
                <c:choose>
                <c:when test="${requestScope.updatedCar.carStatus}">
                <select name="carStatus">
                    <option value="false">не кондиционная</option>
                    <option selected="selected" value="true">кондиционная</option>
                </select>
                </c:when>
                <c:otherwise>
                <select name="carStatus">
                    <option selected="selected" value="false">не кондиционная</option>
                    <option value="true">кондиционная</option>
                </select>
                </c:otherwise>
                </c:choose>
        </div>
        <p>&nbsp;</p>
        <input value="${requestScope.updatedCar.id}" type="hidden" name="id">
        <input name="Save" type="submit" value="Сохранить"/>
    </div>
</div>
</div>


</form>
</body>
</html>


