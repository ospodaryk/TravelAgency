<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Available Hotels</title>
    <style>
        <%@include file="../styles/user.css"%>
    </style>
</head>
<body>
<h1>Available Hotels</h1>
<form method="POST" action="/hotel/book">
    <label for="startDate">Start Date:</label>
    <input type="date" id="startDate" name="startDate" required>

    <label for="endDate">End Date:</label>
    <input type="date" id="endDate" name="endDate" required>

    <button type="submit">Search</button>
</form>

<h2>Results:</h2>
<c:forEach items="${hotels}" var="entry">
    <div>
        <h3>${entry.key.name}</h3>
        <p>Location: ${entry.key.location}</p>
        <p>Description: ${entry.key.description}</p>
        <h4>Rooms:</h4>
        <div class="container">
            <c:forEach var="room" items="${entry.value}">
                <div class="room-card">
                    <h2 class="room-name">${room.roomClassification.name}</h2>
                    <h3 class="room-capacity">Capacity: ${room.capacity}</h3>
                    <h4 class="room-price">Price: ${room.price}</h4>
                    <button onclick="window.location.href='/booking/create/${room.roomId}';">Book</button>
                </div>

            </c:forEach>
        </div>
    </div>
    <br>
    <hr>
</c:forEach>
</body>
</html>
