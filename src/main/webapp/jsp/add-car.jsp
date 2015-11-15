<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавление машины</title>
</head>
<body>
<form action="/add-car" method="post">
    <h1>Добавление машины</h1>

    <p>Тип машины&nbsp;</p>

    <div>
        <select name="carTypeLorry">
            <option value="">Тип машины не выбран</option>
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
    <div><p>Состояние машины&nbsp;</p>
        <select name="carStatus">
            <option value="">Состояние машины не установлено</option>
            <option value="true">кондиционная</option>
            <option value="false">не кондиционная</option>
        </select>
    </div>
    <input name="Save" type="submit" value="Сохранить"/>&nbsp;&nbsp;&nbsp;
    <input name="Cancel" type="button" value="Отменить"/>
</form>
</body>
</html>