<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Bookings</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>
<hr>
<button onclick="window.location.href='/booking/create';">Create Booking</button>

<h1>All Bookings</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Number of People</th>
        <th>Total Price</th>
        <th>Hotel</th>
        <th>Country</th>
        <th>City</th>
        <th>Actual</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="booking" items="${bookings}">
        <tr>
            <td>${booking.bookingId}</td>
            <td>${booking.start_date}</td>
            <td>${booking.end_date}</td>
            <td>${booking.numOfPeople}</td>
            <td>${booking.totalPrice}</td>
            <td>${booking.hotel.name}</td>
            <td>${booking.hotel.city.country.name}</td>
            <td>${booking.hotel.city.name}</td>

            <td>${booking.actual ? "Yes" : "No"}</td>

            <td>
                <a href="<c:url value='/booking/${booking.bookingId}'/>" class="more">View</a> |
                <c:if test="${booking.actual}">
                    <a href="<c:url value='/booking/delete/${booking.bookingId}'/>" class="delete">Delete</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
