<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 25.05.2021
  Time: 12:20
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
    Наявні спікери <br/>
</h2>
<table>
    <tr><th>ID</th><th>Ім'я</th><th>Прізвище</th><th>Email</th><th>Роль</th></tr>
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
    Створити нову доповідь <br/>
</h2>
<p>Результат дії: ${requestScope.result}</p>
<br/>
<form name="NewTopicForm" method="post" action="conferences">
    <input type="hidden" name="command" value="registerTopic">
    <table style="width: 80%">
        <tr>
            <td>Назва</td>
            <td>
                <input type="text" name="topicName" required>
            </td>
        </tr>
        <tr>
            <td>Опис</td>
            <td>
                <input type="text" name="topicDescription" required>
            </td>
        </tr>
        <tr>
            <td>Захід</td>
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
            <td>Спікер</td>
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
            <td>Спікер вже погодився</td>
            <td>
                <select name="speakerApproved" required>
                    <option value="true">Так</option>
                    <option value="false">Ні</option>
                </select>
            </td>
        </tr>

    </table>
    <input type="submit" value="Створити">
</form>
<h2>__________________________________________________________________</h2>
<a href="conferences?command=returnToAcc">На головну</a>
</body>
</html>
