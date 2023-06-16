<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UPDATE ROOM</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h2>UPDATE ROOM</h2>
<form action="<c:url value='/room/update/'/>${room.roomId}" method="post">
    <h3>ID: <span>${room.roomId}</span></h3>
    <h3>Room Number: <input type="text" name="number" value="${room.number}"></h3>
    <h3>Capacity: <input type="text" name="capacity" value="${room.capacity}"></h3>
    <h3>Price: <input type="text" name="price" value="${room.price}"></h3>
    <button type="submit" class="update_but" style="margin-left: 30px">Update</button>
    <button type="reset" class="clear_but">Clear</button>
</form>
</body>
</html>
