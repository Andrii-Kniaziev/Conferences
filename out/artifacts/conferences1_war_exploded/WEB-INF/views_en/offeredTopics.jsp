<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 31.05.2021
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Offered topics</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
<div class="personal-listener d-flex container-fluid">
    <h2>Offers to spend topic</h2>
    <h2>Topics which you have agreed to spend</h2>
</div>
<div class="container-fluid d-flex table-wrapper">
    <table class="table-registr">
        <tr>
            <th>Topic`s ID</th>
            <th>Event`s ID</th>
            <th>Your ID</th>
            <th>Topic`s name</th>
            <th>Description</th>
            <th>Approve / Deny</th>
        </tr>
        <c:forEach var="i" items="${offeredTopics}">
            <tr>
                <td>${i.id}</td>
                <td>${i.eventId}</td>
                <td>${i.speakerId}</td>
                <td>${i.name}</td>
                <td>${i.description}</td>
                <td class="d-flex btn-group">
                    <form name="eventSubscriptionForm" action="conferences">
                        <input type="hidden" name="command" value="offeredTopicDecision">
                        <input type="hidden" name="decision" value="yes">
                        <input type="hidden" name="topicID" value="${i.id}">
                        <input type="submit" value="Approve" class="submit-btn">
                    </form>
                    <form name="eventSubscriptionForm" action="conferences">
                        <input type="hidden" name="command" value="offeredTopicDecision">
                        <input type="hidden" name="decision" value="no">
                        <input type="hidden" name="topicID" value="${i.id}">
                        <input type="submit" value="Deny" class="submit-btn">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <table class="table-registr">
        <tr>
            <th>Topic`s ID</th>
            <th>Event`s ID</th>
            <th>Your ID</th>
            <th>Topic`s доповіді</th>
            <th>Description</th>
        </tr>
        <c:forEach var="i" items="${agreedTopics}">
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
    <a href="conferences?command=returnToAcc">To the main page</a>
</p>
</body>
</html>
