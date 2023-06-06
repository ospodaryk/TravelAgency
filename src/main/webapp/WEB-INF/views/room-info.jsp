<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Room Info</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
<div th:insert="header.html"></div>
<h2>ROOM INFO</h2>
<h3>Room ID: ${room.roomId}</h3>
<h3>Room Number: ${room.number}</h3>
<h3>Capacity: ${room.capacity}</h3>
<h3>Price: ${room.price}</h3>
<h3>Availability: ${room.available}</h3>
<hr>
<button onclick="window.location.href='/room';">Return Back</button>

</body>
</html>
