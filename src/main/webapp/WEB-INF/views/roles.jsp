<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Roles</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h1>All Roles</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Role Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="role" items="${roles}">
        <tr>
            <td>${role.id}</td>
            <td>${role.roleName}</td>
            <td>
                <a href="<c:url value='/role/${role.id}'/>" class="more">View</a> |
                <a href="<c:url value='/role/update/${role.id}'/>" class="update">Update</a> |
                <a href="<c:url value='/role/delete/${role.id}'/>" class="delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
