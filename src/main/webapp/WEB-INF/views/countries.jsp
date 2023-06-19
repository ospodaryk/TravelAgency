<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>All Countries</title>
    <style>
        <%@include file="../styles/admin.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>
<br><br>
<button onclick="window.location.href='/country/create';">Create country</button>

<h1>All Countries</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Actual</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="country" items="${countries}">
        <tr>
            <td>${country.countryId}</td>
            <td>${country.name}</td>
            <td>${country.actual ? "Yes" : "No"}</td>

            <td>
                <a href="<c:url value='/country/${country.countryId}'/>" class="more">View</a> |
                <a href="<c:url value='/country/update/${country.countryId}'/>" class="update">Update</a> |
                <a href="<c:url value='/city/create/${country.countryId}'/>">Add city</a> |

                <c:if test="${country.actual}">
                    <a href="<c:url value='/country/delete/${country.countryId}'/>" class="delete">Delete</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
