<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Bookings</title>
    <style>
        <%@include file="../styles/user.css"%>
    </style>
</head>
<body>
<%@include file="header-admin.html" %>
<hr>
<div class="container">
    <h1>All Bookings</h1>
    <br>    <br>
    <br>
    <br>
    <br>
    <c:forEach var="booking" items="${bookings}">
        <div class="room-card">
            <p>Hotel: ${booking.hotel.name}</p>
            <p>Start Date: ${booking.start_date}</p>
            <p>End Date: ${booking.end_date}</p>
            <p>Total Price: ${booking.totalPrice}</p>
            <p>Status:
                <c:choose>
                    <c:when test="${booking.actual}">
                        Open
                    </c:when>
                    <c:otherwise>
                        Closed
                    </c:otherwise>
                </c:choose>
            </p>
        </div>
    </c:forEach>
</div>
</body>
</html>
