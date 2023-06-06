<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UPDATE HOTEL</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h2>UPDATE HOTEL</h2>
<form action="<c:url value='/hotel/update/'/>${hotel.hotelId}" method="post">
    <h3>ID: <span>${hotel.hotelId}</span></h3>
    <h3>Name: <input type="text" name="name" value="${hotel.name}"></h3>
    <h3>Location: <input type="text" name="location" value="${hotel.location}"></h3>
    <h3>Description: <input type="text" name="description" value="${hotel.description}"></h3>
    <h3>City:
        <select name="city">
            <c:forEach var="city" items="${cities}">
                <option value="${city.cityId}">${city.name}</option>
            </c:forEach>
        </select>
    </h3>
    <button type="submit" class="update_but" style="margin-left: 30px">Update</button>
    <button type="reset" class="clear_but">Clear</button>
</form>
</body>
</html>
