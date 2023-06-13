<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Role Info</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h2>ROLE INFO</h2>
<h3>Name: ${role.roleName}</h3>
<hr>
<button onclick="window.location.href='/role';">Return Back</button>

</body>
</html>
