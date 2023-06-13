<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UPDATE COUNTRY</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h2>UPDATE COUNTRY</h2>
<form action="<c:url value='/country/update/'/>${country.countryId}" method="post">
    <h3>ID: <span>${country.countryId}</span></h3>
    <h3>Country Name: <input type="text" name="name" value="${country.name}"></h3>
    <button type="submit" class="update_but" style="margin-left: 30px">Update</button>
    <button type="reset" class="clear_but">Clear</button>
</form>
</body>
</html>
