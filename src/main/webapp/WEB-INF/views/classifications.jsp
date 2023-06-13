<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Room Classifications</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h1>All Room Classifications</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="roomClassification" items="${roomClassifications}">
        <tr>
            <td>${roomClassification.id}</td>
            <td>${roomClassification.name}</td>
            <td>
                <a href="<c:url value='/roomClassification/${roomClassification.id}'/>" class="more">View</a> |
                <a href="<c:url value='/roomClassification/update/${roomClassification.id}'/>" class="update">Update</a> |
                <a href="<c:url value='/roomClassification/delete/${roomClassification.id}'/>" class="delete">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
