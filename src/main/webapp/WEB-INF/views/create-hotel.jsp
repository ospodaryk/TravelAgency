<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create New Hotel</title>
    <style>
        <%@include file="../styles/main.css"%>
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#country').change(function() {
                var selectedCountry = $(this).val();
                $.ajax({
                    url: "/get_cities",
                    data: { 'country_id' : selectedCountry },
                    success: function(data) {
                        $('#city').empty();
                        $.each(data, function(index, city) {
                            $('#city').append('<option value="' + city.cityId + '">' + city.name + '</option>');
                        });
                    }
                });
            });
        });
    </script>
</head>
<body>
<h2>CREATE HOTEL</h2>
<form:form method="POST" action="/hotel/create" modelAttribute="hotel">
    <h3>Name: <form:input path="name" /></h3>
    <ul>
        <form:errors path="name" cssClass="error"/>
    </ul>
    <h3>Location: <form:input path="location" /></h3>
    <ul>
        <form:errors path="location" cssClass="error"/>
    </ul>
    <h3>Description: <form:input path="description" /></h3>
    <ul>
        <form:errors path="description" cssClass="error"/>
    </ul>
    <h3>Country:
        <form:select path="city.country.countryId" id="country">
            <form:options items="${countries}" itemValue="countryId" itemLabel="name" />
        </form:select>
    </h3>

    <h3>City:
        <form:select path="city.cityId" id="city">
            <form:options items="${cities}" itemValue="cityId" itemLabel="name" />
        </form:select>
    </h3>
    <button type="submit" class="update_but" style="margin-left: 30px">Register</button>
    <button type="reset" class="clear_but">Clear</button>
</form:form>
</body>
</html>
