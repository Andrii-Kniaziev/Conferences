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
   <p>Ваше ім'я: ${sessionScope.name}</p>
   <p>__________________________________________________________________________________________</p>
   <p>Результат дії: ${requestScope.result}</p>
   <p>__________________________________________________________________________________________</p>
   <p>Пропозиції провести доповіді та ті які узгоджені</p>
   <form name="showOfferedTopics" action="conferences">
       <input type="hidden" name="command" value="showOfferedTopics">
       <input type="submit" value="Переглянути">
   </form>
   <p>__________________________________________________________________________________________</p>
   <p>Дповіді на розгляді</p>
   <form name="showTopicsInProcess" action="conferences">
       <input type="hidden" name="command" value="showTopicsInProcess">
       <input type="submit" value="Переглянути">
   </form>
   <p>__________________________________________________________________________________________</p>
   <p>Переглянути заходи</p>
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
   <div>
       <p>Запропонувати доповідь для заходу</p>
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
           <input type="submit" value="Запропонувати">
       </form>
   </div>
   <p>__________________________________________________________________________________________</p>
   <form name="Form3" action="conferences">
       <input type="hidden" name="command" value="logOut">
       <input type="submit" value="Вийти">
   </form>
</body>
</html>
