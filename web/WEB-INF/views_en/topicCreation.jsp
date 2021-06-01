<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 31.05.2021
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false"%>
<%@ page import="java.util.*, java.text.*" %>

<html>
<head>
    <title>Topic creation Form</title>
</head>
<body>
<h2>
    Topic creation Form <br/>
</h2>
<h2>
    Speakers available <br/>
</h2>
<table>
    <tr><th>ID</th><th>Name</th><th>Surname</th><th>Email</th><th>Role</th></tr>
    <c:forEach var="i" items="${accounts}">
        <tr><td>${i.id}</td>
            <td>${i.firstName}</td>
            <td>${i.lastName}</td>
            <td>${i.email}</td>
            <td>${i.role}</td></tr>
    </c:forEach>
</table>
<h2>__________________________________________________________________</h2>
<h2>
    Create new topic <br/>
</h2>
<p>Result of action: ${requestScope.result}</p>
<br/>
<form name="NewTopicForm" method="post" action="conferences">
    <input type="hidden" name="command" value="registerTopic">
    <table style="width: 80%">
        <tr>
            <td>Topic name</td>
            <td>
                <input type="text" name="topicName" required>
            </td>
        </tr>
        <tr>
            <td>Description</td>
            <td>
                <input type="text" name="topicDescription" required>
            </td>
        </tr>
        <tr>
            <td>Event</td>
            <td>
                <select name="eventID" required>
                    <option value=" " selected disabled></option>
                    <c:forEach var="i" items="${events}">
                        <option value="${i.id}">${i.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Speaker</td>
            <td>
                <select name="speakerID" required>
                    <option value=" " selected disabled></option>
                    <c:forEach var="i" items="${accounts}">
                        <option value="${i.id}">${i.firstName} ${i.lastName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>Speaker agreed</td>
            <td>
                <select name="speakerApproved" required>
                    <option value="true">Yes</option>
                    <option value="false">No</option>
                </select>
            </td>
        </tr>

    </table>
    <input type="submit" value="Створити">
</form>
<h2>__________________________________________________________________</h2>
<a href="conferences?command=returnToAcc">To the main page</a>
</body>
</html>
