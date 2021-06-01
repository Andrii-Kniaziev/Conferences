<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 23.05.2021
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Реєстрація</title>
</head>
<body>
<div align="center">
    <h1>Форма реєстрації користувача</h1>
    <form name="Form1" method="post" action="conferences">
        <input type="hidden" name="command" value="register">
        <table style="width: 80%">
            <tr>
                <td>Ім'я</td>
                <td>
                    <input type="text" name="first_name" required>
                </td>
            </tr>
            <tr>
                <td>Прізвище</td>
                <td>
                    <input type="text" name="last_name" required>
                </td>
            </tr>
            <tr>
                <td>Імейл</td>
                <td>
                    <input type="email" name="email" required>
                </td>
            </tr>
            <tr>
                <td>Пароль</td>
                <td>
                    <input type="password" name="password" required>
                </td>
            </tr>
            <tr>
                <td>Роль</td>
                <td>
                    <select name="role" required>
                        <option value=" " selected disabled></option>
                        <option value="speaker">Speaker</option>
                        <option value="listener">Listener</option>
                    </select>
                </td>
            </tr>
        </table>
        <input type="submit" value="Зареєструватися">
    </form>
</div>
<div>
    <a href="index.jsp">На сторінку логіну</a>
</div>
</body>
</html>
