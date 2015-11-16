<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Страница регистрации</title>
</head>
<body>
<form action="/registration" method="post">
    <h1>Страница регистрации</h1>

    <div>
        <label style="line-height:1.6">Логин</label>
        <input name="login" size="15" type="text"/>
    </div>
    <div>
        <label style="line-height:1.6">Пароль</label>
        <input name="password" size="15" type="text"/>
    </div>

    <p>&nbsp;</p>
    <input name="Save" type="submit" value="Сохранить"/>

    <a href="/jsp/login.jsp">Уже есть учетная запись</a>

</form>
</body>
</html>