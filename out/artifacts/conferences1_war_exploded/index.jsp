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
    <title>Логін</title>
  </head>
  <body>
  <h1>Вітаємо на Конференціях</h1>
  <div>
    <h2>Вхід</h2>
    <form name="Form2" method="post" action="conferences">
      <input type="hidden" name="command" value="login">
      <table style="width: 80%">
        <tr>
          <td>Імейл</td>
          <td>
            <input type="email" name="email" required>
          </td>
        </tr>
        <tr>
          <td>Пароль</td>
          <td>
            <input type="text" name="password" required>
          </td>
        </tr>
      </table>
      <input type="submit" value="Увійти">
    </form>
  </div>
  <div>
    <a href="conferences?command=changeLang&language=EN">EN</a>
    <a href="conferences?command=changeLang&language=UA">UA</a>
  </div>
  <div>
    <a href="registration.jsp">Реєстрація</a>
  </div>

  </body>
</html>
