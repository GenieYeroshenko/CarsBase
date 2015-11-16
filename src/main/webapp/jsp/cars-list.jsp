<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Журнал машин</title>

</head>
<body>

<a href="/jsp/trips-list.jsp">Журнал рейсов</a>

<p>&nbsp;</p>


<form>

    <p><span style="font-family:comic sans ms,cursive"><span style="font-size:20px">
        <strong>Журнал машин</strong></span></span></p>

    <table align="left" border="1" cellpadding="1" cellspacing="1" style="width:500px">
        <thead>
        <tr>
            <th scope="col">&nbsp;</th>
            <th scope="col">Тип машины</th>
            <th scope="col">Марка машины</th>
            <th scope="col">Номер машины</th>
            <th scope="col">Состояние машины</th>
            <th scope="col">&nbsp;</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="car" items="${requestScope.newListOfCars}">
            <tr>
                <td><input type="radio" name="car-id"/></td>
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
                            <a href="/change-car-status?id=${car.id}">сломалась</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/change-car-status?id=${car.id}">починилась</a>
                        </c:otherwise>
                    </c:choose>
                    <a></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="/jsp/add-car.jsp">Добавить машину</a>&nbsp; &nbsp; &nbsp;

    <input name="Update" type="button" value="Редактировать"/>&nbsp; &nbsp; &nbsp;
    <input name="Delete" type="button" value="Удалить"/>


</form>
<p>&nbsp;</p>

<p style="text-align: left;"><span style="font-size:14px">
    <a href="http://ya.ru" style="line-height: 20.8px; text-align: right;"><span
            style="font-family:comic sans ms,cursive">
        <strong>Выйти</strong></span></a></span></p>
</body>
</html>
