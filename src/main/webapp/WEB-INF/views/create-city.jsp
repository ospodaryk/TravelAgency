<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New City</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>
<h2>CREATE CITY</h2>
<form:form method="POST" action="/city/create/${country.countryId}" modelAttribute="city">
    <h3>Name: <form:input path="name"/></h3>
    <ul>
        <form:errors path="name" cssClass="error"/>
    </ul>
    <button type="submit" class="update_but" style="margin-left: 30px">Register</button>
    <button type="reset" class="clear_but">Clear</button>
</form:form>
</body>
</html>
