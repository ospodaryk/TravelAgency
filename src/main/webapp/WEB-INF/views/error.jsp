<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <style>
        <sec:authorize access="hasAuthority('STAFF')">
        <%@include file="../styles/admin.css"%>
        </sec:authorize>

    </style>
</head>
<body>
<h1>Ooooops... Something went wrong</h1>
<h1 style="margin: 0 auto; width: max-content">${code}</h1>
<h2 style="margin: 0 auto; width: max-content">${message}</h2>
<button onclick="window.location.href='/logout';">Back</button>
</body>
</html>
