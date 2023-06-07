<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New User</title>
    <style>
        <%@include file="../styles/user.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>
<div th:insert="header.html"></div>
<h2>USER-info</h2>
<h3>Login: ${user.login}</h3>
<h3>Name: ${user.name}</h3>
<h3>Surname: ${user.surname}</h3>
<h3>Email: ${user.email}</h3>
<hr>
<button onclick="window.location.href='/user';">Повернутися назад</button>

</body>
</html>
