<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>СarsBase</title>
    <jsp:include page="header.jsp"/>
</head>
<body>
<form action="/login" method="post">
    <div class="navbar navbar-default navbar-static-top">
        <div class="container">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="container">
                <div class="text-center">
                    <div style="margin-bottom: 50px; margin-top: 50px;">
                        <h1>СarsBase</h1>
                    </div>
                    <c:choose>
                    <c:when test="${requestScope.error != null}">
                    <div class="alert alert-danger" role="alert">
                        <c:out value="${requestScope.error}"/>
                    </div>
                    </c:when>
                    </c:choose>
                    <div>
                        <label style="line-height:1.6">Login</label>
                        <input name="login" size="15" type="text"/>
                    </div>
                    <div>
                        <label style="line-height:1.6">Password</label>
                        <input name="password" size="15" type="password"/>
                    </div>
                    <div style="margin-bottom: 50px; margin-top: 50px;">
                        <input name="LogIn" type="submit" value="Enter"/>
                        <a href="/registration">Registration</a>
                    </div>
</form>
</body>
</html>