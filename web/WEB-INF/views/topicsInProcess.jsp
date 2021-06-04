<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Topics In Process</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="personal-listener d-flex container-fluid">
    <h2>Запропоновані вами доповіді на розгляді у адміністратора</h2>
</div>
<div class="container-fluid d-flex table-wrapper">
    <table class="table-registr">
        <tr>
            <th>ID доповіді</th>
            <th>ID івента</th>
            <th>Ваш ID</th>
            <th>Назва доповіді</th>
            <th>Опис доповіді</th>
        </tr>
        <c:forEach var="i" items="${topicsInProcess}">
            <tr>
                <td>${i.id}</td>
                <td>${i.eventId}</td>
                <td>${i.speakerId}</td>
                <td>${i.name}</td>
                <td>${i.description}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<p class="to-main">
    <a href="conferences?command=returnToAcc">На головну</a>
</p>
</body>
</html>
