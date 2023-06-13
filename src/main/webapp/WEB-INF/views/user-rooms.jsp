<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Rooms</title>
    <style>
        <%@include file="../styles/user.css"%>

        .container {
            display: flex;
            justify-content: center;
            flex-wrap: wrap;
        }

        .room-card {
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
            .room-card {
                flex: 1 0 46%;
                max-width: 46%;
            }
        }

        @media (max-width: 500px) {
            .room-card {
                flex: 1 0 100%;
                max-width: 100%;
            }
        }

        .room-name {
            text-align: center;
        }

        .room-location {
            color: red; /* Change to the color you prefer */
        }

        .room-description,
        .more {
            text-align: center;
        }
    </style>
</head>
<body>
<%@include file="header.html" %>
<h1>${hotel}</h1>

<div class="container">
    <c:forEach var="room" items="${rooms}">
        <div class="room-card">
            <h2 class="room-name">${room.roomClassification.name}</h2>
            <h3 class="room-capacity">Capacity: ${room.capacity}</h3>
            <h4 class="room-price">Price: ${room.price}</h4>
            <button onclick="window.location.href='/booking/create/${room.roomId}';">Book</button>

        </div>
    </c:forEach>
</div>

</body>
</html>
