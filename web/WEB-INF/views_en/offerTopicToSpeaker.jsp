<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 31.05.2021
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Offer Topic to Speaker</title>
</head>
<body>
<h2>Offer topics to speakers command</h2>
<table>
    <tr>
        <th>Topic`s ID</th>
        <th>Event`s ID</th>
        <th>Speaker`s ID</th>
        <th>Topic name</th>
        <th>Topic description</th>
        <th>Choose speaker</th>
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
                    <input type="submit" value="Choose">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="conferences?command=returnToAcc">To the main page</a>
</body>
</html>
