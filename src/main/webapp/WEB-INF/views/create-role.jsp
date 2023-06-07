<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Role</title>
    <style>
        <%@include file="../styles/user.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h2>CREATE ROLE</h2>
<form:form method="POST" action="/role/create" modelAttribute="role">
    <h3>Name: <form:input path="roleName"/></h3>
    <ul>
        <form:errors path="roleName" cssClass="error"/>
    </ul>
    <button type="submit" class="update_but" style="margin-left: 30px">Create</button>
    <button type="reset" class="clear_but">Clear</button>
</form:form>
</body>
</html>
