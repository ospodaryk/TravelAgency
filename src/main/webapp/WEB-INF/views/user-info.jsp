<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New User</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
<div th:insert="header.html"></div>
<h2>USER-info</h2>
<h3>Login: ${user.login}</h3>
<h3>Name: ${user.name}</h3>
<h3>Surname: ${user.surname}</h3>
<h3>Email: ${user.email}</h3>
<h3>Password: ${user.userId}</h3>
</body>
</html>
