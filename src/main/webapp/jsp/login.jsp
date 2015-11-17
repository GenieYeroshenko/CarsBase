<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Страница авторизации</title>
</head>
<body>
<form action="/login" method="post">
    <h1>Страница авторизации</h1>



    <div>
        <label style="line-height:1.6">Логин</label>
        <input name="login" size="15" type="text"/>
    </div>
    <div>
        <label style="line-height:1.6">Пароль</label>
        <input name="password" size="15" type="text"/>
    </div>
    <p>&nbsp;</p>

    <input name="LogIn" type="submit" value="Войти"/>
    <a href="/jsp/registration.jsp">Регистрация</a>

    <p>&nbsp;</p>
    <p>&nbsp;</p>

    <a href="/list-ord">Журнал заявок</a> &nbsp;
    <a href="/list-trip">Журнал рейсов</a>&nbsp;
    <a href="/list-car">Журнал машин</a>


</form>
</body>
</html>