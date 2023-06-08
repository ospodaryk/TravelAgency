<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Country Info</title>
    <style>
        <%@include file="../styles/user.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<div th:insert="header.html"></div>
<h2>COUNTRY INFO</h2>
<h3>Name: ${country.name}</h3>
<hr>
<button onclick="window.location.href='/country';">Return Back</button>

</body>
</html>
