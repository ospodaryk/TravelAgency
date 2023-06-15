<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Hotels</title>
    <style>
        <%@include file="../styles/user.css"%>

        .container {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
        }

        .hotel-card {
            display: flex;
            flex-direction: column;
            border: 1px solid #ccc; /* This creates a frame around your div */
            padding: 10px;
            margin: 10px;
            box-sizing: border-box;
            flex: 1 0 22%; /* This will make 4 divs fit per row */
            max-width: 22%; /* This will make 4 divs fit per row */
        }

        @media (max-width: 800px) {
            .hotel-card {
                flex: 1 0 46%;
                max-width: 46%;
            }
        }

        @media (max-width: 500px) {
            .hotel-card {
                flex: 1 0 100%;
                max-width: 100%;
            }
        }

        .hotel-name {
            text-align: center;
        }

        .hotel-location {
            color: #5c0841; /* Change to the color you prefer */
        }

        .hotel-description,
        .more {
            text-align: center;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">

        <div class="container-fluid">
            <div class="header">
                <br>
                <button class="headbut" onclick="window.location.href='/hotel';">All Hotels</button>
                <button class="headbut" onclick="window.location.href='/hotel/book/${userId}';">Book by Date</button>
                <br>
                <button className="headbut"
                        onclick="window.location.href='/user/${userId}';">My profile
                </button>
                <br>
            </div>
        </div>

    </div>
</nav>
<h1>Hotels</h1>
<div class="container"> <!-- added the class here -->
    <c:forEach var="hotel" items="${hotels}">
        <div class="hotel-card"> <!-- added the class here -->
            <h2 class="hotel-name">${hotel.name}</h2> <!-- added the class here -->
            <h4 class="hotel-description">${hotel.description}</h4>
            <h3 class="hotel-location">${hotel.location}</h3> <!-- added the class here -->
            <button onclick="window.location.href='/room/${user_id}/hotel/${hotel.hotelId}';">Rooms</button>

        </div>
    </c:forEach>
</div>

</body>
</html>
