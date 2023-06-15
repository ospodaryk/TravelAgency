<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UPDATE USER</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h2>UPDATE USER</h2>
<form action="<c:url value='/user/update/'/>${user.userId}" method="post">
<%--    <h3>ID: <span>${user.userId}</span></h3>--%>
    <h3>Login: <input type="text" name="login" value="${user.login}"></h3>
    <h3>Name: <input type="text" name="name" value="${user.name}"></h3>
    <h3>Surname: <input type="text" name="surname" value="${user.surname}"></h3>
    <h3>Email: <input type="email" name="email" value="${user.email}"></h3>
    <h3>New Password: <input type="password" name="password"></h3>
    <button type="submit" class="update_but" style="margin-left: 30px">Update</button>
    <button type="reset" class="clear_but">Clear</button>
    <button onclick="window.location.href='/user';">Back</button>

</form>
</body>
</html>
