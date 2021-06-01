<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 31.05.2021
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>Listener cabinet</title>
</head>
<body>
<h2>Hello listener!</h2>
<p>${sessionScope.role}</p>
<p>Your id: ${sessionScope.id}</p>
<p>Your name: ${sessionScope.name}</p>
<p>__________________________________________________________________________________________</p>
<p>Action result: ${requestScope.result}</p>
<p>__________________________________________________________________________________________</p>
<form name="subscribeForEvent" acction="conferences">
    <input type="hidden" name="command" value="eventSubscriptionInfo" >
    <input type="hidden" name="page" value="1" >
    <input type="submit" value="Registration for events">
</form>
<p>__________________________________________________________________________________________</p>
<p>Watch event history</p>
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
    <input type="submit" value="Look">
</form>
<p>__________________________________________________________________________________________</p>
<form name="form3" acction="conferences">
    <input type="hidden" name="command" value="logOut">
    <input type="submit" value="Logout">
</form>
</body>
</html>
