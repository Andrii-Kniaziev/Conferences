<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 31.05.2021
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SortedEvents</title>
</head>
<body>
<h2>Events</h2>
<h2>__________________________________________________________________</h2>
<table>
    <tr><th>ID</th><th>Event name</th><th>Description</th><th>Date</th><th>Place</th><th>Is finished</th></tr>
    <c:forEach var="i" items="${events}">
        <tr><td>${i.id}</td>
            <td>${i.name}</td>
            <td>${i.description}</td>
            <td>${i.formattedDate}</td>
            <td>${i.place}</td>
            <td>${i.finished}</td></tr>
    </c:forEach>
</table>
<c:forEach var="i" items="${pages}">
    <a href="conferences?command=getEvents&eventTime=${requestScope.eventTime}&sortBy=${requestScope.sortBy}&page=${i}">${i}</a>
</c:forEach>
<h2>__________________________________________________________________</h2>
<a href="conferences?command=returnToAcc">To the main page</a>
</body>
</html>
