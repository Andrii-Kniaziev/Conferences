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
   <p>Ваше имя: ${sessionScope.name}</p>
   <p>__________________________________________________________________________________________</p>
   <p>Результат: ${requestScope.result}</p>
   <p>__________________________________________________________________________________________</p>
   <br/>
   <div>
   <p>Форма создания нового ивента</p>
       <form name="form4" method="post" action="conferences">
           <input type="hidden" name="command" value="createEvent">
           <p>
               <label>
                   Имя ивента: <input type="text" name="eventName" required>
               </label>
           </p>
           <p>
               <label>
                   Описание: <input type="text" name="eventDescription" required>
               </label>
           </p>
           <p>
               <label>
                   Дата и Время: <input type="text" name="eventDate" placeholder="yyyy-MM-dd HH:mm" required>
               </label>
           </p>
           <p>
               <label>
                   Место: <input type="text" name="eventPlace" required>
               </label>
           </p>
           <input type="submit" value="Создать">
       </form>
   </div>
   <p>__________________________________________________________________________________________</p>
   <form name="form5" acction="conferences">
       <input type="hidden" name="command" value="getEvents">
       <input type="submit" value="Показать список ивентов">
   </form>
   <p>__________________________________________________________________________________________</p>
   <form name = "form6" action="conferences">
       <input type="hidden" name="command" value="topicCreateForm">
       <input type="submit" value="Создать топик">
   </form>
   <p>__________________________________________________________________________________________</p>
   <form name="form3" acction="conferences">
       <input type="hidden" name="command" value="logOut">
       <input type="submit" value="Выйти">
   </form>
</body>
</html>
