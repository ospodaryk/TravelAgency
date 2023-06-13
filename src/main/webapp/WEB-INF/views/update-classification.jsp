<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UPDATE ROOM CLASSIFICATION</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h2>UPDATE ROOM CLASSIFICATION</h2>
<form action="<c:url value='/roomClassification/update/'/>${roomClassification.id}" method="post">
    <h3>ID: <span>${roomClassification.id}</span></h3>
    <h3>Name: <input type="text" name="name" value="${roomClassification.name}"></h3>
    <button type="submit" class="update_but" style="margin-left: 30px">Update</button>
    <button type="reset" class="clear_but">Clear</button>
</form>
</body>
</html>
