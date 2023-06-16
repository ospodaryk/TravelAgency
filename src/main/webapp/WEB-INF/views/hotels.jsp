<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hotels</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
</head> <!-- Here, I added the closing head tag -->
<body>
<%@include file="header.html" %>

<h1>Hotels</h1>
<button onclick="window.location.href='/hotel/create';">Create Hotel</button>

<table>
    <tr>
        <th>Hotel ID</th>
        <th>Name</th>
        <th>Location</th>
        <th>Description</th>
        <th>City</th>
        <th>Country</th>
        <th>Actual</th>
        <th>Action</th>
    </tr>
    <c:forEach var="hotel" items="${hotels}">
        <tr>
            <td>${hotel.hotelId}</td>
            <td>${hotel.name}</td>
            <td>${hotel.location}</td>
            <td>${hotel.description}</td>
            <td>${hotel.city.name}</td>
            <td>${hotel.city.country.name}</td>
            <td>${hotel.actual ? "Yes" : "No"}</td>

            <td>
                <a href="<c:url value='/hotel/read/${hotel.hotelId}'/>" class="more">View</a> |
                <a href="<c:url value='/hotel/update/${hotel.hotelId}'/>" class="update">Update</a> |
                <a href="<c:url value='/hotel/delete/${hotel.hotelId}'/>" class="delete">Delete</a> |
                <a href="<c:url value='/room/adm/hotel/${hotel.hotelId}'/>">Rooms</a> |
                <a href="<c:url value='/room/create/${hotel.hotelId}'/>">Add room</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
