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
<h2>Предложить провести топик</h2>
<table>
    <tr>
        <th>Topic ID</th>
        <th>Event ID</th>
        <th>Speaker ID</th>
        <th>Topic`s name</th>
        <th>Description</th>
        <th>Choose Speaker</th>
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
                        <c:forEach var="a" items="${accounts}">
                            <option value="${a.id}">${a.firstName} ${a.lastName}</option>
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
<a href="conferences?command=returnToAcc">На главную</a>
</body>
</html>
