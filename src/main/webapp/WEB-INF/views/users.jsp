<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
</head> <!-- Here, I added the closing head tag -->
<body>
<h1>Users</h1>


<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Login</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Email</th>
        <th>Role</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.userId}</td>
            <td>${user.login}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.email}</td>
            <td>${user.role.roleName}</td>
            <td>
                <a href="<c:url value='/user/${user.userId}'/>"class="more">View</a> |
                <a href="<c:url value='/user/update/${user.userId}'/>" class="update">Update</a> |
                <a href="<c:url value='/user/delete/${user.userId}'/>" class="delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
