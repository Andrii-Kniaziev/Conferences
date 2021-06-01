<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 23.05.2021
  Time: 12:02
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
   <p>Ваш id: ${sessionScope.id}</p>
   <p>Ваше ім'я: ${sessionScope.name}</p>
   <p>__________________________________________________________________________________________</p>
   <p>Результат дії: ${requestScope.result}</p>
   <p>__________________________________________________________________________________________</p>
   <form name="subscribeForEvent" acction="conferences">
       <input type="hidden" name="command" value="eventSubscriptionInfo" >
       <input type="hidden" name="page" value="1" >
       <input type="submit" value="Регистрация на ивенты">
   </form>
   <p>__________________________________________________________________________________________</p>
   <p>Переглянути івенти</p>
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
       <input type="submit" value="Переглянути">
   </form>
   <p>__________________________________________________________________________________________</p>
   <form name="form3" acction="conferences">
       <input type="hidden" name="command" value="logOut">
       <input type="submit" value="Вийти">
   </form>
</body>
</html>
