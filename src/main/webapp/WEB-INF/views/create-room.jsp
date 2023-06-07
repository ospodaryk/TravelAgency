<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Room</title>
    <style>
        <%@include file="../styles/user.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h2>CREATE ROOM</h2>
<form:form method="POST" action="/room/create/${hotel.hotelId}" modelAttribute="room">
    <h3>Room Number: <form:input path="number"/></h3>
    <ul>
        <form:errors path="number" cssClass="error"/>
    </ul>
    <h3>Capacity: <form:input path="capacity"/></h3>
    <ul>
        <form:errors path="capacity" cssClass="error"/>
    </ul>
    <h3>Price: <form:input path="price"/></h3>
    <ul>
        <form:errors path="price" cssClass="error"/>
    </ul>
    <h3>Is Available: <form:checkbox path="available"/></h3>
    <button type="submit" class="update_but" style="margin-left: 30px">Register</button>
    <button type="reset" class="clear_but">Clear</button>
</form:form>
</body>
</html>
