<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UPDATE BOOKING</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h2>UPDATE BOOKING</h2>
<form action="<c:url value='/booking/update/'/>${booking.bookingId}" method="post">
    <h3>ID: <span>${booking.bookingId}</span></h3>
    <h3>Start Date: <input type="text" name="start_date" id="datepicker_begin" value="${booking.start_date}"></h3>
    <h3>End Date: <input type="text" name="end_date" id="datepicker_end" value="${booking.end_date}"></h3>
    <h3>Total Price: <input type="number" name="totalPrice" value="${booking.totalPrice}"></h3>
    <button type="submit" class="update_but" style="margin-left: 30px">Update</button>
    <button type="reset" class="clear_but">Clear</button>
</form>
</body>
</html>
