<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 28.05.2021
  Time: 11:30
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
        <th>Название</th>
        <th>Описание</th>
        <th>Дата</th>
        <th>Место</th>
        <th>Подписаться</th>
    </tr>
    <c:forEach var="i" items="${events}">
        <tr>
            <td>${i.name}</td>
            <td>${i.description}</td>
            <td>${i.formattedDate}</td>
            <td>${i.place}</td>
            <td>
                <form name="eventSubscriptionForm" action="conferences">
                    <input type="hidden" name="command" value="subscribeForEvent">
                    <input type="hidden" name="eventID" value="${i.id}">
                    <input type="submit" value="Зарегистрироваться">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<c:forEach var="i" items="${pages}">
    <a href="conferences?command=eventSubscriptionInfo&page=${i}">${i}</a>
</c:forEach>
<br/>
<a href="conferences?command=returnToAcc">На главную</a>
</body>
</html>
