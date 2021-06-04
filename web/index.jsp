<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
  <head>
    <title>Логін</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
  </head>
  <body>
  <div class="heading">
    <h1>Вітаємо на Конференціях</h1>
  </div>

  <div class="registration">
    <h2>Вхід</h2>
    <form name="Form2" method="post" action="conferences"  >
      <input type="hidden" name="command" value="login">
      <table style="width: 80%">
        <tr class="registration-line">
          <td class="registration-title">Імейл</td>
          <td>
            <input type="email" name="email" required class="registration-input">
          </td>
        </tr>
        <tr class="registration-line">
          <td class="registration-title" >Пароль</td>
          <td>
            <input type="password" name="password" required class="registration-input">
          </td>
        </tr>
      </table>
      <input type="submit" value="Увійти" class="btn btn-password">
    </form>
  </div>
  <div class="languages">
    <a href="conferences?command=changeLang&language=EN">EN</a>
    <a href="conferences?command=changeLang&language=UA">UA</a>
  </div>
  <div class="registration-page">
    <a href="registration.jsp">Реєстрація</a>
  </div>

  </body>
</html>
