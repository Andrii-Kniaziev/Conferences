<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 23.05.2021
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>Speaker cabinet</title>
</head>
<body>
   <h2>Hello Speaker!</h2>
   <p>${sessionScope.role}</p>
   <p>Ваш id: ${sessionScope.id}</p>
   <p>Ваше имя: ${sessionScope.name}</p>
   <p>__________________________________________________________________________________________</p>
   <p>Результат: ${requestScope.result}</p>
   <p>__________________________________________________________________________________________</p>
   <p>Предложения провести топики</p>
   <form name="showOfferedTopics" action="conferences">
       <input type="hidden" name="command" value="showOfferedTopics">
       <input type="submit" value="Показать">
   </form>
   <p>__________________________________________________________________________________________</p>
   <p>Просмотреть ивенты</p>
   <form name="form5" acction="conferences">
       <select name="eventTime" required>
           <option value="future">Будущие</option>
           <option value="past">Прошлые</option>
       </select>
       <select name="sortBy" required>
           <option value="date">По дате</option>
           <option value="topicNumber">По количеству топиков</option>
           <option value="listenersNumber">По количеству участников</option>
       </select>
       <input type="hidden" name="command" value="getEvents">
       <input type="hidden" name="page" value="1">
       <input type="submit" value="Показать">
   </form>
   <p>__________________________________________________________________________________________</p>
   <form name="Form3" action="conferences">
       <input type="hidden" name="command" value="logOut">
       <input type="submit" value="Выйти">
   </form>
</body>
</html>
