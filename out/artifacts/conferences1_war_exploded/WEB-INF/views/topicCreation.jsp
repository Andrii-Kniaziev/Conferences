<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ page import="java.util.*, java.text.*" %>

<html>
<head>
    <title>Topic creation Form</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="personal-listener d-flex container-fluid">
    <h2>
        Topic creation Form <br/>
    </h2>
</div>
<div class="container">
    <h3>
        Наявні спікери
    </h3>
    <table class="table-registr ">
        <tr>
            <th>ID</th>
            <th>Ім'я</th>
            <th>Прізвище</th>
            <th>Email</th>
            <th>Роль</th>
        </tr>
        <c:forEach var="i" items="${accounts}">
            <tr>
                <td>${i.id}</td>
                <td>${i.firstName}</td>
                <td>${i.lastName}</td>
                <td>${i.email}</td>
                <td>${i.role}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div class="alert">Результат дії: ${requestScope.result}</div>
<div class=" create-new-topic-container container">
    <h3>
        Створити нову доповідь <br/>
    </h3>


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
        <input type="submit" value="Створити" class="btn submit-btn">
    </form>
</div>
<p class="to-main">
    <a href="conferences?command=returnToAcc">На головну</a>
</p>
</body>
</html>
