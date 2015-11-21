<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <li>${sessionScope.authorizedUser.login}</li>
    <title>Добавление машины</title>
</head>
<body>
<form action="/add-car" method="post">
    <h1>Добавление машины</h1>


    <div>
        <label style="line-height:1.6">Тип машины</label>
        <select name="carTypeLorry">
            <option value="">не выбран</option>
            <option value="false">легковая</option>
            <option value="true">грузовая</option>
        </select>
    </div>
    <div>
        <label style="line-height:1.6">Марка машины</label>
        <input name="model" size="15" type="text"/>
    </div>
    <div>
        <label style="line-height:1.6">Номер машины</label>
        <input name="licencePlate" size="15" type="text"/>
    </div>
    <label style="line-height:1.6">Состояние машины</label>
        <select name="carStatus">
            <option value="">не установлено</option>
            <option value="true">кондиционная</option>
            <option value="false">не кондиционная</option>
        </select>
    </div>
    <p>&nbsp;</p>
    <input name="Save" type="submit" value="Сохранить"/>
    <li><a href="/logout">Выход</a></li>

</form>
</body>
</html>