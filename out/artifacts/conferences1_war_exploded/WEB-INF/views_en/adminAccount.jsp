<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 31.05.2021
  Time: 13:54
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
    <p>Your id: ${sessionScope.id}</p>
    <p>Your name: ${sessionScope.name}</p>
    <form name="form3" acction="conferences">
        <input type="hidden" name="command" value="logOut">
        <input type="submit" value="Logout" class="btn btn-light">
    </form>
</div>

<div class="alert">Action result: ${requestScope.result}</div>


<div class="create-new-event container">
    <h3>New event creation form</h3>
    <form name="form4" method="post" action="conferences">
        <input type="hidden" name="command" value="createEvent">
        <p>
            <label>
                Event name: <input type="text" name="eventName" required>
            </label>
        </p>
        <p>
            <label>
                Description: <input type="text" name="eventDescription" required>
            </label>
        </p>
        <p>
            <label>
                Date: <input type="text" name="eventDate" placeholder="yyyy-MM-dd" required>
            </label>
        </p>
        <p>
            <label>
                Time: <input type="text" name="eventTime" placeholder="HH:mm" required>
            </label>
        </p>
        <p>
            <label>
                Place: <input type="text" name="eventPlace" required>
            </label>
        </p>
        <p class="submit">
            <input type="submit" value="Create" class="btn submit-btn">
        </p>
    </form>
</div>
<div class="admin-acc-wrapper d-flex container">
    <div>
        <h3>Watch event history</h3>
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
    <div>
        <form name="form6" action="conferences">
            <input type="hidden" name="command" value="topicCreateForm">
            <input type="submit" value="Create new topic" class="btn btn-watch">
        </form>
        <form name="form6" action="conferences">
            <input type="hidden" name="command" value="offerTopicToSpeakerInfo">
            <input type="submit" value="Topics in process" class="btn btn-watch">
        </form>
    </div>
</div>

</body>
</html>
