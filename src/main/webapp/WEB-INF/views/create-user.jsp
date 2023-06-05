<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New User</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style></head>
<body>

<h2>CREATE USER</h2>
<form:form method="POST" action="/user/create" modelAttribute="user">
    <h3>First Name: <form:input path="name" /></h3>
    <ul>
        <form:errors path="name" cssClass="error"/>
    </ul>
    <h3>Last Name: <form:input path="surname" /></h3>
    <ul>
        <form:errors path="surname" cssClass="error"/>
    </ul>
    <h3>Email: <form:input path="email" /></h3>
    <h3>Password: <form:password path="password" /></h3>
    <ul>
        <form:errors path="password" cssClass="error"/>
    </ul>
    <button type="submit" class="update_but" style="margin-left: 30px">Register</button>
    <button type="reset" class="clear_but">Clear</button>
</form:form>
</body>
</html>