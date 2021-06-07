<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Listener cabinet</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class="personal-listener d-flex container-fluid">
    <h2>Hello listener!</h2>
    <p>${sessionScope.role}</p>
    <p>Ваш id: ${sessionScope.id}</p>
    <p>Ваше ім'я: ${sessionScope.name}</p>
    <form name="form3" acction="conferences">
        <input type="hidden" name="command" value="logOut">
        <input type="submit" value="Вийти" class="btn btn-light">
    </form>
</div>

<div class="alert">Результат дії: ${requestScope.result}</div>

<div class="container-small d-flex container-wrapper">
    <div class="wrapper">
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
    <div class="wrapper">
        <form name="subscribeForEvent" acction="conferences">
            <input type="hidden" name="command" value="eventSubscriptionInfo">
            <input type="hidden" name="page" value="1">
            <input type="submit" value="Реєстрація на заходи" class="btn btn-dark">
        </form>
    </div>

    <div class="wrapper">
        <form name="markPresence" acction="conferences">
            <input type="hidden" name="command" value="markPresenceInfo">
            <input type="submit" value="Відмітити присутність" class="btn btn-dark">
        </form>
    </div>
</div>


</body>
</html>
