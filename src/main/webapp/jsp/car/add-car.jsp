<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add car</title>
    <jsp:include page="../header.jsp"/>
</head>
<body>

<form action="/add-car" method="post">

    <div class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="/list-ord-driver">Order list</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li><a href="/list-car">Car list</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Add car</a></li>
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
            <h1>Add car</h1>

            <div style="margin-bottom: 20px; margin-top: 50px;">
                <div>
                    <label style="line-height:1.6">Car type</label>
                <select name="carTypeLorry">
                    <option value="false">passenger car</option>
                    <option value="true">lorry</option>
                </select>
            </div>
            <div>
                <label style="line-height:1.6">Car model</label>
                <input name="model" size="15" type="text"/>
            </div>
            <div>
                <label style="line-height:1.6">Licence plate</label>
                <input name="licencePlate" size="15" type="text"/>
            </div>
                <label style="line-height:1.6">Serviceability status</label>
            <select name="carStatus">
                <option value="true">OK</option>
                <option value="false">defect</option>
            </select>
        </div>
            <input name="Save" type="submit" value="Save"/>
        </div>
    </div>
</form>
</body>
</html>