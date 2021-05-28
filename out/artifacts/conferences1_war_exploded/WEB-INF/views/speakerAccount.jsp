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
   <form name="Form3" action="conferences">
       <input type="hidden" name="command" value="logOut">
       <input type="submit" value="Выйти">
   </form>
</body>
</html>
