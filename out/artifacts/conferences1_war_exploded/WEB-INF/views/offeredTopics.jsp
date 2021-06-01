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
</head>
<body>
<h2>Пропозиції провести доповідь</h2>
<table>
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
            <td>
                <form name="eventSubscriptionForm" action="conferences">
                    <input type="hidden" name="command" value="offeredTopicDecision">
                    <input type="hidden" name="decision" value="yes">
                    <input type="hidden" name="topicID" value="${i.id}">
                    <input type="submit" value="Так">
                </form>
                <form name="eventSubscriptionForm" action="conferences">
                    <input type="hidden" name="command" value="offeredTopicDecision">
                    <input type="hidden" name="decision" value="no">
                    <input type="hidden" name="topicID" value="${i.id}">
                    <input type="submit" value="Ні">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="conferences?command=returnToAcc">На головну</a>
</body>
</html>
