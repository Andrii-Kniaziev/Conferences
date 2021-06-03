<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 03.06.2021
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Topics In Process</title>
</head>
<body>
<h2>Topics that you offered in process</h2>
<table class="table-registr">
    <tr>
        <th>Topic`s ID</th>
        <th>Event`s ID</th>
        <th>Your ID</th>
        <th>Topic`s доповіді</th>
        <th>Description</th>
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
