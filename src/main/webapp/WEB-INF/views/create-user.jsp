
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New User</title>
    <link href="../static/styles/main.css" th:href="@{/styles/main.css}" rel="stylesheet"/>

</head>
<body>
<div th:insert="header.html"></div>

<h2>CREATE USER</h2>
<form th:action="@{'/users/create'}" th:object="${user}" th:method="post">
    <h3>First Name: <input type="text" th:field="*{name}"></h3>
    <ul>
        <li th:each="err : ${#fields.errors('firstName')}" th:text="${err}" class="error"/>
    </ul>
    <h3>Last Name: <input type="text" th:field="*{surname}"></h3>
    <ul>
        <li th:each="err : ${#fields.errors('lastName')}" th:text="${err}" class="error"/>
    </ul>
    <h3>Email: <input type="email" th:field="*{email}"></h3>
    <h3>Password: <input type="password" th:field="*{password}"></h3>
    <ul>
        <li th:each="err : ${#fields.errors('password')}" th:text="${err}" class="error"/>
    </ul>
    <button type="submit" class="update_but" style="margin-left: 30px">Register</button>
    <button type="reset" class="clear_but">Clear</button>
</form>
</body>
</html>
