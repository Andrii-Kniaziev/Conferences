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
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../styles.css">
</head>
<div>
    <table style="width: 80%" class="table-registr ">
        <tr>
            <th>ID Івента</th>
            <th>Назва</th>
            <th>Опис</th>
            <th>Дата</th>
            <th>Місце</th>
            <th>Зареєструватися / Відписатися</th>
        </tr>
        <c:forEach var="i" items="${events}">
            <tr>
                <td>${i.id}</td>
                <td>${i.name}</td>
                <td>${i.description}</td>
                <td>${i.formattedDate}</td>
                <td>${i.place}</td>
                <td>
                    <c:set var="currentID" value="${i.id}"/>
                    <c:set var="contains" value="false"/>
                    <c:forEach var="eID" items="${eventIDs}">
                        <c:if test="${eID eq currentID}">
                            <c:set var="contains" value="true"/>
                        </c:if>
                    </c:forEach>
                    <c:if test="${contains eq 'true'}">
                        <form name="eventUnsubscriptionForm" action="conferences">
                            <input type="hidden" name="command" value="unsubscribeFromEvent">
                            <input type="hidden" name="eventID" value="${i.id}">
                            <input type="submit" value="Відписатися" class="submit-btn">
                        </form>
                    </c:if>
                    <c:if test="${contains eq 'false'}">
                        <form name="eventSubscriptionForm" action="conferences">
                            <input type="hidden" name="command" value="subscribeForEvent">
                            <input type="hidden" name="eventID" value="${i.id}">
                            <input type="submit" value="Зареєструватися" class="submit-btn">
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="page-number">
<c:forEach var="i" items="${pages}">
    <a href="conferences?command=eventSubscriptionInfo&page=${i}">${i}</a>
</c:forEach>
</div>
<br/>
<p class="to-main">
    <a href="conferences?command=returnToAcc">На головну</a>
</p>
</body>
</html>
