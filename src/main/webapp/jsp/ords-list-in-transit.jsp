<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Журнал заявок (только в пути)</title>
</head>

<body>


<form>

    <p><span style="font-family:comic sans ms,cursive"><span style="font-size:20px"><strong>Журнал заявок (только в пути)</strong></span></span>
    </p>

    <a href="/jsp/ords-list.jsp">Все</a>&nbsp; &nbsp; &nbsp;
    <a href="/jsp/ords-list-assigned.jsp">Назначенные</a>&nbsp; &nbsp; &nbsp;
    <label>В пути</label>&nbsp; &nbsp; &nbsp;
    <a href="/jsp/ords-list-done.jsp">Выполненные</a>&nbsp; &nbsp; &nbsp;
    <a href="/jsp/ords-list-in-queue.jsp">Не назначенные</a>&nbsp; &nbsp; &nbsp;
    <p>&nbsp;</p>

    <table align="left" border="1" cellpadding="1" cellspacing="1" style="width:500px">
        <thead>
        <tr>
            <th scope="col">&nbsp;</th>
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
        <tr>
            <td><input type="radio" name="ord-id"/></td>
            <td>${requestScope.newListOfOrdsInTransit}</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>

        </tr>
        <tr>
            <td><input type="radio" name="ord-id"/></td>
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
    <p>&nbsp;</p>
    <a href="/jsp/add-ord.jsp">Добавить заявку</a>&nbsp; &nbsp; &nbsp;
    <input name="Update" type="button" value="Редактировать"/>&nbsp; &nbsp; &nbsp;
    <input name="Delete" type="button" value="Удалить"/>


</form>

<p>&nbsp;</p>

<p style="text-align: left;"><span style="font-size:14px">
    <a href="http://ya.ru" style="line-height: 20.8px; text-align: right;">
        <span style="font-family:comic sans ms,cursive"><strong>Выйти</strong></span></a></span></p>
</body>
</html>
