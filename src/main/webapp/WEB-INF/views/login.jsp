<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LOGIN</title>
    <style>
        <%@include file="../styles/login.css"%>
    </style>
</head>
<body>

<div>
    <h2>LogIn Page</h2>
</div>
<hr>
<c:if test="${param.error != null}">
    <h1>Invalid username or password</h1>
    <hr>
</c:if>

<c:if test="${param.logout != null}">
    <h1>You have been logged out</h1>
    <hr>
</c:if>
<form method="POST" id="form-login" action="${pageContext.request.contextPath}/form-login">
    <div>
        <h2>Username:
            <input type="text" name="username"/></h2>
    </div>
    <div>
        <h2>Password:
            <input type="password" name="password"/></h2>
    </div>
    <div>
        <input  type="submit" value="LogIn"/>
    </div>
</form>
<br>
<hr>


<h3>Don't have an account?
    <a href="${pageContext.request.contextPath}/users/create"> Register now!</a></h3>
</body>
</html>
