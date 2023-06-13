<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Booking</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script>
        $(document).ready(function() {
            $("#start_date").datepicker();
            $("#end_date").datepicker();

            $("#end_date").change(function() {
                var startDate = $("#start_date").datepicker("getDate");
                var endDate = $("#end_date").datepicker("getDate");

                if (startDate && endDate && startDate >= endDate) {
                    alert("End date cannot be earlier than or equal to the start date.");
                    $("#end_date").val("");
                }
            });
        });

    </script>
</head>
<body>
<%@include file="header.html" %>
<h2>CREATE BOOKING</h2>
<form:form method="POST" action="/booking/create/${room.roomId}" modelAttribute="booking">
    <h3>Start Date: <input type="text" id="start_date" name="start_date"/></h3>
    <ul>
        <form:errors path="start_date" cssClass="error"/>
    </ul>
    <h3>End Date: <input type="text" id="end_date" name="end_date"/></h3>
    <ul>
        <form:errors path="end_date" cssClass="error"/>
    </ul>
    <h3>Number of People: <input type="number" id="numOfPeople" name="numOfPeople"/></h3>
    <ul>
        <form:errors path="numOfPeople" cssClass="error"/>
    </ul>
    <h3>Room Capacity: <span id="roomCapacity">${room.capacity}</span></h3>

    <button type="submit" class="update_but" style="margin-left: 30px">Register</button>
    <button type="reset" class="clear_but">Clear</button>
</form:form>
</body>
</html>
