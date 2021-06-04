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
    <p>Your id: ${sessionScope.id}</p>
    <p>Your name: ${sessionScope.name}</p>
    <form name="form3" acction="conferences">
        <input type="hidden" name="command" value="logOut">
        <input type="submit" value="Logout" class="btn btn-light">
    </form>
</div>

<div class="alert">Action result: ${requestScope.result}</div>

<div class="container d-flex container-wrapper">
    <div class="wrapper">
        <form name="subscribeForEvent" acction="conferences">
            <input type="hidden" name="command" value="eventSubscriptionInfo">
            <input type="hidden" name="page" value="1">
            <input type="submit" value="Registration for events" class="btn btn-dark">

        </form>
    </div>
    <div class="wrapper">
    <p>Watch event history</p>
    <form name="form5" acction="conferences" class="watch-events-form">
        <select name="eventTime" required>
            <option value="future">Future</option>
            <option value="past">Past</option>
        </select>
        <select name="sortBy" required>
            <option value="date">By date</option>
            <option value="topicNumber">By topic quantity</option>
            <option value="listenersNumber">By quantity of listeners</option>
        </select>
        <input type="hidden" name="command" value="getEvents">
        <input type="hidden" name="page" value="1">
        <input type="submit" value="Look" class="btn btn-watch">
    </form>
    </div>
</div>

</body>
</html>
