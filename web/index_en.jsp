<%--
  Created by IntelliJ IDEA.
  User: Envy
  Date: 31.05.2021
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Welcome to conferences</h1>
<div>
    <h2>Enter</h2>
    <form name="Form2" method="post" action="conferences">
        <input type="hidden" name="command" value="login">
        <table style="width: 80%">
            <tr>
                <td>Email</td>
                <td>
                    <input type="email" name="email" required>
                </td>
            </tr>
            <tr>
                <td>Password</td>
                <td>
                    <input type="text" name="password" required>
                </td>
            </tr>
        </table>
        <input type="submit" value="Login">
    </form>
</div>
<div>
    <a href="conferences?command=changeLang&language=EN">EN</a>
    <a href="conferences?command=changeLang&language=UA">UA</a>
</div>
<div>
    <a href="registration_en.jsp">Registration</a>
</div>
</body>
</html>
