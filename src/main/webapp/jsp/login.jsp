<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Страница авторизации</title>
    <jsp:include page="header.jsp"/>

</head>
<body>
<form action="/login" method="post">
    <h1>Страница авторизации</h1>

    <c:choose>
        <c:when test="${requestScope.error != null}">
            <div class="alert alert-danger" role="alert">
                <c:out value="${requestScope.error}"/>
            </div>
        </c:when>
    </c:choose>

    <div>
        <label style="line-height:1.6">Логин</label>
        <input name="login" size="15" type="text"/>
    </div>
    <div>
        <label style="line-height:1.6">Пароль</label>
        <input name="password" size="15" type="password"/>
    </div>
    <p>&nbsp;</p>

    <input name="LogIn" type="submit" value="Войти"/>
    <a href="/registration">Регистрация</a>

    <p>&nbsp;</p>

    <p>&nbsp;</p>

    <!-- TODO remove links -->

    <a href="/list-ord">Журнал заявок</a>
    <a href="/list-trip">Журнал рейсов</a>
    <a href="/list-car">Журнал машин</a>


</form>
</body>
</html>