<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 30.05.2021
  Time: 15:46
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
    <h2>Пропозиції провести доповідь</h2>
    <h2>Доповіді які ви погодилися провести</h2>
</div>
<div class="container-fluid d-flex table-wrapper">
    <table class="table-registr">
        <tr>
            <th>ID доповіді</th>
            <th>ID івента</th>
            <th>Ваш ID</th>
            <th>Назва доповіді</th>
            <th>Опис доповіді</th>
            <th>Погодитися / Відмовитися</th>
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
                        <input type="submit" value="Так" class="submit-btn">
                    </form>
                    <form name="eventSubscriptionForm" action="conferences">
                        <input type="hidden" name="command" value="offeredTopicDecision">
                        <input type="hidden" name="decision" value="no">
                        <input type="hidden" name="topicID" value="${i.id}">
                        <input type="submit" value="Ні" class="submit-btn">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <table class="table-registr">
        <tr>
            <th>ID доповіді</th>
            <th>ID івента</th>
            <th>Ваш ID</th>
            <th>Назва доповіді</th>
            <th>Опис доповіді</th>
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
    <a href="conferences?command=returnToAcc">На головну</a>
</p>
</body>
</html>
