<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавление заявки</title>
</head>
<body>
<form action="/add-ord" method="post">

    <h1>Добавление заявки</h1>

    <div>
        <label style="line-height:1.6">Дата</label>
    </div>

    </div>

    <div>
        <label style="line-height:1.6">Маршрут</label>
        <input name="rout" size="15" type="text"/>
    </div>

    <div>
        <label style="line-height:1.6">Тип машины</label>
        <select name="carTypeLorry">
            <option value="">не выбран</option>
            <option value="false">легковая</option>
            <option value="true">грузовая</option>
        </select>
    </div>


    <p>Машина</p>
    <select name="carId">
        <c:forEach var="car" items="${requestScope.newListOfCars}">
            <option value="${car.id}">${car.licencePlate} - ${car.cabDriver.login}</option>
        </c:forEach>
    </select>


    </div>

    <div>
        <label style="line-height:1.6">Статус заявки</label>
        <select name="ordStatus">
            <option value="">не установлен</option>
            <option value="ASSIGNED">назначена</option>
            <option value="IN_QUEUE">не назначена</option>

        </select>
    </div>
    <p>&nbsp;</p>


    <input name="Save" type="submit" value="Сохранить"/>
    <li><a href="/logout">Выход</a></li>

</form>
</body>
</html>