<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><html>
<html>
<head>
    <title>SortedEvents</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
<div class="personal-listener d-flex container-fluid">
    <h2>Заходи які вже завершились</h2>
</div>

<table class="table-registr ">
    <tr>
        <th>ID</th>
        <th>Назва</th>
        <th>Опис</th>
        <th>Дата проведення</th>
        <th>Місце</th>
        <th>Присутність</th>
    </tr>
    <c:forEach var="i" items="${events}">
        <tr>
            <td>${i.id}</td>
            <td>${i.name}</td>
            <td>${i.description}</td>
            <td>${i.formattedDate}</td>
            <td>${i.place}</td>
            <td>
                <form name="eventUnsubscriptionForm" action="conferences">
                    <input type="hidden" name="command" value="markPresence">
                    <input type="hidden" name="eventID" value="${i.id}">
                    <input type="hidden" name="presence" value="yes">
                    <input type="submit" value="Був(Була)" class="submit-btn">
                </form>
                <form name="eventUnsubscriptionForm" action="conferences">
                    <input type="hidden" name="command" value="markPresence">
                    <input type="hidden" name="eventID" value="${i.id}">
                    <input type="hidden" name="presence" value="no">
                    <input type="submit" value="Не Був(Не Була)" class="submit-btn">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<p class="to-main">
    <a href="conferences?command=returnToAcc">На головну</a>
</p>
</body>
</html>
