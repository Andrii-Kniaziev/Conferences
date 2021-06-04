<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Speaker cabinet</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div class=" personal-listener container-fluid heading d-flex">
    <h2>Hello Speaker!</h2>
    <p>${sessionScope.role}</p>
    <p>Ваш id: ${sessionScope.id}</p>
    <p>Ваше ім'я: ${sessionScope.name}</p>
    <form name="Form3" action="conferences">
        <input type="hidden" name="command" value="logOut">
        <input type="submit" value="Вийти" class="btn btn-light">
    </form>
</div>

<div class="alert">Результат дії: ${requestScope.result}</div>

<div class="container d-flex speaker-container">
    <div class="speaker-wrapper d-flex">
        <h3>Пропозиції провести доповіді та ті які узгоджені</h3>
        <form name="showOfferedTopics" action="conferences">
            <input type="hidden" name="command" value="showOfferedTopics">
            <input type="submit" value="Переглянути" class="btn submit-btn">
        </form>
    </div>
    <div class="speaker-wrapper d-flex">
        <h3>Доповіді на розгляді</h3>
        <form name="showTopicsInProcess" action="conferences">
            <input type="hidden" name="command" value="showTopicsInProcess">
            <input type="submit" value="Переглянути" class="btn submit-btn">
        </form>
    </div>
</div>
<div class="container d-flex speaker-container2">
    <div class="speaker-wrapper">
        <h3>Переглянути заходи</h3>
        <form name="form5" acction="conferences">
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
            <input type="submit" value="Переглянути" class="btn submit-btn">
        </form>
    </div>

    <div class="speaker-wrapper">
        <h3>Запропонувати доповідь для заходу</h3>
        <form name="topicForEvent" method="post" action="conferences">
            <input type="hidden" name="command" value="offerTopicForEvent">
            <p>
                <label>
                    Ім'я Доповіді: <input type="text" name="topicName" required>
                </label>
            </p>
            <p>
                <label>
                    Опис: <input type="text" name="topicDescription" required>
                </label>
            </p>
            <p>
                <label>
                    ID заходу: <input type="number" step="1" name="eventID" required pattern="/d+/">
                </label>
            </p>
            <input type="submit" value="Запропонувати" class="btn submit-btn">
        </form>
    </div>
</div>


</body>
</html>
