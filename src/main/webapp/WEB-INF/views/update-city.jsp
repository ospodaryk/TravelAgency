<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UPDATE CITY</title>
    <style>
        <%@include file="../styles/user.css"%>
    </style>
</head>
<body>
<%@include file="header.html" %>

<h2>UPDATE CITY</h2>
<form action="<c:url value='/city/update/'/>${city.cityId}" method="post">
    <h3>ID: <span>${city.cityId}</span></h3>
    <h3>City Name: <input type="text" name="name" value="${city.name}"></h3>
    <h3>Country:
        <select name="country.countryId" id="country">
            <c:forEach var="country" items="${allCountries}">
                <option value="${country.countryId}"
                        <c:if test="${city.country.countryId == country.countryId}"> selected="selected"</c:if>
                >${country.name}</option>
            </c:forEach>
        </select>
    </h3>
    <button type="submit" class="update_but" style="margin-left: 30px">Update</button>
    <button type="reset" class="clear_but">Clear</button>
</form>
</body>
</html>
