<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create New User</title>
    <style>
        <%@include file="../styles/user.css"%>
    </style>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="container-fluid">
            <div class="header">
                <br>
                <button class="headbut" onclick="window.location.href='/hotel';">All Hotels</button>
                <button class="headbut" onclick="window.location.href='/hotel/book';">Book by Date</button>
                <button className="headbut" class="headbut right-button" onclick="window.location.href='/user/read';">My profile</button>
                <br>
            </div>
        </div>

    </div>
</nav>
<h3>Login: ${user.login}</h3>
<h3>Name: ${user.name}</h3>
<h3>Surname: ${user.surname}</h3>
<h3>Email: ${user.email}</h3>
<hr>
<button onclick="window.location.href='/booking/user';" class="update">My bookings</button>
<br><br>
<button onclick="window.location.href='/user/update';" class="update">Update</button>
<br><br>
<button onclick="window.location.href='/user/delete';" class="delete">Delete</button>
<br><br>
<button onclick="window.location.href='/logout';">Logout</button>
<br><br>
<button onclick="window.location.href='/hotel';">Back</button>
<br>
</body>
</html>
