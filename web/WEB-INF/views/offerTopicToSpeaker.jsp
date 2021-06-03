<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 30.05.2021
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Offer Topic to Speaker</title>
</head>
<body>
<h2>Запропонувати доповідь іншому спікеру</h2>
<table>
    <tr>
        <th>ID доповіді</th>
        <th>ID заходу</th>
        <th>ID спікера</th>
        <th>Назва доповіді</th>
        <th>Опис доповіді</th>
        <th>Обрати спікера</th>
    </tr>
    <c:forEach var="i" items="${availableTopics}">
        <tr>
            <td>${i.id}</td>
            <td>${i.eventId}</td>
            <td>free</td>
            <td>${i.name}</td>
            <td>${i.description}</td>
            <td>
                <form name="offerFreeTopicToSpeaker" action="conferences">
                    <select name="speakerID" required>
                        <option value=" " selected disabled></option>
                        <c:set var="previousSpeaker" value="${i.speakerId}"/>
                        <c:forEach var="a" items="${accounts}">
                            <c:set var="potentialSpeaker" value="${a.id}"/>
                            <c:if test="${previousSpeaker ne potentialSpeaker}">
                                <option value="${a.id}">${a.firstName} ${a.lastName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="command" value="offerTopicToSpeaker">
                    <input type="hidden" name="topicID" value="${i.id}">
                    <input type="submit" value="Обрати">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<p>_____________________________________________________________________</p>
<h2>Доповіді запропоновані спікером</h2>
<table>
    <tr>
        <th>ID доповіді</th>
        <th>ID заходу</th>
        <th>ID спікера</th>
        <th>Назва доповіді</th>
        <th>Опис доповіді</th>
        <th>Одобрити / Відмовити</th>
    </tr>
    <c:forEach var="i" items="${proposedTopics}">
        <tr>
            <td>${i.id}</td>
            <td>${i.eventId}</td>
            <td>${i.speakerId}</td>
            <td>${i.name}</td>
            <td>${i.description}</td>
            <td>
                <form name="ApproveOrDenyTopic" action="conferences">
                    <input type="hidden" name="command" value="approveOrDenyTopic">
                    <input type="hidden" name="topicID" value="${i.id}">
                    <input type="hidden" name="decision" value="yes">
                    <input type="submit" value="Одобрити">
                </form>
                <form name="ApproveOrDenyTopic" action="conferences">
                    <input type="hidden" name="command" value="approveOrDenyTopic">
                    <input type="hidden" name="topicID" value="${i.id}">
                    <input type="hidden" name="decision" value="no">
                    <input type="submit" value="Відмовити">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="conferences?command=returnToAcc">На головну</a>
</body>
</html>
