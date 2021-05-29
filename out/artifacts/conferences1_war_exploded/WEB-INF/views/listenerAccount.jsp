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
   <p>Ваше имя: ${sessionScope.name}</p>
   <p>__________________________________________________________________________________________</p>
   <p>Результат: ${requestScope.result}</p>
   <p>__________________________________________________________________________________________</p>
   <form name="subscribeForEvent" acction="conferences">
       <input type="hidden" name="command" value="eventSubscriptionInfo" >
       <input type="hidden" name="page" value="1" >
       <input type="submit" value="Регистрация на ивенты">
   </form>
   <form name="form3" acction="conferences">
       <input type="hidden" name="command" value="logOut">
       <input type="submit" value="Выйти">
   </form>
</body>
</html>
