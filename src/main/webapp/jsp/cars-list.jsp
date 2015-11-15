<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Журнал водителя</title>
</head>

<body>

<a href="/jsp/add-car.jsp">Добавить машину</a>

<form>

    <p><span style="font-family:comic sans ms,cursive"><span style="font-size:20px"><strong>Журнал машин</strong></span></span>
    </p>
    <table align="left" border="1" cellpadding="1" cellspacing="1" style="width:500px">
        <thead>
        <tr>
            <th scope="col">&nbsp;</th>
            <th scope="col">Марка машины</th>
            <th scope="col">Номер машины</th>
            <th scope="col">Тип машины</th>
            <th scope="col">Состояние машины</th>

        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="radio" name="car-id"/></td>
            <td>${requestScope.newListOfCars}</td>
            <td></td>
            <td></td>
            <td></td>

        </tr>
        <tr>
            <td><input type="radio" name="car-id"/></td>
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


    <input name="Add" type="button" value="Добавить"/>&nbsp; &nbsp; &nbsp;<input name="Update" type="button"
                                                                                 value="Редактировать"/>&nbsp; &nbsp;
    &nbsp;<input name="Delete" type="button" value="Удалить"/>


</form>
<p>&nbsp;</p>

<p style="text-align: left;"><span style="font-size:14px"><a href="http://ya.ru"
                                                             style="line-height: 20.8px; text-align: right;"><span
        style="font-family:comic sans ms,cursive"><strong>Выйти</strong></span></a></span></p>
</body>
</html>