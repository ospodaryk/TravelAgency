<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>

    <style>
        <%@include file="../styles/user.css"%>
    </style>

</head>
<body>
<h1>Ooooops... Something went wrong</h1>

<h1 style="margin: 0 auto; width: max-content" th:text="${code}"></h1>
<h2 style="margin: 0 auto; width: max-content" th:text="${message}"></h2>

</body>
</html>
