<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Добавление машины</title>
    <jsp:include page="../header.jsp"/>
</head>
<body>

<form action="/add-car" method="post">

    <div class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/list-ord-driver">Журнал заявок</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li><a href="/list-car">Журнал машин</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Добавить машину</a></li>
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
            <h1>Добавление машины</h1>

            <h1></h1>

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

            <p>&nbsp;</p>
            <input name="Save" type="submit" value="Сохранить"/>
        </div>
        </table>

    </div>
    </div>
</form>
</body>
</html>