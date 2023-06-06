<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hotel Info</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
<div th:insert="header.html"></div>
<h2>HOTEL INFO</h2>
<h3>Name: ${hotel.name}</h3>
<h3>Location: ${hotel.location}</h3>
<h3>Description: ${hotel.description}</h3>
<hr>
<button onclick="window.location.href='/hotel';">Return Back</button>

</body>
</html>
