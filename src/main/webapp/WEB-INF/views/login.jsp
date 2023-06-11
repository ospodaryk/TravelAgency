<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LOGIN</title>
</head>
<body>
<div th:replace="header.html"></div>

<div>
    <h2>LogIn Page</h2>
</div>
<hr>
<div th:if="${param.error}"><h1>Invalid username or password</h1><hr></div>
<div th:if="${param.logout}"><h1>You have been logged out</h1><hr></div>
<form method="POST" id="form-login" th:action="@{/form-login}">
    <div>
        <h2>Username:</h2>
        <div>
            <input type="text" name="username"/>
        </div>
    </div>
    <div>
        <h2>Password:</h2>
        <div>
            <input type="password" name="password"/>
        </div>
    </div>
    <div>
        <input type="submit" value="LogIn"/>
    </div>
</form>
<br><br>
<h3 style="align-content: center">Don't have an account?</h3>
<button onclick="window.location.href='/user/create';">Register now!</button>

</body>
</html>

