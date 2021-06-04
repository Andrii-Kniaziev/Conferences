<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Offer Topic to Speaker</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="personal-listener d-flex container-fluid">
    <h2>Запропонувати доповідь іншому спікеру</h2>
</div>
<div class="container">
    <table class="table-registr">
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
                        <input type="submit" value="Обрати" class="btn submit-btn">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="container">
    <h3>Доповіді запропоновані спікером</h3>
    <table  class="table-registr">
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
                        <input type="submit" value="Одобрити" class="btn submit-btn">
                    </form>
                    <form name="ApproveOrDenyTopic" action="conferences">
                        <input type="hidden" name="command" value="approveOrDenyTopic">
                        <input type="hidden" name="topicID" value="${i.id}">
                        <input type="hidden" name="decision" value="no">
                        <input type="submit" value="Відмовити" class="btn submit-btn">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<p class="to-main">
    <a href="conferences?command=returnToAcc">На головну</a>
</p>
</body>
</html>
