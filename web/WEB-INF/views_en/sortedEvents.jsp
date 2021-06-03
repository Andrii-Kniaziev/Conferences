<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 31.05.2021
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SortedEvents</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
<div class="personal-listener d-flex container-fluid">
    <h2>Events</h2>

</div>
<table class="table-registr table-custom">
    <tr>
        <th>ID</th>
        <th>Event name</th>
        <th>Description</th>
        <th>Date</th>
        <th>Place</th>
        <th>Is finished</th>
    </tr>
    <c:forEach var="i" items="${events}">
        <tr>
            <td>${i.id}</td>
            <td>${i.name}</td>
            <td>${i.description}</td>
            <td>${i.formattedDate}</td>
            <td>${i.place}</td>
            <td>${i.finished}</td>
        </tr>
    </c:forEach>
</table>
<div class="page-number">
    <c:forEach var="i" items="${pages}">
        <a href="conferences?command=getEvents&eventTime=${requestScope.eventTime}&sortBy=${requestScope.sortBy}&page=${i}">${i}</a>
    </c:forEach>
</div>
<p class="to-main">
    <a href="conferences?command=returnToAcc">To the main page</a>
</p>
</body>
</html>
