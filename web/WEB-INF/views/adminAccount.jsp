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
</head>
<body>
   <h2>Hello Admin!</h2>
   <p>${sessionScope.role}</p>
   <p>Ваш id: ${sessionScope.id}</p>
   <p>Ваше ім'я: ${sessionScope.name}</p>
   <p>__________________________________________________________________________________________</p>
   <p>Результат дії: ${requestScope.result}</p>
   <p>__________________________________________________________________________________________</p>
   <br/>
   <div>
   <p>Форма створення нового заходу</p>
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
           <input type="submit" value="Створити">
       </form>
   </div>
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
   <form name = "form6" action="conferences">
       <input type="hidden" name="command" value="topicCreateForm">
       <input type="submit" value="Створити доповідь">
   </form>
   <p>__________________________________________________________________________________________</p>
   <form name = "form6" action="conferences">
       <input type="hidden" name="command" value="offerTopicToSpeakerInfo">
       <input type="submit" value="Запропонувати доповідь спікеру">
   </form>
   <p>__________________________________________________________________________________________</p>
   <form name="form3" acction="conferences">
       <input type="hidden" name="command" value="logOut">
       <input type="submit" value="Вийти">
   </form>
</body>
</html>
