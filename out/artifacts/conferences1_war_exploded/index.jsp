<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 23.05.2021
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
  <head>
    <title>Login</title>
  </head>
  <body>
  <h1>Добро пожаловать на конференции</h1>
  <div>
    <h2>Вход</h2>
    <form name="form2" method="post" acction="conferences">
      <input type="hidden" name="command" value="login">
      <table style="width: 80%">
        <tr>
          <td>Имейл</td>
          <td>
            <input type="email" name="email">
          </td>
        </tr>
        <tr>
          <td>Пароль</td>
          <td>
            <input type="text" name="password">
          </td>
        </tr>
      </table>
      <input type="submit" value="Войти">
    </form>
  </div>

  <div>
    <a href="registration.jsp">Регистрация</a>
  </div>

  </body>
</html>
