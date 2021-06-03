<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 23.05.2021
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Admin Cabinet</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="personal-listener d-flex container-fluid">
    <h2>Hello Admin!</h2>
    <p>${sessionScope.role}</p>
    <p>Ваш id: ${sessionScope.id}</p>
    <p>Ваше ім'я: ${sessionScope.name}</p>
    <form name="form3" acction="conferences">
        <input type="hidden" name="command" value="logOut">
        <input type="submit" value="Вийти" class="btn btn-light">
    </form>
</div>

<div class="alert">Результат дії: ${requestScope.result}</div>


<div class="create-new-event container">
    <h3>Форма створення нового заходу</h3>
    <form name="form4" method="post" action="conferences">
        <input type="hidden" name="command" value="createEvent">
        <p>
            <label>
                Ім'я івента: <input type="text" name="eventName" required>
            </label>
        </p>
        <p>
            <label>
                Опис: <input type="text" name="eventDescription" required>
            </label>
        </p>
        <p>
            <label>
                Дата: <input type="text" name="eventDate" placeholder="yyyy-MM-dd" required>
            </label>
        </p>
        <p>
            <label>
                Час: <input type="text" name="eventTime" placeholder="HH:mm" required>
            </label>
        </p>
        <p>
            <label>
                Місце: <input type="text" name="eventPlace" required>
            </label>
        </p>
        <p class="submit">
            <input type="submit" value="Створити" class="btn submit-btn">
        </p>
    </form>
</div>
<div class="admin-acc-wrapper d-flex container">
    <div>
        <h3>Переглянути івенти</h3>
        <form name="form5" acction="conferences" class="watch-events-form">
            <select name="eventTime" required>
                <option value="future">Майбутні</option>
                <option value="past">Минулі</option>
            </select>
            <select name="sortBy" required>
                <option value="date">За датою</option>
                <option value="topicNumber">За кількістю доповідей</option>
                <option value="listenersNumber">За кількістю слухачів</option>
            </select>
            <input type="hidden" name="command" value="getEvents">
            <input type="hidden" name="page" value="1">
            <input type="submit" value="Переглянути" class="btn btn-watch">
        </form>
    </div>
    <div>
        <form name="form6" action="conferences">
            <input type="hidden" name="command" value="topicCreateForm">
            <input type="submit" value="Створити доповідь" class="btn btn-watch">
        </form>

        <form name="form6" action="conferences">
            <input type="hidden" name="command" value="offerTopicToSpeakerInfo">
            <input type="submit" value="Доповіді в обробці" class="btn btn-watch">
        </form>
    </div>
</div>

</body>
</html>
