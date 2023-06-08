<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Countries</title>
    <style>
        <%@include file="../styles/user.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h1>All Countries</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="country" items="${countries}">
        <tr>
            <td>${country.countryId}</td>
            <td>${country.name}</td>
            <td>
                <a href="<c:url value='/country/${country.countryId}'/>" class="more">View</a> |
                <a href="<c:url value='/country/update/${country.countryId}'/>" class="update">Update</a> |
                <a href="<c:url value='/country/delete/${country.countryId}'/>" class="delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
