<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Car list</title>

    <jsp:include page="../header.jsp"/>
</head>
<body>


<div class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/list-ord-driver">Order list</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Car list</a></li>
            </ul>
            <ul class="nav navbar-nav">
                <li><a href="/add-car">Add car</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout">Exit</a></li>
            </ul>
            <p class="navbar-text navbar-right">${sessionScope.authorizedUser.login}</p>
        </div>
    </div>
</div>
<div class="container">
    <div class="text-center">


        <h1>Car list</h1>

        <c:choose>
            <c:when test="${requestScope.error != null}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${requestScope.error}"/>
                </div>
            </c:when>
        </c:choose>
        <a class="btn btn-info" href="/list-car">Back</a>

    </div>
</div>
</body>
</html>