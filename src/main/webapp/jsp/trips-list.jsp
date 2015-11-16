<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Журнал рейсов</title>
</head>

<body>

<a href="/jsp/cars-list.jsp">Журнал машин</a>
<td>&nbsp;</td>


<form>
    <td>&nbsp;</td>
    <p><span style="font-family:comic sans ms,cursive"><span style="font-size:20px"><strong>Журнал рейсов</strong></span></span>
    </p>

    <table align="left" border="1" cellpadding="1" cellspacing="1" style="width:500px">
        <thead>
                <th scope="col">&nbsp;</th>
                <th scope="col">Номер заявки</th>
                <th scope="col">Дата</th>
                <th scope="col">Маршрут</th>
                <th scope="col">Тип машины</th>
                <th scope="col">Марка машины</th>
                <th scope="col">Номер машины</th>
                <th scope="col">Статус</th>
        </thead>
        <tbody>
            <tr>
                <td><input type="radio" name="trip-id"/></td>
                <td>${requestScope.newListOfTrips}</td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td><input type="radio" name="trip-id"/></td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
        </tbody>
    </table>

    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>

    <input name="IN_TRANSIT" type="button" value="В пути"/>&nbsp; &nbsp; &nbsp;
    <input name="DONE" type="button" value="Выполнен"/>

</form>
<p>&nbsp;</p>

<p style="text-align: left;"><span style="font-size:14px">
    <a href="http://ya.ru" style="line-height: 20.8px; text-align: right;"><span style="font-family:comic sans ms,cursive">
        <strong>Выйти</strong></span></a></span></p>
</body>
</html>