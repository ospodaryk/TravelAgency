<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booking Info</title>
    <style>
        <%@include file="../styles/user.css"%>
    </style>
</head>
<body>
<%@include file="header-admin.html" %>

<div th:insert="header.html"></div>
<h2>BOOKING INFO</h2>
<h3>Hotel: ${booking.hotel.name}</h3>
<h3>Start Date: ${booking.start_date}</h3>
<h3>End Date: ${booking.end_date}</h3>
<h3>Total Price: ${booking.totalPrice}</h3>
<hr>
<button onclick="window.location.href='/hotel';">Return Back</button>

</body>
</html>
