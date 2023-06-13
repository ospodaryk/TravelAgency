<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>City Info</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<div th:insert="header.html"></div>
<h2>CITY INFO</h2>
<h3>Name: ${city.name}</h3>
<h3>Country: ${city.country.name}</h3>
<hr>
<button onclick="window.location.href='/city';">Return Back</button>

</body>
</html>
