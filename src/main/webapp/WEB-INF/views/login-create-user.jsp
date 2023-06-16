<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
\<html>
<head>
    <meta charset="UTF-8">
    <title>Create New User</title>
    <style>
        <%@include file="../styles/login.css"%>
    </style>
</head>
<body>

<form:form method="POST" action="/create" modelAttribute="user">
    <h3>Login: <form:input path="login"/></h3>
    <ul>
        <form:errors path="login" cssClass="error"/>
    </ul>
    <h3>First Name: <form:input path="name"/></h3>
    <ul>
        <form:errors path="name" cssClass="error"/>
    </ul>
    <h3>Last Name: <form:input path="surname"/></h3>
    <ul>
        <form:errors path="surname" cssClass="error"/>
    </ul>
    <h3>Email: <form:input path="email"/></h3>
    <ul>
        <form:errors path="email" cssClass="error"/>
    </ul>
    <h3>Password: <form:password path="password"/></h3>
    <ul>
        <form:errors path="password" cssClass="error"/>
    </ul>
    <button type="submit" class="update_but" style="margin-left: 30px">Register</button>
    <button type="reset" class="clear_but">Clear</button>
</form:form>
</body>
</html>
