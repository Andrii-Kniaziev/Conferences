<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 31.05.2021
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Subscribe for event</title>
</head>
<body>

<table style="width: 80%">
    <tr>
        <th>Event ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Date</th>
        <th>Place</th>
        <th>Register</th>
    </tr>
    <c:forEach var="i" items="${events}">
        <tr>
            <td>${i.id}</td>
            <td>${i.name}</td>
            <td>${i.description}</td>
            <td>${i.formattedDate}</td>
            <td>${i.place}</td>
            <td>
                <c:set var="currentID" value="${i.id}"/>
                <c:set var="contains" value="false"/>
                <c:forEach var="eID" items="${eventIDs}">
                    <c:if test="${eID eq currentID}">
                        <c:set var="contains" value="true" />
                    </c:if>
                </c:forEach>
                <c:if test="${contains eq 'true'}">
                    <form name="eventUnsubscriptionForm" action="conferences">
                        <input type="hidden" name="command" value="unsubscribeFromEvent">
                        <input type="hidden" name="eventID" value="${i.id}">
                        <input type="submit" value="Unsubscribe">
                    </form>
                </c:if>
                <c:if test="${contains eq 'false'}">
                    <form name="eventSubscriptionForm" action="conferences">
                        <input type="hidden" name="command" value="subscribeForEvent">
                        <input type="hidden" name="eventID" value="${i.id}">
                        <input type="submit" value="Subscribe">
                    </form>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<c:forEach var="i" items="${pages}">
    <a href="conferences?command=eventSubscriptionInfo&page=${i}">${i}</a>
</c:forEach>
<br/>
<a href="conferences?command=returnToAcc">To the main page</a>
</body>
</html>
