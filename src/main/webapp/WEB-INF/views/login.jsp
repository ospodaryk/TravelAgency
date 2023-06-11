<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <title>LOGIN</title>
</head>
<body>
<div th:replace="header"></div>

<div class="col-md-offset-2">
    <h2>LogIn Page</h2>
</div>
<hr>
<h4>Default USERS</h4>
<hr>
<div th:if="${param.error}"><h1>Invalid username or password</h1><hr></div>
<div th:if="${param.logout}"><h1>You have been logged out</h1><hr></div>
<form class="form-horizontal" method="POST" id="form-login" th:action="@{/form-login}">
    <div class="form-group">
        <h2 class="col-sm-2 control-label">Username:</h2>
        <div class="col-sm-8">
            <input type="text" class="form-control" name="username"/>
        </div>
    </div>
    <div class="form-group">
        <h2 class="col-sm-2 control-label">Password:</h2>
        <div class="col-sm-8">
            <input type="password" class="form-control" name="password"/>
        </div>
    </div>
    <div class="col-sm-offset-2 col-sm-8">
        <input class="btn btn-info" type="submit" value="LogIn"/>
    </div>
</form>
<br><br>
<h3 class="form-group" style="align-content: center">Don't have an account?
    <a th:href="@{/users/create}"> Register now!</a></h3>
</body>
</html>