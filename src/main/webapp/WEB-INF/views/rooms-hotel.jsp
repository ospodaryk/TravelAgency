<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Rooms</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
<h1>Rooms</h1>
<button onclick="window.location.href='/room/create';">Create Room</button>
<h1>Hotel</h1>
<h2>${hotel}</h2>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Room Number</th>
        <th>Capacity</th>
        <th>Price</th>
        <th>Availability</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="room" items="${rooms}">
        <tr>
            <td>${room.roomId}</td>
            <td>${room.number}</td>
            <td>${room.capacity}</td>
            <td>${room.price}</td>
            <td>${room.available ? "Yes" : "No"}</td>
            <td>
                <a href="<c:url value='/room/${room.roomId}'/>" class="more">View</a> |
                <a href="<c:url value='/room/update/${room.roomId}'/>" class="update">Update</a> |
                <a href="<c:url value='/room/delete/${room.roomId}'/>" class="delete">Delete</a> |
                <c:if test="${room.available}">
                    <a href="<c:url value='/room/book/${room.roomId}'/>" class="book">Book</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
