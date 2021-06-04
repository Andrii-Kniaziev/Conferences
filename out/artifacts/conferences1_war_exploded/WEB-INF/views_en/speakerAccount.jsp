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
    <p>Your id: ${sessionScope.id}</p>
    <p>Your name: ${sessionScope.name}</p>
    <form name="Form3" action="conferences">
        <input type="hidden" name="command" value="logOut">
        <input type="submit" value="Logout" class="btn btn-light">
    </form>
</div>

<div class="alert">Action result: ${requestScope.result}</div>
<div class="container d-flex speaker-container">
    <div class="speaker-wrapper d-flex">
        <h3>Offers to spend topic and accepted</h3>
        <form name="showOfferedTopics" action="conferences">
            <input type="hidden" name="command" value="showOfferedTopics">
            <input type="submit" value="Look" class="btn submit-btn">
        </form>
    </div>
    <div class="speaker-wrapper d-flex">
        <h3>Topics in process</h3>
        <form name="showTopicsInProcess" action="conferences">
            <input type="hidden" name="command" value="showTopicsInProcess">
            <input type="submit" value="Look" class="btn submit-btn">
        </form>
    </div>
</div>
<div class="container d-flex speaker-container2">
    <div class="speaker-wrapper">
        <h3>Watch event history</h3>
        <form name="form5" acction="conferences">
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
            <input type="submit" value="Look" class="btn submit-btn">
        </form>
    </div>
    <div class="speaker-wrapper">
        <h3>Offer topic for event</h3>
        <form name="topicForEvent" method="post" action="conferences">
            <input type="hidden" name="command" value="offerTopicForEvent">
            <p>
                <label>
                    Topic`s name: <input type="text" name="topicName" required>
                </label>
            </p>
            <p>
                <label>
                    Description: <input type="text" name="topicDescription" required>
                </label>
            </p>
            <p>
                <label>
                    Event ID: <input type="number" step="1" name="eventID" required pattern="/d+/">
                </label>
            </p>
            <input type="submit" value="Offer" class="btn submit-btn">
        </form>
    </div>
</div>
</body>
</html>
