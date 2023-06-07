<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Cities</title>
    <style>
        <%@include file="../styles/user.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h1>All Cities</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="city" items="${cities}">
        <tr>
            <td>${city.cityId}</td>
            <td>${city.name}</td>
            <td>
                <a href="<c:url value='/city/${city.cityId}'/>" class="more">View</a> |
                <a href="<c:url value='/city/update/${city.cityId}'/>" class="update">Update</a> |
                <a href="<c:url value='/city/delete/${city.cityId}'/>" class="delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>